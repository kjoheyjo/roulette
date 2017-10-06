package com.gt.services;

import java.util.List;

import com.gt.hibernate.GtGameConfig;

public interface GtGameConfigService {
	void saveGameConfig(GtGameConfig gtGameConfig);
    List<GtGameConfig> findAllGameConfig();
    GtGameConfig findByGameConfigId(int gameConfigId);
    GtGameConfig findConfigByDomain(String domain);
}
