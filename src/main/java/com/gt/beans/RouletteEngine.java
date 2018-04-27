package com.gt.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gt.config.Config;
import com.gt.controller.RouletteController;
import com.gt.hibernate.GtGameAccount;
import com.gt.hibernate.GtPlayerBets;
import com.gt.hibernate.GtSpinBets;
import com.gt.hibernate.GtSpins;
import com.gt.logic.GameLogic;
import com.gt.request.GameRequest;
import com.gt.services.GtGameAccountServiceImpl;
import com.gt.services.GtPlayerBetsServiceImpl;
import com.gt.services.GtSpinBetsServiceImpl;
import com.gt.services.GtSpinsServiceImpl;
import com.gt.services.WebServiceCalling;

import generated.Bet;
import generated.BetState;

@Component
public class RouletteEngine {
	private static final Logger LOGGER = Logger.getLogger(RouletteController.class);
	private WebServiceCalling webServiceCalling;
	private GtGameAccountServiceImpl gtGameAccountServiceImpl;
	private GtPlayerBetsServiceImpl gtPlayerBetsServiceImpl;
	private GtSpinsServiceImpl gtSpinsServiceImpl;
	private GtSpinBetsServiceImpl gtSpinBetsService; 
	private static final String SERVER_PRIVATE_KEY = "DC892D7D7EE2EE48F2426030C3D0A76F59400D851562165D8F283B4E3AEA5C82FF065847B49B4CC27F3DA907D7ADC3DAE7F643A9FB9133E5A1EC2EE549A5D9A4";
	private static final int PLATFORM_ID = 3;

	@Autowired
	Config config;
	@Autowired
	public void setWebServiceCalling(WebServiceCalling webServiceCalling) {
		this.webServiceCalling = webServiceCalling;
	}
	@Autowired
	public void setGtSpinBetsService(GtSpinBetsServiceImpl gtSpinBetsService) {
		this.gtSpinBetsService = gtSpinBetsService;
	}
	@Autowired(required=false)
	public void setGtGameAccountServiceImpl(GtGameAccountServiceImpl gtGameAccountServiceImpl) {
		this.gtGameAccountServiceImpl = gtGameAccountServiceImpl;
	}
	@Autowired
	public void setGtPlayerBetsServiceImpl(GtPlayerBetsServiceImpl gtPlayerBetsServiceImpl) {
		this.gtPlayerBetsServiceImpl = gtPlayerBetsServiceImpl;
	}
	@Autowired
	public void setGtSpinsServiceImpl(GtSpinsServiceImpl gtSpinsServiceImpl) {
		this.gtSpinsServiceImpl = gtSpinsServiceImpl;
	}

