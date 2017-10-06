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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.gt.beans.ExceptionMassage;
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

import org.apache.log4j.Logger;

@EnableWebMvc
@RestController
public class RouletteController {

	private static final Logger logger = Logger.getLogger(RouletteController.class);
	private GtBetsServiceImpl gtBetsServiceImpl;
	private GtGameAccountServiceImpl gtGameAccountServiceImpl;
	private GtGameConfigServiceImpl gtGameConfigServiceImpl;
	
	
	private WebServiceCalling webServiceCalling;
	private String domainName = "http://192.168.0.51:8090/gt_games_platform/";

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

	@CrossOrigin()
	@RequestMapping(value= "/init",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getInit(  @RequestBody(required = true) GameRequest gameRequest,HttpServletRequest req) {
		logger.info("init call");
		printRequestInfo(req);
		Header requestHeader = gameRequest.getHeader();
		GameResponse gameResponse = new GameResponse();
		buildHeader(gameResponse,requestHeader);	// prepare header in gameResponse
		buildInit(gameResponse);					// prepare init in gameResponse
		buildStats(gameResponse);					// prepare stats in gameResponse
		return gameResponse;
	}

	@CrossOrigin()
	@RequestMapping(value= "/spin",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse getSpin(  @RequestBody(required = true) GameRequest gameRequest) {
		logger.info("spin call");
		Header requestHeader = gameRequest.getHeader();
		GameResponse gameResponse = new GameResponse();
		buildHeader(gameResponse,requestHeader);	// prepare header in gameResponse
		buildPlay(gameResponse,gameRequest);		// prepare play in gameResponse
		gameResponse.getHeader().getCustomer().getAccount().setBalance((float) 9078.00);

		return gameResponse;
	}

	@CrossOrigin()
	@RequestMapping(value= "/close",method = RequestMethod.POST, consumes= "application/xml")
	public @ResponseBody GameResponse close(  @RequestBody(required = true) GameRequest gameRequest) {
		logger.info("close call");
		Header requestHeader = gameRequest.getHeader();
		GameResponse gameResponse = new GameResponse();
		buildHeader(gameResponse,requestHeader);	// prepare header in gameResponse
		gameResponse.getHeader().getCustomer().getAccount().setBalance((float) 9078.00);
		gameResponse.setClose(new Close());

		return gameResponse;
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
		play.setStake(stake);

		BonusPromotion bonusPromotion = new BonusPromotion();
		bonusPromotion.setBonusName("BonusBar");
		bonusPromotion.setBonusType("BBAR");
		bonusPromotion.setEarnedPcnt(0.00f);
		bonusPromotion.setAwardTarget((byte) 100);
		bonusPromotion.setPointsEarned((byte) 0);
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

		FreeBetSummaryInit freeBetSummary = new FreeBetSummaryInit();		// FreeBetSummary
		freeBetSummary.setAvailableBalance((float) 64.00);
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

	private void buildHeader(GameResponse gameResponse, Header requestHeader) {
		System.out.println("========== game request received ============");
		String cookie = requestHeader.getCustomer().getCookie();
		System.out.println("cookie received " + cookie);

		Header responseHeader = new Header();		// create reponse header
		checkCookie(cookie);						// check cookie

		if(requestHeader.getGameDetails() != null) {		// get channel from  requestHeader
			String channel = requestHeader.getGameDetails().getChannel();
			System.out.println("channel " + channel);
			GameId gameId = new GameId();			// set GameId in response header
			Short id = getIdFromCookie(cookie);
			gameId.setId(id);
			gameId.setVer((byte) 1);
			gameId.setChannel(channel);
			responseHeader.setGameId(gameId);
		} else if (requestHeader.getGameId() != null) {
			responseHeader.setGameId(requestHeader.getGameId());
		}

		Customer customer = new Customer();			// set Customer in repsponse header
		Account account = getAccountFromCookie(cookie);
		customer.setAccount(account);
		responseHeader.setCustomer(customer);
		gameResponse.setHeader(responseHeader);

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

	private void checkCookie(String cookie) {
		try {
			// TODO Auto-generated method stub
			HashMap<String, Object> requestesParameters = new HashMap<String, Object>();
			String url = domainName + UrlCall.API_MY_ACCOUNT;
			String response = webServiceCalling.sendPost(requestesParameters, url, cookie, false);

			if (response != null) {
				// Get LAst game details if not spain than init else send response of spin
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw new ExceptionManagement(ExceptionMassage.ERROR_CODE_INTERNAL_SERVER,ExceptionMassage.ERROR_MSG_INTERNAL_SERVER);

		}

	}

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
			logger.info("url: " + requestURL.toString());
		} else {
			logger.info("url: " + requestURL.append('?').append(queryString).toString());
		}
		logger.info( "method:" + req.getMethod());
		Enumeration headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = (String)headerNames.nextElement();
			logger.info("header: " + headerName + ":" + req.getHeader(headerName));
		}
		Map<String, String[]> params = req.getParameterMap();
		for(Entry<String, String[]> s : params.entrySet()){
			String paramName = "";
			logger.info("Attribute: '"+paramName+"', Value: '"+req.getParameter(paramName) + "'");
		}
	}

}