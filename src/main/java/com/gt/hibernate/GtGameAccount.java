package com.gt.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gt_game_account")
public class GtGameAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="player_id")
	private int playerId;
	@Column(name="spin_id")
	private long spinId;
	@Column(name="state")
	private int state;		// 0 -> init, 1 -> spin, 2 -> close
	@Column(name="bet_amount")
	private float betAmount;
	@Column(name="draw")
	private int draw;
	@Column(name="winnings")
	private float winnings;
	@Column(name="modified_date")
	private Timestamp modifiedDate;
	@Column(name="created_date")
	private Timestamp createdDate;
	
	public long getSpinId() {
		return spinId;
	}

	public void setSpinId(long spinId) {
		this.spinId = spinId;
	}

	public GtGameAccount(int id, int playerId, int state, Timestamp modifiedDate, Timestamp createdDate) {
		super();
		this.id = id;
		this.playerId = playerId;
		this.state = state;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
	}
	
	public GtGameAccount(int playerId, int state, Timestamp modifiedDate, Timestamp createdDate) {
		this.playerId = playerId;
		this.state = state;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
	}
	
	public GtGameAccount(int playerId, int state, Timestamp modifiedDate) {
		this.playerId = playerId;
		this.state = state;
		this.modifiedDate = modifiedDate;
	}

	public GtGameAccount() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public float getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(float betAmount) {
		this.betAmount = betAmount;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public float getWinnings() {
		return winnings;
	}

	public void setWinnings(float winnings) {
		this.winnings = winnings;
	}

	@Override
	public String toString() {
		return "GtGameAccount [id=" + id + ", playerId=" + playerId + ", state=" + state
				+ ", betAmount=" + betAmount + ", draw=" + draw + ", winnings=" + winnings + ", modifiedDate="
				+ modifiedDate + ", createdDate=" + createdDate + "]";
	}
	
	
	
}
