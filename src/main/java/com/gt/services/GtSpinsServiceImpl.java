package com.gt.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gt.dao.GtSpinsDao;
import com.gt.hibernate.GtSpins;

public class GtSpinsServiceImpl implements GtSpinsService{

	private GtSpinsDao dao;

	public GtSpinsDao getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(GtSpinsDao dao) {
		this.dao = dao;
	}
	@Override
	public void createSpinSchedule(Date spinTime) {
		dao.createSpinSchedule(spinTime);
	}

	@Override
	public void updateSpinSchedule(GtSpins gtSpin) {
		dao.updateSpinSchedule(gtSpin);
	}

	@Override
	public GtSpins findSpinById(long id) {
		return dao.findSpinById(id);
	}

}
