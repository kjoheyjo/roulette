package com.gt.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gt_spins")
public class GtSpins {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="spin_time")
	private Date spinTime;
	@Column(name="draw")
	private int draw;
	@Column(name="status")
	private int status;
	public GtSpins() {
		
	}
	public GtSpins(long id, Date spinTime, int draw, int status) {
		this.id = id;
		this.spinTime = spinTime;
		this.draw = draw;
		this.status = status;
	}
	public GtSpins(Date spinTime, int draw, int status) {
		this.spinTime = spinTime;
		this.draw = draw;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getSpinTime() {
		return spinTime;
	}
	public void setSpinTime(Date spinTime) {
		this.spinTime = spinTime;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "GtSpins [id=" + id + ", spinTime=" + spinTime + ", draw=" + draw + ", status=" + status + "]";
	}
	
}
