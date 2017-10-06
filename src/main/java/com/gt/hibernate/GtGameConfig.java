package com.gt.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gt_game_config")
public class GtGameConfig {
	
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="game_config_id")
	private int gameConfigId;
	@Column(name="domain")
	private String domain;
	@Column(name="min_animation_time")
	private int minAnimationTime;
	@Column(name="max_animation_time")
	private int maxAnimationTime;
	@Column(name="min_stake")
	private int minStake;
	@Column(name="max_stake")
	private int maxStake;
	@Column(name="default_stake")
	private int defaultStake;
	@Column(name="max_winnings")
	private int maxWinnings;
	@Column(name="min_number")
	private int minNumber;
	@Column(name="max_number")
	private int maxNumber;
	@Column(name="total_draws")
	private int totalDraws;
	@Column(name="draw_duplicates")
	private int drawDuplicates;
	@Column(name="status")
	private int status;
	
	public GtGameConfig(int gameConfigId, int minAnimationTime, int maxAnimationTime, int minStake, int defaultStake,
			int maxWinnings, int minNumber, int maxNumber, int totalDraws, int drawDuplicates, int status) {
		super();
		this.gameConfigId = gameConfigId;
		this.minAnimationTime = minAnimationTime;
		this.maxAnimationTime = maxAnimationTime;
		this.minStake = minStake;
		this.defaultStake = defaultStake;
		this.maxWinnings = maxWinnings;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.totalDraws = totalDraws;
		this.drawDuplicates = drawDuplicates;
		this.status = status;
	}
	
	public GtGameConfig() {
		
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getMinAnimationTime() {
		return minAnimationTime;
	}

	public void setMinAnimationTime(int minAnimationTime) {
		this.minAnimationTime = minAnimationTime;
	}

	public int getMaxAnimationTime() {
		return maxAnimationTime;
	}

	public void setMaxAnimationTime(int maxAnimationTime) {
		this.maxAnimationTime = maxAnimationTime;
	}

	public int getMinStake() {
		return minStake;
	}

	public void setMinStake(int minStake) {
		this.minStake = minStake;
	}
	
	public int getMaxStake() {
		return maxStake;
	}

	public void setMaxStake(int maxStake) {
		this.maxStake = maxStake;
	}

	public int getDefaultStake() {
		return defaultStake;
	}

	public void setDefaultStake(int defaultStake) {
		this.defaultStake = defaultStake;
	}

	public int getMaxWinnings() {
		return maxWinnings;
	}

	public void setMaxWinnings(int maxWinnings) {
		this.maxWinnings = maxWinnings;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getTotalDraws() {
		return totalDraws;
	}

	public void setTotalDraws(int totalDraws) {
		this.totalDraws = totalDraws;
	}

	public int getDrawDuplicates() {
		return drawDuplicates;
	}

	public void setDrawDuplicates(int drawDuplicates) {
		this.drawDuplicates = drawDuplicates;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGameConfigId() {
		return gameConfigId;
	}

	public void setGameConfigId(int gameConfigId) {
		this.gameConfigId = gameConfigId;
	}

	@Override
	public String toString() {
		return "GtGameConfig [gameConfigId=" + gameConfigId + ", minAnimationTime=" + minAnimationTime
				+ ", maxAnimationTime=" + maxAnimationTime + ", minStake=" + minStake + ", defaultStake=" + defaultStake
				+ ", maxWinnings=" + maxWinnings + ", minNumber=" + minNumber + ", maxNumber=" + maxNumber
				+ ", totalDraws=" + totalDraws + ", drawDuplicates=" + drawDuplicates + ", status=" + status + "]";
	}
	
	
}
