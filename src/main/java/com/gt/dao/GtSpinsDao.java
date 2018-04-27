package com.gt.dao;

import java.util.Date;

import com.gt.hibernate.GtSpins;

public interface GtSpinsDao {
	void updateSpinSchedule(GtSpins gtSpin);
	GtSpins findSpinById(long id);
	void saveSpinSchedule(GtSpins gtSpin);
	GtSpins findNextSpin();
}
