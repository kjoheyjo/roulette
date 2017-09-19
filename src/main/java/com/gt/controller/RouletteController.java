package com.gt.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gt.request.GameRequest;

import generated.Account;
import generated.BetDef;
import generated.Customer;
import generated.GameId;
import generated.GameResponse;
import generated.Header;
import generated.Init;


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
	
	
	private void buildStats(GameResponse gameResponse) {
		// TODO Auto-generated method stub
		
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
		
		
		
		
	}


	private void buildHeader(GameResponse gameResponse, Header requestHeader) {
		System.out.println("========== game request received ============");
		String cookie = requestHeader.getCustomer().getCookie();
		System.out.println("cookie received " + cookie);
		
		// check cookie
		checkCookie(cookie);
		
		// get channel from  requestHeader
		String channel = requestHeader.getGameDetails().getChannel();
		System.out.println("channel " + channel);
		
		// create reponse header
		Header responseHeader = new Header();
		
		// set GameId in response header
		GameId gameId = new GameId();
		Short id = getIdFromCookie(cookie);
		gameId.setId(id);
		gameId.setVer((byte) 1);
		gameId.setChannel(channel);
		responseHeader.setGameId(gameId);
		
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