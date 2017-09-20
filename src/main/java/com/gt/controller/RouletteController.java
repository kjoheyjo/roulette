package com.gt.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gt.logic.GameLogic;
import com.gt.request.GameRequest;

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
import generated.FreeBetSummaryInit;
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


@EnableWebMvc
@RestController
public class RouletteController {

	//@RequestParam("initXml")
	@RequestMapping(value= "/init",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getInit(  @RequestBody(required = true) GameRequest gameRequest) {
		Header requestHeader = gameRequest.getHeader();
		GameResponse gameResponse = new GameResponse();

		// prepare header in gameResponse
		buildHeader(gameResponse,requestHeader);
		// prepare init in gameResponse
		buildInit(gameResponse);
		// prepare stats in gameResponse
		buildStats(gameResponse);

		return gameResponse;
	}
	
	@RequestMapping(value= "/spin",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getSpin(  @RequestBody(required = true) GameRequest gameRequest) {
		Header requestHeader = gameRequest.getHeader();
		GameResponse gameResponse = new GameResponse();
		
		// prepare header in gameResponse
		buildHeader(gameResponse,requestHeader);
		
		// prepare play in gameResponse
		buildPlay(gameResponse,gameRequest);
		
		return gameResponse;
	}
	
	@RequestMapping(value= "/close",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse close(  @RequestBody(required = true) GameRequest gameRequest) {
		Header requestHeader = gameRequest.getHeader();
		GameResponse gameResponse = new GameResponse();
		
		// prepare header in gameResponse
		buildHeader(gameResponse,requestHeader);
		gameResponse.setClose(new Close());
		
		return gameResponse;
	}
		
	private void buildPlay(GameResponse gameResponse, GameRequest gameRequest) {
		Play playRequest = gameRequest.getPlay();
		Float stake = playRequest.getStake();
		//Byte numBets = gameRequest.getBetState().getNumBets();
		
		GameLogic logic = new GameLogic();
		byte drawn = logic.spin((byte)0, false, (byte)1, (byte)36);
		BetState responseBetState = logic.calculateWinnings(drawn,playRequest.getBetState());
		
		Float totalWinnings = 0.00f;
		for(Bet bet : responseBetState.getBet()) {
			totalWinnings += bet.getWinnings();
		}
		
		BigDecimal bd = new BigDecimal(Float.toString(totalWinnings));
	    bd = bd.setScale(4, RoundingMode.HALF_UP);
	    totalWinnings = bd.floatValue();
		
		Play play = new Play();
		play.setStake(stake);
		play.setId((short) 1904);
		play.setWin(totalWinnings);
		play.setBetState(responseBetState);
		play.setStake(stake);
		
		BonusPromotion bonusPromotion = new BonusPromotion();
		bonusPromotion.setBonusName("BonusBar");
		bonusPromotion.setBonusType("BBAR");
		bonusPromotion.setEarnedPcnt(0.00f);
		bonusPromotion.setAwardTarget((byte)100);
		bonusPromotion.setPointsEarned((byte)0);
		play.setBonusPromotion(bonusPromotion);
		
		gameResponse.setPlay(play);
		
	}


	private void buildStats(GameResponse gameResponse) {
		StatsAttribute statsAttribute = new StatsAttribute();
		statsAttribute.setName("Bet");
		statsAttribute.setValueAttribute("19|4|36|27");
		
		Stats stats = new Stats();
		stats.setStatsAttribute(statsAttribute);
		gameResponse.setStats(stats);
	}


	private void buildInit(GameResponse gameResponse) {
		Init init = new Init();
		init.setMaxAnimTime((short) 30000);
		init.setMinAnimTime((short)1000);
		init.setMinStake((float)1);
		init.setMaxStake((float)1000);
		init.setDfltStake((float)1);
		init.setMaxWinnings((float)10);
		init.setStakeIncr("0.01|0.05|0.2|1.0|5.0|20.0|100.0");
		init.setNew("Yes");
		init.setStake((float)0.00);
		init.setStakePerLine((float)0.00);
		init.setWin((float)0.00);
		init.setId((byte)0);
		
		// BetDef
		BetDef betDef = new BetDef();
		betDef.setMinNumber((byte)1);
		betDef.setMaxNumber((byte)36);
		betDef.setTotalDrawn((byte) 1);
		betDef.setDrawDuplicates("true");
		betDef.setNumDrawSets((byte)1);
		betDef.setUseLaPartage("false");
		betDef.setMaxGroups((byte)1);
		init.setBetDef(betDef);
		
		// BetPayout
		BetPayout betPayout = new BetPayout();
		betPayout.setTotal((byte)121);
		
		for(BetName betName : BetName.values()){
			BetSeln betSeln = new BetSeln();
			betSeln.setLaPartageSeln("");
			betSeln.setLifetimeType("T");
			betSeln.setType("S");
			betSeln.setMinStake("");
			betSeln.setPayoutIncr((float)0.00);
			betSeln.setMthAct("T");
			betSeln.setMthNo((byte)1);
			betSeln.setSeln(betName.toString());
			betSeln.setName(betName.name());
			
			if(betName.toString().split("\\|").length == 1){
				betSeln.setMaxStake((float)25.00);
				betSeln.setPayout((float)36.0000);
			}else if(betName.toString().split("\\|").length == 2){
				betSeln.setMaxStake((float)50.00);
				betSeln.setPayout((float)18.00);
			}else if(betName.toString().split("\\|").length == 3){
				betSeln.setMaxStake((float)250.00);
				betSeln.setPayout((float)12.00);
			}else if(betName.toString().split("\\|").length == 4) {
				betSeln.setMaxStake((float)100.0);
				betSeln.setPayout((float)9.0000);
			}else if(betName.toString().split("\\|").length == 6){
				betSeln.setMaxStake((float)150.00);
				betSeln.setPayout((float)6.00);
			}else if(betName.toString().split("\\|").length == 12){
				betSeln.setMaxStake((float)250.00);
				betSeln.setPayout((float)3.0000);
			}else if(betName.toString().split("\\|").length == 18){
				betSeln.setMaxStake((float)500.00);
				betSeln.setPayout((float)2.00);
			}
			betPayout.getBetSeln().add(betSeln);
		}
		init.setBetPayout(betPayout);
		
		// FreeBetSummary
		FreeBetSummaryInit freeBetSummary = new FreeBetSummaryInit();
		freeBetSummary.setAvailableBalance((float) 64.00);
		freeBetSummary.setNumTokens((byte)1);
		init.setFreebetSummary(freeBetSummary);
		
		// BonusPromotion
		BonusPromotionInit bonusPromotion = new BonusPromotionInit();
		bonusPromotion.setBonusName("BonusBar");
		bonusPromotion.setBonusType("BBAR");
		bonusPromotion.setEarnedPcnt((float)0.0);
		bonusPromotion.setAwardTarget((byte)100);
		bonusPromotion.setPointsEarned((byte)0);
		init.setBonusPromotion(bonusPromotion);
		
		// GameConfig
		GameConfig gameConfig = new GameConfig();
		GameConfigSection gameConfigSection = new GameConfigSection();
		gameConfigSection.setId((byte)1);
		gameConfigSection.setName("Theme Configuration");
		gameConfigSection.setAllowedActions("SetDefault");
		
		Theme theme1 = new Theme();
		Theme theme2 = new Theme();
		Theme theme3 = new Theme();
		// theme 1
		theme1.setName("Futuristic");
		theme1.setSelected("NO");
		// theme 2
		theme2.setName("Nautical");
		theme2.setSelected("NO");
		// theme 3
		theme3.setName("Historical");
		theme3.setSelected("NO");
		
		gameConfigSection.getTheme().add(theme1);
		gameConfigSection.getTheme().add(theme2);
		gameConfigSection.getTheme().add(theme3);
		gameConfig.getGameConfigSection().add(gameConfigSection);
		init.setGameConfig(gameConfig);
		
		gameResponse.setInit(init);
	}


	private void buildHeader(GameResponse gameResponse, Header requestHeader) {
		System.out.println("========== game request received ============");
		String cookie = requestHeader.getCustomer().getCookie();
		System.out.println("cookie received " + cookie);
		
		// create reponse header
		Header responseHeader = new Header();
		
		// check cookie
		checkCookie(cookie);
		
		// get channel from  requestHeader
		if(requestHeader.getGameDetails() != null && requestHeader.getGameId() == null) {
			String channel = requestHeader.getGameDetails().getChannel();
			System.out.println("channel " + channel);
			// set GameId in response header
			GameId gameId = new GameId();
			Short id = getIdFromCookie(cookie);
			gameId.setId(id);
			gameId.setVer((byte) 1);
			gameId.setChannel(channel);
			responseHeader.setGameId(gameId);
		}else if(requestHeader.getGameId() != null) {
			responseHeader.setGameId(requestHeader.getGameId());
		}
		
		// set Customer in repsponse header
		Customer customer = new Customer();
		Account account = getAccountFromCookie(cookie);
		customer.setAccount(account);
		responseHeader.setCustomer(customer);
		gameResponse.setHeader(responseHeader);
		
	}


	private Account getAccountFromCookie(String cookie) {
		// TODO Auto-generated method stub
		Account account = new Account();
		account.setAdjustedFreeBalance("No");
		account.setCcyCode("INR");
		account.setCcyDecimalSeparator(".");
		account.setBalance((float)9090.00);
		account.setCcyThousandSeparator(",");
		account.setHeldFunds((float)22.00);
		return account;
	}


	private Short getIdFromCookie(String cookie) {
		// TODO Auto-generated method stub
		return 1001;
	}


	private void checkCookie(String cookie) {
		// TODO Auto-generated method stub
		
	}


	@RequestMapping(value= "/test",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getTest(  @RequestBody(required = true) Header test) {
		System.out.println("============== TEST =============");
		GameResponse game = new GameResponse();
		game.setHeader(test);
		System.out.println(test.getGameId().getId());
		return game;
	}

}