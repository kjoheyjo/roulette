package com.gt.services;

import java.util.List;

import com.gt.hibernate.GtBets;

public interface GtBetsService {
	void saveBet(GtBets gtBets);
    List<GtBets> findAllBets();
    void deleteByBetType(String betType);
    GtBets findByBetType(String betType);
    void updateBet(GtBets gtBets);
	List<GtBets> findAllBetsByConfigId(int configId);
}
