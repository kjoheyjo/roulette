package com.gt.dao;

import java.util.List;

import com.gt.hibernate.GtGameConfig;

public interface GtGameConfigDao {
	void saveGameConfig(GtGameConfig gtGameConfig);
    List<GtGameConfig> findAllGameConfig();
    GtGameConfig findByGameConfigId(int gameConfigId);
	GtGameConfig findByDomain(String domain);
}
