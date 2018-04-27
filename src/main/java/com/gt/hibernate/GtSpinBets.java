package com.gt.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gt_spin_bets")
public class GtSpinBets {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="spin_id")
	private long spinId;
	@Column(name="player_id")
	private long playerId;
	@Column(name="bet_name")
	private String betName;
	@Column(name="stake")
	private float stake;
	@Column(name="selection")
	private String selection;
	
	public GtSpinBets() {
		
	}

	public GtSpinBets(long spinId, long playerId, String betName, float stake, String selection) {
		this.spinId = spinId;
		this.playerId = playerId;
		this.betName = betName;
		this.stake = stake;
		this.selection = selection;
	}

	public long getSpinId() {
		return spinId;
	}

	public void setSpinId(long spinId) {
		this.spinId = spinId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
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

	@Override
	public String toString() {
		return "GtSpinBets [spinId=" + spinId + ", playerId=" + playerId + ", betName=" + betName + ", stake=" + stake
				+ ", selection=" + selection + "]";
	}
	
}
