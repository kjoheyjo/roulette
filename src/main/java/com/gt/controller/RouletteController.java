package com.gt.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gt.logic.GameLogic;
import com.gt.request.GameRequest;
import com.gt.hibernate.GtBets;
import com.gt.hibernate.GtGameAccount;
import com.gt.hibernate.GtGameConfig;
import com.gt.services.GtBetsServiceImpl;
import com.gt.services.GtGameAccountServiceImpl;
import com.gt.services.GtGameConfigServiceImpl;
import com.gt.beans.ExceptionManagement;
import com.gt.beans.ExceptionMessage;
import com.gt.beans.GameObject;
import com.gt.beans.RouletteEngine;
import com.gt.beans.UrlCall;
import com.gt.services.WebServiceCalling;

import generated.Account;
import generated.Bet;
import generated.BetDef;
import generated.BetName;
import generated.BetPayout;
import generated.BetSeln;
import generated.BetState;
import generated.BonusPromotion;
import generated.BonusPromotionInit;
import generated.Close;
import generated.Customer;
import generated.FreeBetSummary;
import generated.GameConfig;
import generated.GameConfigSection;
import generated.GameId;
import generated.GameResponse;
import generated.Header;
import generated.Init;
import generated.Play;
import generated.Stats;
import generated.StatsAttribute;
import generated.Theme;

import org.apache.log4j.Logger;

@EnableWebMvc
@RestController
public class RouletteController {

	private static final Logger LOGGER = Logger.getLogger(RouletteController.class);
	private GtBetsServiceImpl gtBetsServiceImpl;
	private GtGameAccountServiceImpl gtGameAccountServiceImpl;
	private GtGameConfigServiceImpl gtGameConfigServiceImpl;
	private WebServiceCalling webServiceCalling;
	private RouletteEngine rouletteEngine;
	//private final static String API_BASE_URL = "http://192.168.0.51:8090/gt_games_platform/";//"http://35.154.245.194/";
	@Autowired
	public void setWebServiceCalling(WebServiceCalling webServiceCalling) {
		this.webServiceCalling = webServiceCalling;
	}

	@Autowired(required=false)
	public void setGtGameConfigServiceImpl(GtGameConfigServiceImpl gtGameConfigServiceImpl) {
		this.gtGameConfigServiceImpl = gtGameConfigServiceImpl;
	}

	@Autowired(required=false)
	public void setGtBetsServiceImpl(GtBetsServiceImpl gtBetsServiceImpl) {
		this.gtBetsServiceImpl = gtBetsServiceImpl;
	}

	@Autowired(required=false)
	public void setGtGameAccountServiceImpl(GtGameAccountServiceImpl gtGameAccountServiceImpl) {
		this.gtGameAccountServiceImpl = gtGameAccountServiceImpl;
	}

	@Autowired(required=false)
	public void setRouletteEngine(RouletteEngine rouletteEngine) {
		this.rouletteEngine = rouletteEngine;
	}

