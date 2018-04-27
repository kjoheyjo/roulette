package com.gt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gt.dao.GtGameConfigDao;
import com.gt.hibernate.GtGameConfig;

@Service
public class GtGameConfigServiceImpl implements GtGameConfigService {
	
	private GtGameConfigDao dao;
	
	@Autowired
	public void setDao(GtGameConfigDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveGameConfig(GtGameConfig gtGameConfig) {
		dao.saveGameConfig(gtGameConfig);
	}

	@Override
	public List<GtGameConfig> findAllGameConfig() {
		return dao.findAllGameConfig();
	}

	@Override
	public GtGameConfig findByGameConfigId(int gameConfigId) {
		return dao.findByGameConfigId(gameConfigId);
	}
	
	@Override
	public GtGameConfig findConfigByDomain(String domain) {
		return dao.findByDomain(domain);
	}

}
