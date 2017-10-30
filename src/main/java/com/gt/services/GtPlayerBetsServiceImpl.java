package com.gt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.dao.GtPlayerBetsDao;
import com.gt.hibernate.GtPlayerBets;

@Service
public class GtPlayerBetsServiceImpl implements GtPlayerBetsService {
	
	GtPlayerBetsDao dao;
	
	@Autowired
	public void setDao(GtPlayerBetsDao dao) {
		this.dao = dao;
	}

	@Override
	public void savePlayerBet(GtPlayerBets gtPlayerBet) {
		dao.savePlayerBet(gtPlayerBet);
	}

	@Override
	public List<GtPlayerBets> findAllByGameId(int gameId, int playerId) {
		return dao.findAllByGameId(gameId, playerId);
	}

	@Override
	public void updatePlayerBet(GtPlayerBets gtPlayerBet) {
		dao.updatePlayerBet(gtPlayerBet);
	}
	
}
