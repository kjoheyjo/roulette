package com.gt.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gt_player_bets")
public class GtPlayerBets implements Serializable {
	@Id
	@Column(name="game_id")
	private int gameId;
	@Id
	@Column(name="player_id")
	private int playerId;
	@Id
	@Column(name="bet_name")
	private String betName;
	@Column(name="stake")
	private float stake;
	@Column(name="selection")
	private String selection = "";
	
	public GtPlayerBets() {
		
	}
	public GtPlayerBets(int gameId, int playerId, String betName, float stake, String selection) {
		this.gameId = gameId;
		this.playerId = playerId;
		this.betName = betName;
		this.stake = stake;
		this.selection = selection;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getBetName() {
		return betName;
	}
	public void setBetName(String betName) {
		this.betName = betName;
	}
	public float getStake() {
		return stake;
	}
	public void setStake(float stake) {
		this.stake = stake;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	
}
