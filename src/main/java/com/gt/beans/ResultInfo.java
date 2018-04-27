package com.gt.beans;

public class ResultInfo {
	private float stake;
	private float winnings;
	private Integer cashType;
	private int numDrawn;
	
	public ResultInfo(float stake, float winnings, Integer cashType, int numDrawn) {
		super();
		this.stake = stake;
		this.winnings = winnings;
		this.cashType = cashType;
		this.numDrawn = numDrawn;
	}
	public float getStake() {
		return stake;
	}
	public void setStake(float stake) {
		this.stake = stake;
	}
	public float getWinnings() {
		return winnings;
	}
	public void setWinnings(float winnings) {
		this.winnings = winnings;
	}
	public float getCashType() {
		return cashType;
	}
	public void setCashType(Integer cashType) {
		this.cashType = cashType;
	}
	public int getNumDrawn() {
		return numDrawn;
	}
	public void setNumDrawn(int numDrawn) {
		this.numDrawn = numDrawn;
	}
}
