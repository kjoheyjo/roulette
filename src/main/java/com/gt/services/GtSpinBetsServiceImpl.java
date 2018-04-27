package com.gt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.dao.GtSpinBetsDao;
import com.gt.hibernate.GtSpinBets;

@Service
public class GtSpinBetsServiceImpl implements GtSpinBetsService {
	
	private GtSpinBetsDao dao;
	
	@Autowired
	public void setGtSpinBetsDao(GtSpinBetsDao gtSpinBetsDao) {
		this.dao = gtSpinBetsDao;
	}
	
	@Override
	public void saveSpinBets(GtSpinBets gtSpinBets) {
		dao.saveSpinBets(gtSpinBets);
	}

	@Override
	public List<GtSpinBets> findBets(long spinId) {
		System.out.println("spinbet service");
		return dao.findBets(spinId);
	}
	
	@Override
	public List<GtSpinBets> findBetsBySpinIdPlayerId(long spinId, long playerId){
		return dao.findBetsBySpinIdPlayerId(spinId, playerId);
	}

}