	@CrossOrigin()
	@RequestMapping(value= "/init",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse testInit( @RequestBody(required = true) GameRequest gameRequest,HttpServletRequest req) {
		try {
			LOGGER.info("init call");
			System.out.println("init call");
			printRequestInfo(req);

			GameObject gameObj = rouletteEngine.init(gameRequest);
			GameResponse gameResponse = new GameResponse();

			buildHeader(gameResponse,gameRequest.getHeader());	
			buildInit(gameResponse);					
			buildStats(gameResponse,gameObj);		
			FreeBetSummary freeBetSummary = new FreeBetSummary(gameObj.getDummyBalance(),1);
			gameResponse.getInit().setFreebetSummary(freeBetSummary);
			gameResponse.getHeader().getCustomer().getAccount().setBalance(gameObj.getRealBalance());

			if(gameObj.getResultInfo()!=null) {
				// append spin response
				Play play = new Play();
				play.setStake(gameObj.getResultInfo().getStake());
				play.setWin(gameObj.getResultInfo().getWinnings());
				play.setBetState(gameObj.getBetState());

				BonusPromotion bonusPromotion = new BonusPromotion();
				bonusPromotion.setBonusName("BonusBar");
				bonusPromotion.setBonusType("BBAR");
				bonusPromotion.setEarnedPcnt(0.00f);
				bonusPromotion.setAwardTarget((byte) 100);
				bonusPromotion.setPointsEarned((byte) 0);
				play.setBonusPromotion(bonusPromotion);

				gameResponse.setPlay(play);
			}

			return gameResponse;
		} catch (ExceptionManagement e) {
			e.printStackTrace();
			System.out.println("controller exception");
			throw new ExceptionManagement(e.getErrorCode(), e.getErrorMessage());
		}
	}

	@CrossOrigin()
	@RequestMapping(value= "/spin",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse testSpin(  @RequestBody(required = true) GameRequest gameRequest) {
		LOGGER.info("spin call");
		try {
			GameObject gameObj = rouletteEngine.spin(gameRequest);

			if(gameObj != null) {
				return buildGameResponse(gameObj,gameRequest);

			}else {
				return null;
			}
		} catch (ExceptionManagement e) {
			e.printStackTrace();
			System.out.println("controller exception");
			throw new ExceptionManagement(e.getErrorCode(), e.getErrorMessage());
		}

	}

	@CrossOrigin()
	@RequestMapping(value= "/close",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse testClose(  @RequestBody(required = true) GameRequest gameRequest) {
		LOGGER.info("close call");
		GameObject gameObj = null;
		try {
			gameObj = rouletteEngine.close(gameRequest);
		} catch (ExceptionManagement e) {
			e.printStackTrace();
			System.out.println("controller exception");
			throw new ExceptionManagement(e.getErrorCode(), e.getErrorMessage());
		}
		GameResponse gameResponse = new GameResponse();
		buildHeader(gameResponse,gameRequest.getHeader());	
		gameResponse.setClose(new Close());
		gameResponse.getHeader().setGameId(gameRequest.getHeader().getGameId());
		if(gameObj != null) {
			gameResponse.getHeader().getCustomer().getAccount().setBalance(gameObj.getRealBalance());
		}
		return gameResponse;
	}
	
	@CrossOrigin
	@RequestMapping(value="/pageNotFound")
	public @ResponseBody ExceptionManagement pageNotFound() {
		throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_MALFORMED_URL, ExceptionMessage.ERROR_MSG_MALFORMED_URL);
	}

	private void buildPlay(GameResponse gameResponse, GameRequest gameRequest) {
		Play playRequest = gameRequest.getPlay();
		Float stake = playRequest.getStake();
		// INR stake - withdraw request to platform.

		// Byte numBets = gameRequest.getBetState().getNumBets();

		GameLogic logic = new GameLogic();
		byte drawn = logic.spin((byte)0, false, (byte)0, (byte)36);
		BetState responseBetState = logic.calculateWinnings(drawn,playRequest.getBetState());

		Float totalWinnings = 0.00f;
		for (Bet bet : responseBetState.getBet()) {
			totalWinnings += bet.getWinnings();
		}

		BigDecimal bd = new BigDecimal(Float.toString(totalWinnings));
		bd = bd.setScale(4, RoundingMode.HALF_UP);
		totalWinnings = bd.floatValue();

		// INR totalWinnings - deposit request to platform

		Play play = new Play();
		play.setStake(stake);
		play.setId((short) 1904);
		play.setWin(totalWinnings);
		play.setBetState(responseBetState);

		BonusPromotion bonusPromotion = new BonusPromotion();
		bonusPromotion.setBonusName("BonusBar");
		bonusPromotion.setBonusType("BBAR");
		bonusPromotion.setEarnedPcnt(0.00f);
		bonusPromotion.setAwardTarget((byte) 100);
		bonusPromotion.setPointsEarned((byte) 0);
		play.setBonusPromotion(bonusPromotion);

		gameResponse.setPlay(play);

	}

	private void buildStats(GameResponse gameResponse,final GameObject gameObj) {
		StatsAttribute statsAttribute = new StatsAttribute();
		statsAttribute.setName("Bet");
		String draws = "";
		if(!gameObj.getLastDraws().isEmpty()) {
			for(int draw : gameObj.getLastDraws()) {
				draws += draw + "|";
			}
			draws = draws.substring(0, draws.length()-1);
		}else {
			draws = "12|23|14|35|1";
		}
		statsAttribute.setValueAttribute(draws);

		Stats stats = new Stats();
		stats.setStatsAttribute(statsAttribute);
		gameResponse.setStats(stats);
	}

	private void buildInit(GameResponse gameResponse) {

		GtGameConfig config = gtGameConfigServiceImpl.findConfigByDomain("localhost");

		Init init = new Init();
		init.setMaxAnimTime((short) config.getMaxAnimationTime());
		init.setMinAnimTime((short)config.getMinAnimationTime());
		init.setMinStake((float)config.getMinStake());
		init.setMaxStake((float)config.getMaxStake());
		init.setDfltStake((float)config.getDefaultStake());
		init.setMaxWinnings((float)config.getMaxWinnings());
		init.setStakeIncr("0.01|0.05|0.2|1.0|5.0|20.0|100.0");
		init.setNew("Yes");
		init.setStake((float)0.00);
		init.setStakePerLine((float)0.00);
		init.setWin((float)0.00);
		init.setId((byte)0);

		// BetDef
		BetDef betDef = new BetDef();
		betDef.setMinNumber((byte)config.getMinNumber());
		betDef.setMaxNumber((byte)config.getMaxNumber());
		betDef.setTotalDrawn((byte) config.getTotalDraws());
		betDef.setDrawDuplicates(String.valueOf("1".equals(config.getDrawDuplicates())));
		betDef.setNumDrawSets((byte) 1);
		betDef.setUseLaPartage("false");
		betDef.setMaxGroups((byte) 1);
		init.setBetDef(betDef);

		// BetPayout
		BetPayout betPayout = new BetPayout();
		betPayout.setTotal((byte)121);

		String betType = "";
		List<GtBets> bets = gtBetsServiceImpl.findAllBetsByConfigId(config.getGameConfigId());
		for(BetName betName : BetName.values()){
			betType = betName.name().split("_")[0];
			BetSeln betSeln = new BetSeln();
			betSeln.setLaPartageSeln("");
			betSeln.setLifetimeType("T");
			betSeln.setType("S");
			betSeln.setPayoutIncr((float) 0.00);
			betSeln.setMthAct("T");
			betSeln.setMthNo((byte) 1);
			betSeln.setSeln(betName.toString());
			betSeln.setName(betName.name());
			for(GtBets bet : bets) {
				if(betType.equals(bet.getBetType())) {
					betSeln.setMaxStake(bet.getMaxStakeValue());
					betSeln.setMinStake(bet.getMinStakeValue());
					betSeln.setPayout(bet.getPayout());
				}
			}

			betPayout.getBetSeln().add(betSeln);
		}
		init.setBetPayout(betPayout);

		FreeBetSummary freeBetSummary = new FreeBetSummary();		// FreeBetSummary
		freeBetSummary.setAvailableBalance((double) 64.00);
		freeBetSummary.setNumTokens((byte) 1);
		init.setFreebetSummary(freeBetSummary);

		BonusPromotionInit bonusPromotion = new BonusPromotionInit();		// BonusPromotion
		bonusPromotion.setBonusName("BonusBar");
		bonusPromotion.setBonusType("BBAR");
		bonusPromotion.setEarnedPcnt((float) 0.0);
		bonusPromotion.setAwardTarget((byte) 100);
		bonusPromotion.setPointsEarned((byte) 0);
		init.setBonusPromotion(bonusPromotion);

		GameConfig gameConfig = new GameConfig();							// GameConfig
		GameConfigSection gameConfigSection = new GameConfigSection();
		gameConfigSection.setId((byte) 1);
		gameConfigSection.setName("Theme Configuration");
		gameConfigSection.setAllowedActions("SetDefault");

		Theme theme1 = new Theme();
		Theme theme2 = new Theme();
		Theme theme3 = new Theme();

		theme1.setName("Futuristic");		// theme 1
		theme1.setSelected("NO");
		theme2.setName("Nautical");			// theme 2
		theme2.setSelected("NO");	
		theme3.setName("Historical");		// theme 3
		theme3.setSelected("NO");

		gameConfigSection.getTheme().add(theme1);
		gameConfigSection.getTheme().add(theme2);
		gameConfigSection.getTheme().add(theme3);
		gameConfig.getGameConfigSection().add(gameConfigSection);
		init.setGameConfig(gameConfig);

		gameResponse.setInit(init);
	}

	private void buildHeader(GameResponse gameResponse, final Header requestHeader) {
		Header header = new Header();

		Header responseHeader = new Header();		// create reponse header
		//<GameId id='303' ver='1' channel='I' />
		GameId gameId= new GameId();
		gameId.setId(303);
		gameId.setChannel("I");
		gameId.setVer((byte)1);
		responseHeader.setGameId(gameId);

		Customer customer = new Customer();			// set Customer in repsponse header
		Account account = new Account();
		account.setAdjustedFreeBalance("No");
		account.setCcyCode("INR");
		account.setCcyDecimalSeparator(".");
		account.setCcyThousandSeparator(",");
		account.setHeldFunds((float) 00.00);
		//account.setBalance(100000);
		customer.setAccount(account);
		responseHeader.setCustomer(customer);
		gameResponse.setHeader(responseHeader);

	}

	private GameResponse buildGameResponse(GameObject gameObj, final GameRequest gameRequest) {
		GameResponse gameResponse = new GameResponse();
		Header header = new Header();
		GameId gameId = new GameId(gameObj.getGameId(),gameRequest.getHeader().getGameId().getVer(),
				gameRequest.getHeader().getGameId().getChannel());

		Customer customer = new Customer();				
		Account account = new Account();
		account.setAdjustedFreeBalance("No");
		account.setCcyCode("INR");
		account.setCcyDecimalSeparator(".");
		account.setCcyThousandSeparator(",");
		account.setBalance(gameObj.getRealBalance());
		customer.setAccount(account);

		header.setCustomer(customer);
		header.setGameId(gameId);
		gameResponse.setHeader(header);

		Play play = new Play();
		play.setStake(gameObj.getResultInfo().getStake());
		play.setWin(gameObj.getResultInfo().getWinnings());
		play.setBetState(gameObj.getBetState());

		BonusPromotion bonusPromotion = new BonusPromotion();
		bonusPromotion.setBonusName("BonusBar");
		bonusPromotion.setBonusType("BBAR");
		bonusPromotion.setEarnedPcnt(0.00f);
		bonusPromotion.setAwardTarget((byte) 100);
		bonusPromotion.setPointsEarned((byte) 0);
		play.setBonusPromotion(bonusPromotion);

		FreeBetSummary freeBetSummary = new FreeBetSummary(gameObj.getDummyBalance(),1);
		play.setFreebetSummary(freeBetSummary);

		gameResponse.setPlay(play);

		return gameResponse;
	}

	private Account getAccountFromCookie(String cookie) {
		// TODO Auto-generated method stub
		Account account = new Account();
		account.setAdjustedFreeBalance("No");
		account.setCcyCode("USD");
		account.setCcyDecimalSeparator(".");
		account.setBalance((float)9090);
		account.setCcyThousandSeparator(",");
		account.setHeldFunds((float) 22.00);
		return account;
	}

	private Short getIdFromCookie(String cookie) {
		// TODO Auto-generated method stub
		return 1001;
	}

	private void checkCookie(String cookie) {/*
		try {
			// TODO Auto-generated method stub
			HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
			String url = API_BASE_URL + UrlCall.API_MY_ACCOUNT;
			String response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);

			if (response != null) {
				// Get LAst game details if not spain than init else send response of spin
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMessage.ERROR_CODE_INTERNAL_SERVER,ExceptionMessage.ERROR_MSG_INTERNAL_SERVER);

		}

	*/}

	@RequestMapping(value = "/data", method = RequestMethod.POST, consumes = "application/xml")
	public @ResponseBody GameResponse getData(@RequestBody(required = true) Header header) {
		System.out.println("data test");

		List<GtBets> bets = gtBetsServiceImpl.findAllBets();
		GtGameConfig configs = gtGameConfigServiceImpl.findByGameConfigId(1);
		gtGameAccountServiceImpl.saveGameAccount(new GtGameAccount(1, 10, 1,new Timestamp(0),new Timestamp(1)));

		for(GtBets bet : bets) {
			System.out.println(bet.toString());
		}
		System.out.println(configs.toString());
		GameResponse game = new GameResponse();
		return game;
	}

	private void printRequestInfo(HttpServletRequest req) {

		StringBuffer requestURL = req.getRequestURL();
		String queryString = req.getQueryString();
		if (queryString == null) {
			LOGGER.info("url: " + requestURL.toString());
			System.out.println("url: " + requestURL.toString());
		} else {
			LOGGER.info("url: " + requestURL.append('?').append(queryString).toString());
			System.out.println("url: " + requestURL.append('?').append(queryString).toString());
		}
		LOGGER.info( "method:" + req.getMethod());
		System.out.println( "method:" + req.getMethod());
		Enumeration headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = (String)headerNames.nextElement();
			LOGGER.info("header: " + headerName + ":" + req.getHeader(headerName));
			System.out.println("header: " + headerName + ":" + req.getHeader(headerName));
		}
		Map<String, String[]> params = req.getParameterMap();
		for(Entry<String, String[]> s : params.entrySet()){
			String paramName = "";
			LOGGER.info("Attribute: '"+paramName+"', Value: '"+req.getParameter(paramName) + "'");
			System.out.println("Attribute: '"+paramName+"', Value: '"+req.getParameter(paramName) + "'");
		}
	}

	@CrossOrigin()
	@RequestMapping(value= "/testInit",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getInit( @RequestBody(required = true) GameRequest gameRequest,HttpServletRequest req) {
		System.out.println("init call");
		printRequestInfo(req);
//		String cookie = "98e34d690cd6bb9c469a14483007e74e";
//		checkCookie(cookie);
		GameResponse gr = new GameResponse();
		buildHeader(gr, gameRequest.getHeader());
		buildInit(gr);
		buildStats(gr,new GameObject(11, 123));

		return gr;
	}
	
	/*
	@CrossOrigin()
	@RequestMapping(value= "/spin",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getSpin( @RequestBody(required = true) GameRequest gameRequest) {
		logger.info("spin call");
		GameResponse gr = new GameResponse();
		try {

		//GameObject gameObj = rouletteEngine.spin(gameRequest);

		buildHeader(gr,gameRequest.getHeader());		
		buildPlay(gr,gameRequest);			
		gr.getHeader().getCustomer().getAccount().setBalance((float) 9078.00);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gr;
	}

	@CrossOrigin()
	@RequestMapping(value= "/close",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getClose( @RequestBody(required = true) GameRequest gameRequest) {
		logger.info("close call");
		//GameObject gameObj = rouletteEngine.close(gameRequest);
		GameResponse gr = new GameResponse();
		buildHeader(gr,gameRequest.getHeader());	
		gr.getHeader().getCustomer().getAccount().setBalance((float) 9078.00);
		gr.setClose(new Close());

		return gr;
	}*/

}