	public GameObject init(GameRequest gameRequest) {
		int playerId = 0;
		int gameId = 0;
		double realBalance = 0;
		double dummyBalance = 0;
		String cookie="";
		try {
			cookie = gameRequest.getHeader().getCustomer().getCookie();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_MALFORMED_XML, ExceptionMessage.ERROR_MSG_MALFORMED_XML);
		}
		HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
		requestesParameters.put("session_key", cookie);
		String url = config.getApiBaseUrl() + UrlCall.API_MY_ACCOUNT;
		String response = null;
		try {
			response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, e.getMessage());
		}

		if (response != null) {
			try {
				Map<String, Object> value = new Gson().fromJson(
						response, new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				LOGGER.info("my account api response :  "+value);
				Map<String, Object> data = new Gson().fromJson(
						value.get("data").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				Map<String, Object> userBalance = new Gson().fromJson(
						data.get("user_balance").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				if(data.containsKey("user_details")) {
					Map<String, Object> userDetails = new Gson().fromJson(
							data.get("user_details").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
							);

					Double userId = (Double) userDetails.get("user_id");
					playerId = userId.intValue();

				}
				realBalance = (Double) userBalance.get("real_amount");
				dummyBalance = (Double) userBalance.get("dummy_amount");
				System.out.println("init - player id - " + playerId + " .realBalance - " + realBalance + " .dummyBalanace - " + dummyBalance);
			} catch (Exception e) {
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " error decoding api response" );
			}
		}else {
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " session key expired." );
		}

		GtGameAccount lastGame = getLastActiveGame(playerId);					// get last game not closed.
		GameObject gameObj = new GameObject(gameId, playerId);
		List<Integer> lastDraws = getLastDraws();
		gameObj.setLastDraws(lastDraws);
		if(lastGame==null) {
			gameObj.setResultInfo(null);
		}else {
			ResultInfo resultInfo = new ResultInfo(lastGame.getBetAmount(), lastGame.getWinnings(),null,lastGame.getDraw());
			gameObj.setResultInfo(resultInfo);
			// TODO : add betState of that game.
			List<GtPlayerBets> bets = gtPlayerBetsServiceImpl.findAllByGameId(lastGame.getId(), playerId);
			BetState betState = new BetState();
			betState.setDrawn(lastGame.getDraw());
			betState.setTotalBets((byte) bets.size());
			for(GtPlayerBets playerBet : bets) {
				betState.getBet().add(new Bet(playerBet.getBetName(), playerBet.getStake(), playerBet.getSelection()));
			}
			GameLogic logic = new GameLogic();
			betState = logic.calculateWinnings((byte)lastGame.getDraw(), betState);
			gameObj.setBetState(betState);
		}

		// if timely roulette set spin id and spin time
		if(gameRequest.getHeader().getGameDetails().getTimeRoulette() != null &&
				gameRequest.getHeader().getGameDetails().getTimeRoulette().equals("Yes")){
			// find next scheduled spin
			GtSpins gtSpin = findNextSpin();
			// set next scheduled spin params
			if(gtSpin != null) {
				System.out.println(gtSpin.toString());
				gameObj.setSpinTime(gtSpin.getSpinTime());
				gameObj.setSpinId(gtSpin.getId());
			}
		}
		// set player's balance
		gameObj.setRealBalance(realBalance);
		gameObj.setDummyBalance(dummyBalance);
		return gameObj;


	}

	public GameObject spin(GameRequest gameRequest) {
		//int playerId = 0;
		int gameId = 0;
		double realBalance = 0;
		double dummyBalance = 0;
		int cashType = 0;
		float totalBet = 0.00f;
		byte drawn;
		float totalWinnings = 0.00f;
		boolean isSuccess = false;
		String cookie=null;
		String response;
		try {
			cookie = gameRequest.getHeader().getCustomer().getCookie();
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_MALFORMED_XML, ExceptionMessage.ERROR_MSG_MALFORMED_XML);
		}
		// find playerId from cookie
		int playerId = getPlayerIdFromCookie(cookie);
		// find last active game, if any.
		GtGameAccount gameAccount = getLastActiveGame(playerId);
		GameObject gameObj = null;

		// check cash type for this spin.
		if(gameRequest.getPlay().getFreebets().equals("Yes")) {
			cashType = 0;			// 3 - dummy
			System.out.println("cash type " + cashType);
		}else {
			cashType = 0;			// 0 - real
			System.out.println("cash type no " + cashType);
		}

		// if no active game exists, create new gameAccount
		if(gameAccount == null) {					
			// check total bet and available balance.
			totalBet = gameRequest.getPlay().getStake();

			// debit
			isSuccess = transaction(totalBet,playerId,cashType,PLATFORM_ID,cookie,false);
			if(!isSuccess) {
				LOGGER.info("debit transaction failed.");
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_DEBIT_FAILED, ExceptionMessage.ERROR_MSG_DEBIT_FAILED);
			}

			// spin, Rng
			GameLogic logic = new GameLogic();
			drawn = logic.spin((byte)0, false, (byte)0, (byte)36);
			BetState betState = logic.calculateWinnings(drawn,gameRequest.getPlay().getBetState());

			// calculate total winnings
			for (Bet bet : betState.getBet()) {
				totalWinnings += bet.getWinnings();
			}
			BigDecimal bd = new BigDecimal(Float.toString(totalWinnings));
			bd = bd.setScale(4, RoundingMode.HALF_UP);
			totalWinnings = bd.floatValue();

			// gameAccount, save to db
			gameAccount = new GtGameAccount(playerId, 1, new Timestamp(System.currentTimeMillis()));
			gameAccount.setBetAmount(totalBet);
			gameAccount.setWinnings(totalWinnings);
			gameAccount.setDraw(drawn);
			gtGameAccountServiceImpl.saveGameAccount(gameAccount);
			gameId = gameAccount.getId();

			// save player bets to db.
			GtPlayerBets playerBet;
			for(Bet bet : gameRequest.getPlay().getBetState().getBet()) {
				playerBet = new GtPlayerBets(gameId, playerId, bet.getName(), bet.getStake(), bet.getSeln());
				gtPlayerBetsServiceImpl.savePlayerBet(playerBet);
			}

			// credit
			if(totalWinnings > 0) {
				isSuccess = transaction(totalWinnings,playerId,cashType,PLATFORM_ID,cookie,true);
				if(!isSuccess) {
					// TODO - handle credit transaction failed.
				}
			}

			ResultInfo resultInfo = new ResultInfo(totalBet,totalWinnings,cashType,drawn);

			// prepare gameObject to return. set gameAccount, gameId, playerId,userBalance betState, ResultInfo
			gameObj = new GameObject(gameId,playerId);
			gameObj.setBetState(betState);
			gameObj.setResultInfo(resultInfo);
		}else {
			// send previous spin response
			gameId = gameAccount.getId();
			gameObj = new GameObject(gameId,playerId,realBalance);
			gameObj.setBetState(null);
			ResultInfo resultInfo = new ResultInfo(gameAccount.getBetAmount(),gameAccount.getWinnings(),cashType,gameAccount.getDraw());
			gameObj.setResultInfo(resultInfo);
			List<GtPlayerBets> bets = gtPlayerBetsServiceImpl.findAllByGameId(gameAccount.getId(), playerId);
			BetState betState = new BetState();
			betState.setDrawn(gameAccount.getDraw());
			betState.setTotalBets((byte) bets.size());
			for(GtPlayerBets playerBet : bets) {
				betState.getBet().add(new Bet(playerBet.getBetName(), playerBet.getStake(), playerBet.getSelection()));
			}
			GameLogic logic = new GameLogic();
			betState = logic.calculateWinnings((byte)gameAccount.getDraw(), betState);
			gameObj.setBetState(betState);
		}
		// update player's real and dummy balance
		Map<String, Object> userBalance = getUserBalance(cookie);
		if(userBalance != null) {
			realBalance = (Double) userBalance.get("real_amount");
			dummyBalance = (Double) userBalance.get("dummy_amount");

		}
		gameObj.setRealBalance(realBalance);
		gameObj.setDummyBalance(dummyBalance);

		return gameObj;

	}

	public GameObject close(GameRequest gameRequest) {
		double realBalance = 0;
		double dummyBalance = 0;
		int playerId = 0;

		String cookie = gameRequest.getHeader().getCustomer().getCookie();
		HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
		requestesParameters.put("session_key", cookie);
		String url = config.getApiBaseUrl() + UrlCall.API_MY_ACCOUNT;
		String response = "";

		try {
			response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, e.getMessage());
		}

		if (response != null) {
			try {
				Map<String, Object> value = new Gson().fromJson(
						response, new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				LOGGER.info("my account api response :  "+value);
				Map<String, Object> data = new Gson().fromJson(
						value.get("data").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				Map<String, Object> userBalance = new Gson().fromJson(
						data.get("user_balance").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				if(data.containsKey("user_details")) {
					Map<String, Object> userDetails = new Gson().fromJson(
							data.get("user_details").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
							);

					Double userId = (Double) userDetails.get("user_id");
					playerId = userId.intValue();

				}
				realBalance = (Double) userBalance.get("real_amount");
				dummyBalance = (Double) userBalance.get("dummy_amount");
				System.out.println("init - player id - " + playerId + " .realBalance - " + realBalance + " .dummyBalanace - " + dummyBalance);
			} catch (Exception e) {
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " error decoding api response" );
			}
		}else {
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " session key expired.");
		}

		// change status of game account to close
		int gameId = closeGame(playerId);
		GameObject gameObj = new GameObject(gameId, playerId);
		gameObj.setRealBalance(realBalance);
		gameObj.setDummyBalance(dummyBalance);
		return gameObj;

	}

	public GameObject freezeBets(GameRequest gameRequest) {
		double realBalance = 0;
		double dummyBalance = 0;
		int playerId = 0;
		int cashType = 0;
		String cookie = gameRequest.getHeader().getCustomer().getCookie();
		HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
		requestesParameters.put("session_key", cookie);
		String url = config.getApiBaseUrl() + UrlCall.API_MY_ACCOUNT;
		String response = "";

		try {
			response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, e.getMessage());
		}

		if (response != null) {
			try {
				Map<String, Object> value = new Gson().fromJson(
						response, new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				LOGGER.info("my account api response :  "+value);
				Map<String, Object> data = new Gson().fromJson(
						value.get("data").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				Map<String, Object> userBalance = new Gson().fromJson(
						data.get("user_balance").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				if(data.containsKey("user_details")) {
					Map<String, Object> userDetails = new Gson().fromJson(
							data.get("user_details").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
							);

					Double userId = (Double) userDetails.get("user_id");
					playerId = userId.intValue();

				}
				realBalance = (Double) userBalance.get("real_amount");
				dummyBalance = (Double) userBalance.get("dummy_amount");
				System.out.println("init - player id - " + playerId + " .realBalance - " + realBalance + " .dummyBalanace - " + dummyBalance);
			} catch (Exception e) {
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " error decoding api response" );
			}
		}else {
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " session key expired.");
		}

		// find next spin
		GtSpins nextSpin = findNextSpin();
		GameObject gameobj = null;
		if(nextSpin != null) {
			long spinId = (int) nextSpin.getId();
			// calculate total bet
			GtSpinBets gtSpinBets;
			float totalBet = 0f;
			for(Bet bet : gameRequest.getPlay().getBetState().getBet()) {
				totalBet += bet.getStake();
			}
			gameobj = new GameObject();
			gameobj.setSpinId(spinId);
			// debit
			boolean isSuccess = transaction(totalBet,playerId,cashType,PLATFORM_ID,cookie,false);
			if(!isSuccess) {
				LOGGER.info("debit transaction failed.");
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_DEBIT_FAILED, ExceptionMessage.ERROR_MSG_DEBIT_FAILED);
			}
			
			// save player bets to db.
			for(Bet bet : gameRequest.getPlay().getBetState().getBet()) {
				gtSpinBets = new GtSpinBets(spinId, playerId, bet.getName(), bet.getStake(), bet.getSeln());
				gtSpinBetsService.saveSpinBets(gtSpinBets);
			}

		}

		return gameobj;
	}

	private int getPlayerIdFromCookie(String cookie) {
		String response = null;
		int playerId = 0;
		HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
		String url = config.getApiBaseUrl() + UrlCall.API_MY_ACCOUNT;
		requestesParameters.put("session_key", cookie);
		try {
			response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, e1.getMessage());
		};

		// get player id
		if (response != null) {
			try {
				Map<String, Object> value = new Gson().fromJson(
						response, new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				LOGGER.info("my account api response :  "+value);
				Map<String, Object> data = new Gson().fromJson(
						value.get("data").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				if(data.containsKey("user_details")) {
					Map<String, Object> userDetails = new Gson().fromJson(
							data.get("user_details").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
							);

					Double userId = (Double) userDetails.get("user_id");
					playerId = userId.intValue();

				}
				System.out.println("init - player id - " + playerId);
				return playerId;
			} catch (Exception e) {
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " error decoding api response" );
			}
		}else {
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " session key invalid.");
		}
	}

	private Map<String, Object> getUserBalance(String cookie) {
		String response = null;
		HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
		String url = config.getApiBaseUrl() + UrlCall.API_MY_ACCOUNT;
		requestesParameters.put("session_key", cookie);
		try {
			response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, e.getMessage());
		}
		if (response != null) {
			try {
				Map<String, Object> value = new Gson().fromJson(
						response, new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				LOGGER.info("my account api response :  "+value);
				Map<String, Object> data = new Gson().fromJson(
						value.get("data").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				Map<String, Object> userBalance = new Gson().fromJson(
						data.get("user_balance").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				if(data.containsKey("user_details")) {
					Map<String, Object> userDetails = new Gson().fromJson(
							data.get("user_details").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
							);

					Double userId = (Double) userDetails.get("user_id");

				}
				return userBalance;
			} catch (Exception e) {
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " error decoding api response" );
			}
		}else {
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " session key expired.");
		}
	}


	private boolean transaction(float amount,int playerId, int platform, int cashType, String sessionKey, boolean isCredit) {
		try {
			HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
			requestesParameters.put("cash_type", cashType);
			requestesParameters.put("plateform",platform);
			requestesParameters.put("amount", amount);
			String url = config.getApiBaseUrl();
			String response;
			if(isCredit) {
				url = url + UrlCall.API_DEPOSIT;
				requestesParameters.put("source", 3);				// join game
				requestesParameters.put("source_id",0);
				requestesParameters.put("user_id", playerId);
				response = webServiceCalling.sendPost(requestesParameters, url, SERVER_PRIVATE_KEY, true);
			}else{
				url = url + UrlCall.API_WITHDRAW;
				requestesParameters.put("source", 1);				// game won
				requestesParameters.put("source_id",0);
				requestesParameters.put("session_key", sessionKey);
				response = webServiceCalling.sendPost(requestesParameters, url, sessionKey, false);
			}
			if(response==null) {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * @param playerId
	 * @return the last simple roulette game that player was part of..
	 */
	private GtGameAccount getLastActiveGame(int playerId) {
		// TODO Auto-generated method stub
		System.out.println("finding last active game for player id " + playerId);
		return gtGameAccountServiceImpl.findActiveGameByPlayerId(playerId);
	}

	/**
	 * @param playerId
	 * @return the last time-based roulette game that player was part of..
	 */
	private GtGameAccount getLastSpinGame(int playerId) {
		// TODO Auto-generated method stub
		System.out.println("finding last active time based roulette game for player id " + playerId);
		return gtGameAccountServiceImpl.findActiveSpinGameByPlayerId(playerId);
	}

	/**
	 * @return list of the last 5 numbers drawn after spinning the wheel.
	 */
	private List<Integer> getLastDraws() {
		// TODO Auto-generated method stub
		List<GtGameAccount> games = gtGameAccountServiceImpl.findRecentGames();
		List<Integer> draws = new ArrayList<Integer>();
		for(GtGameAccount game : games) {
			draws.add(game.getDraw());
		}
		return draws;
	}

	private int closeGame(int playerId) {
		// TODO Auto-generated method stub
		GtGameAccount gameAccount = getLastActiveGame(playerId);
		System.out.println("close game");
		if(gameAccount != null) {
			System.out.println(gameAccount.toString());
			gameAccount.setState(0);		// 0 - close
			gtGameAccountServiceImpl.updateGameAccount(gameAccount);
			return gameAccount.getId();
		}
		return -1;
	}
	/**
	 * finds the next scheduled spin.
	 * @return GtSpins object containing spintTime and spinId. 
	 */
	public GtSpins findNextSpin() {
		return gtSpinsServiceImpl.findNextSpin();
	}

	public void generateSpinResult() {
		try {
			int cashType = 0;
			// find scheduled spin with spinId from database
			GtSpins currentSpin = findNextSpin();
			long spinId = 0;

			if(currentSpin != null) {
				spinId = currentSpin.getId();
				System.out.println("spin id" + spinId);
			}else {
				return;
			}
			// draw a number using gamelogic.spin()
			GameLogic gameLogic = new GameLogic();
			int drawNumber = gameLogic.spin((byte)0, false, (byte)0, (byte)36);

			// save number drawn to db in table gtSpins
			currentSpin.setDraw(drawNumber);
			currentSpin.setStatus(1);
			gtSpinsServiceImpl.updateSpinSchedule(currentSpin);


			// read all bets freezed by players on this spin Id from gtSpinBets
			List<GtSpinBets> spinBEts = gtSpinBetsService.findBets(spinId);
			System.out.println("reading bets - num bets " + spinBEts.size());
			long playerId = 0;
			BetState betState = null;
			HashMap<Long, BetState> playerBetStateMap = new HashMap<Long,BetState>();
			for(GtSpinBets sb : spinBEts) {
				System.out.println(sb.toString());
				playerId = sb.getPlayerId();
				if(!playerBetStateMap.containsKey(playerId)) {
					betState = new BetState();
					System.out.println("new bet state created " + playerBetStateMap.keySet().size());
					betState.setDrawn(currentSpin.getDraw());
					betState.setNumBets((byte) 0);
					playerBetStateMap.put(playerId, betState);
				}
				betState.setNumBets((byte) (betState.getNumBets() + 1));
				betState.getBet().add(new Bet(sb.getBetName(), sb.getStake(), sb.getSelection()));
			}

			// calculate winnings of each player and create a game account for each of them and save to db.
			BetState playerBetState;
			BetState winningBetState;
			for(long pId : playerBetStateMap.keySet()) {
				playerBetState = playerBetStateMap.get(pId);
				System.out.println(playerBetState.toString());
				GameLogic logic = new GameLogic();
				winningBetState = logic.calculateWinnings((byte) currentSpin.getDraw(), playerBetState);
				Float totalWinnings = 0f;
				Float totalStake = 0f;
				// calculate total winnings
				for (Bet bet : winningBetState.getBet()) {
					totalWinnings += bet.getWinnings();
					totalStake += bet.getStake();
				}
				BigDecimal bd = new BigDecimal(Float.toString(totalWinnings));
				bd = bd.setScale(4, RoundingMode.HALF_UP);
				totalWinnings = bd.floatValue();
				
				// credit winning amount to player's account
				if(totalWinnings > 0){
					boolean isSuccess = transaction(totalWinnings,(int)playerId,cashType ,PLATFORM_ID,"",true);
					if(!isSuccess) {
						LOGGER.info("debit transaction failed.");
						throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_DEBIT_FAILED, ExceptionMessage.ERROR_MSG_DEBIT_FAILED);
					}
				}
				
				// gameAccount, save to db
				GtGameAccount gameAccount = new GtGameAccount((int)pId, 1, new Timestamp(System.currentTimeMillis()));
				gameAccount.setBetAmount(totalStake);
				gameAccount.setWinnings(totalWinnings);
				gameAccount.setDraw(currentSpin.getDraw());
				gameAccount.setSpinId(spinId);
				System.out.println(gameAccount.toString());
				gtGameAccountServiceImpl.saveGameAccount(gameAccount);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public GameObject getSpinResult(GameRequest gameRequest) {
		// TODO Auto-generated method stub
		String cookie = gameRequest.getHeader().getCustomer().getCookie();
		int playerId = getPlayerIdFromCookie(cookie);

		double realBalance = 0;
		double dummyBalance = 0;
		try {
			cookie = gameRequest.getHeader().getCustomer().getCookie();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_MALFORMED_XML, ExceptionMessage.ERROR_MSG_MALFORMED_XML);
		}
		HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
		requestesParameters.put("session_key", cookie);
		String url = config.getApiBaseUrl() + UrlCall.API_MY_ACCOUNT;
		String response = null;
		try {
			response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, e.getMessage());
		}

		if (response != null) {
			try {
				Map<String, Object> value = new Gson().fromJson(
						response, new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				LOGGER.info("my account api response :  "+value);
				Map<String, Object> data = new Gson().fromJson(
						value.get("data").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				Map<String, Object> userBalance = new Gson().fromJson(
						data.get("user_balance").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
						);
				if(data.containsKey("user_details")) {
					Map<String, Object> userDetails = new Gson().fromJson(
							data.get("user_details").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
							);

					Double userId = (Double) userDetails.get("user_id");
					playerId = userId.intValue();

				}
				realBalance = (Double) userBalance.get("real_amount");
				dummyBalance = (Double) userBalance.get("dummy_amount");
				System.out.println("spin result - player id - " + playerId + " .realBalance - " + realBalance + " .dummyBalanace - " + dummyBalance);
			} catch (Exception e) {
				throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " error decoding api response" );
			}
		}else {
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_API_ERROR, ExceptionMessage.ERROR_MSG_API_ERROR + " session key expired." );
		}


		GtGameAccount lastGame = getLastSpinGame(playerId);						// get last game not closed.
		GameObject gameObj = new GameObject();
		gameObj.setUserId(playerId);

		List<Integer> lastDraws = getLastDraws();
		gameObj.setLastDraws(lastDraws);
		if(lastGame==null) {
			gameObj.setResultInfo(null);
		}else {
			ResultInfo resultInfo = new ResultInfo(lastGame.getBetAmount(), lastGame.getWinnings(),null,lastGame.getDraw());
			gameObj.setResultInfo(resultInfo);
			// TODO : add betState of that game.
			List<GtSpinBets> bets = gtSpinBetsService.findBetsBySpinIdPlayerId(lastGame.getSpinId(), lastGame.getPlayerId());
			BetState betState = new BetState();
			betState.setDrawn(lastGame.getDraw());
			betState.setTotalBets((byte) bets.size());
			for(GtSpinBets playerBet : bets) {
				betState.getBet().add(new Bet(playerBet.getBetName(), playerBet.getStake(), playerBet.getSelection()));
			}
			GameLogic logic = new GameLogic();
			betState = logic.calculateWinnings((byte)lastGame.getDraw(), betState);
			gameObj.setBetState(betState);
		}
		gameObj.setSpinId(lastGame.getSpinId());

		// set player's balance
		gameObj.setRealBalance(realBalance);
		gameObj.setDummyBalance(dummyBalance);
		return gameObj;

	}


}
