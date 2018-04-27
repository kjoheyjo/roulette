package com.gt.dao;

import java.util.List;

import com.gt.hibernate.GtBets;

public interface GtBetsDao {
	void saveBet(GtBets gtBets);
    List<GtBets> findAllBets();
    void deleteByBetType(String betType);
    GtBets findByBetType(String betType);
    void updateBet(GtBets gtBets);
	List<GtBets> getAllBetsByConfigId(int configId);
}
