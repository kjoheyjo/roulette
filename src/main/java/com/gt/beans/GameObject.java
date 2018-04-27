package com.gt.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import generated.BetState;

public class GameObject {
	private int gameId;
	private int userId;
	private double realBalance;
	private double dummyBalance;
	private BetState betState;
	private ResultInfo resultInfo;
	private Timestamp spinTime;
	private long spinId;
	private List<Integer> lastDraws = new ArrayList<Integer>();
	
	public GameObject(int gameId, int userId) {
		this.gameId = gameId;
		this.userId = userId;
	}
	public GameObject(int gameId, int userId, BetState betState) {
		this.gameId = gameId;
		this.userId = userId;
		this.betState = betState;
	}
	public GameObject(int gameId, int userId,double realBalance) {
		this.gameId = gameId;
		this.userId = userId;
		this.realBalance = realBalance;
	}
	public GameObject() {
		
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public BetState getBetState() {
		return betState;
	}
	public void setBetState(BetState betState) {
		this.betState = betState;
	}
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
	public double getDummyBalance() {
		return dummyBalance;
	}
	public void setDummyBalance(double dummyBalance) {
		this.dummyBalance = dummyBalance;
	}
	public double getRealBalance() {
		return realBalance;
	}
	public void setRealBalance(double realBalance) {
		this.realBalance = realBalance;
	}
	public List<Integer> getLastDraws() {
		return lastDraws;
	}
	public void setLastDraws(List<Integer> lastDraws) {
		this.lastDraws = lastDraws;
	}
	public Timestamp getSpinTime() {
		return spinTime;
	}
	public void setSpinTime(Timestamp spinTime) {
		this.spinTime = spinTime;
	}
	public long getSpinId() {
		return spinId;
	}
	public void setSpinId(long spinId) {
		this.spinId = spinId;
	}
	
}
