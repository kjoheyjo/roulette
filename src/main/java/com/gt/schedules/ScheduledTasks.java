package com.gt.schedules;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ScheduledTasks {
	
	@Scheduled(fixedRate=100000,initialDelay=3000)
	public void createSpinSchedules() {
		
	}
	@Scheduled(cron="0 0 0/1 1/1 * ? *")
	public void createSpinSchedulesCronTask() {
		
	}
}
