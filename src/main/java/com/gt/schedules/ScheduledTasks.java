package com.gt.schedules;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gt.beans.RouletteEngine;
import com.gt.hibernate.GtSpins;
import com.gt.services.GtSpinsService;
import com.gt.services.GtSpinsServiceImpl;

@Service
@EnableScheduling
public class ScheduledTasks {
	
	GtSpinsService gtSpinsService;
	RouletteEngine rouletteEngine;
	
	@Autowired
	public void setGtSpinsService(GtSpinsServiceImpl gtSpinsServiceImpl) {
		this.gtSpinsService = gtSpinsServiceImpl;
	}
	
	@Autowired
	public void setRouletteEngine(RouletteEngine rouletteEngine) {
		this.rouletteEngine = rouletteEngine;
	}
	
	//@Scheduled(fixedRate=10000,initialDelay=10000)
	public void createSpinSchedules() {
		rouletteEngine.generateSpinResult();
	}
	
	@Scheduled(cron="0 0/30 * * * *")
	public void createSpinSchedulesCronTask() {
		GtSpins gtSpin = new GtSpins(new Timestamp(System.currentTimeMillis()), -1, 0);
		gtSpinsService.saveSpinSchedule(gtSpin);
	}
	
	@Scheduled(cron="50 0/30 * * * *")
	public void spinCronTask() {
		rouletteEngine.generateSpinResult();
	}
}
