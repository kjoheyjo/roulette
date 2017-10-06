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
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="player_id")
	private long playerId;
	@Column(name="game_id")
	private int gameId;
	@Column(name="state")
	private int state;		// 0 -> init, 1 -> spin, 2 -> close
	@Column(name="modified_date")
	private Timestamp modifiedDate;
	@Column(name="created_date")
	private Timestamp createdDate;
	
	public GtGameAccount(int id, long playerId, int gameId, int state, Timestamp modifiedDate, Timestamp createdDate) {
		super();
		this.id = id;
		this.playerId = playerId;
		this.gameId = gameId;
		this.state = state;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
	}
	
	public GtGameAccount(long playerId, int gameId, int state, Timestamp modifiedDate, Timestamp createdDate) {
		this.playerId = playerId;
		this.gameId = gameId;
		this.state = state;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
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

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
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

	@Override
	public String toString() {
		return "GtGameAccount [id=" + id + ", playerId=" + playerId + ", gameId=" + gameId + ", state=" + state
				+ ", modifiedDate=" + modifiedDate + ", createdDate=" + createdDate + "]";
	}
	
	
	
}
