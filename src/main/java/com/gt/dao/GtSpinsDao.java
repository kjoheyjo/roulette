package com.gt.dao;

import java.util.Date;

import com.gt.hibernate.GtSpins;

public interface GtSpinsDao {
	void createSpinSchedule(Date spinTime);
	void updateSpinSchedule(GtSpins gtSpin);
	GtSpins findSpinById(long id);
}
