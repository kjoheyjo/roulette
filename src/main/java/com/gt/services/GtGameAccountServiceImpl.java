package com.gt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gt.dao.GtGameAccountDao;
import com.gt.hibernate.GtGameAccount;

@Service
@Transactional
public class GtGameAccountServiceImpl implements GtGameAccountService {
	
	private GtGameAccountDao dao;
	
	@Autowired
	public void setDao(GtGameAccountDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveGameAccount(GtGameAccount gtGameAccount) {
		dao.saveGameAccount(gtGameAccount);
	}

	@Override
	public List<GtGameAccount> findAllGameAccount() {
		return dao.findAllGameAccount();
	}

	@Override
	public GtGameAccount findByGameId(int gameId) {
		return dao.findByGameId(gameId);
	}

	@Override
	public void updateGameAccount(GtGameAccount gtGameAccount) {
		dao.updateGameAccount(gtGameAccount);
	}

}
