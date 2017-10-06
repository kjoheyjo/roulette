package com.gt.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gt_bets")
public class GtBets {
	
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="game_config_id")
	private int gameConfigId;
	@Column(name="bet_type")
	private String betType;
	@Column(name="min_stake_value")
	private float minStakeValue;
	@Column(name="max_stake_value")
	private float maxStakeValue;
	@Column(name="payout")
	private int payout;
	@Column(name="modified_date")
	private Timestamp modifiedDate;
	@Column(name="created_date")
	private Timestamp createdDate;
	
	public GtBets(int bets, int gameConfigId, String betType, float minStakeValue, float maxStakeValue, int payout,
			Timestamp modifiedDate, Timestamp createdDate) {
		super();
		this.gameConfigId = gameConfigId;
		this.betType = betType;
		this.minStakeValue = minStakeValue;
		this.maxStakeValue = maxStakeValue;
		this.payout = payout;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
	}

	public GtBets() {
		
	}

	public int getGameConfigId() {
		return gameConfigId;
	}

	public void setGameConfigId(int gameConfigId) {
		this.gameConfigId = gameConfigId;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public float getMinStakeValue() {
		return minStakeValue;
	}

	public void setMinStakeValue(float minStakeValue) {
		this.minStakeValue = minStakeValue;
	}

	public float getMaxStakeValue() {
		return maxStakeValue;
	}

	public void setMaxStakeValue(float maxStakeValue) {
		this.maxStakeValue = maxStakeValue;
	}

	public int getPayout() {
		return payout;
	}

	public void setPayout(int payout) {
		this.payout = payout;
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
		return "GtBets [id=" + id + ", gameConfigId=" + gameConfigId + ", betType=" + betType
				+ ", minStakeValue=" + minStakeValue + ", maxStakeValue=" + maxStakeValue + ", payout=" + payout
				+ ", modifiedDate=" + modifiedDate + ", createdDate=" + createdDate + "]";
	}
	
}
