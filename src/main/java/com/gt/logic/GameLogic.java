package com.gt.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import generated.Bet;
import generated.BetName;
import generated.BetState;

public class GameLogic {
	
	public byte spin(byte numToDraw, boolean isBiased,byte lower, byte upper) {
		if(upper < lower) {
			return -1;
		}
		if(isBiased) {
			return numToDraw;
		}else {
			Random random = new Random();
			return (byte) (lower + (byte)random.nextInt(upper + 1));
			
		}
	}

	public BetState calculateWinnings(byte drawn, BetState requestBetState) {
		BetState responseBetState = new BetState();
		responseBetState.setTotalBets(requestBetState.getNumBets());
		responseBetState.setDrawn(drawn);
		String selection = "";
		String[] bets = null ;
		float winnings;
		boolean hit;
		for(Bet bet : requestBetState.getBet()) {
			winnings = 0.0f;
			hit = false;
			selection = BetName.valueOf(bet.getName()).toString();
			if(selection.contains("|")) {
				bets = selection.split("\\|");
			}else {
				bets = new String[1];
				bets[0] = bet.getSeln();
				System.out.println(selection);
			}
			
			for(String s : bets) {
				System.out.println(s);
				if((byte)Integer.parseInt(s) == drawn) {
					hit = true;
					break;
				}
			}
			
			if(hit){
				winnings = bet.getStake()*getPayout(bet);
				BigDecimal bd = new BigDecimal(Float.toString(winnings));
			    bd = bd.setScale(4, RoundingMode.HALF_UP);
			    winnings = bd.floatValue();
			}
			bet.setWinnings(winnings);
			responseBetState.getBet().add(bet);
		}
		return responseBetState;
	}

	private float getPayout(Bet bet) {
		System.out.println("inside get payout");
		BetName betName = BetName.valueOf(bet.getName());
		System.out.println("bet " + betName.name() + " " + betName.toString());
		if(betName.toString().split("\\|").length == 1){
			return 36.0f;
		}else if(betName.toString().split("\\|").length == 2){
			return 18.0000f;
		}else if(betName.toString().split("\\|").length == 3){
			return 12.0000f;
		}else if(betName.toString().split("\\|").length == 4) {
			return 9.0000f;
		}else if(betName.toString().split("\\|").length == 6){
			return 6.0000f;
		}else if(betName.toString().split("\\|").length == 12){
			return 3.0000f;
		}else if(betName.toString().split("\\|").length == 18){
			return 2.0000f;
		}
		return (float)0;
	}
	
	
}
