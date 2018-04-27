package com.gt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gt.dao.GtBetsDao;
import com.gt.dao.GtBetsDaoImpl;
import com.gt.hibernate.GtBets;

@Service
public class GtBetsServiceImpl implements GtBetsService {
	
	private GtBetsDao dao;
	
	public GtBetsDao getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(GtBetsDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveBet(GtBets gtBets) {
		dao.saveBet(gtBets);
	}

	@Override
	public List<GtBets> findAllBets() {
		return dao.findAllBets();
	}

	@Override
	public void deleteByBetType(String betType) {
		dao.deleteByBetType(betType);
	}

	@Override
	public GtBets findByBetType(String betType) {
		return dao.findByBetType(betType);
	}

	@Override
	public void updateBet(GtBets gtBets) {
		dao.updateBet(gtBets);
	}
	
	@Override
	public List<GtBets> findAllBetsByConfigId(int configId) {
		return dao.getAllBetsByConfigId(configId);
	}

}
