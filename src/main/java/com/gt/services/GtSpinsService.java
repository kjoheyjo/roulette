package com.gt.services;

import java.util.Date;

import com.gt.hibernate.GtSpins;

public interface GtSpinsService {
	void createSpinSchedule(Date spinTime);
	void updateSpinSchedule(GtSpins gtSpin);
	GtSpins findSpinById(long id);
}
