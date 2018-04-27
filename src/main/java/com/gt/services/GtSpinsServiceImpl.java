package com.gt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.dao.GtSpinsDao;
import com.gt.hibernate.GtSpins;

@Service
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
	public void saveSpinSchedule(GtSpins gtSpin) {
		dao.saveSpinSchedule(gtSpin);
	}

	@Override
	public void updateSpinSchedule(GtSpins gtSpin) {
		dao.updateSpinSchedule(gtSpin);
	}

	@Override
	public GtSpins findSpinById(long id) {
		return dao.findSpinById(id);
	}
	
	@Override
	public GtSpins findNextSpin() {
		return dao.findNextSpin();
	}

}
