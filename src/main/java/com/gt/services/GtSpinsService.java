package com.gt.services;

import com.gt.hibernate.GtSpins;

public interface GtSpinsService {
	void saveSpinSchedule(GtSpins gtSpin);
	void updateSpinSchedule(GtSpins gtSpin);
	GtSpins findSpinById(long id);
	GtSpins findNextSpin();
}
