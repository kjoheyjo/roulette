package com.gt.dao;

import java.util.List;

import com.gt.hibernate.GtPlayerBets;

public interface GtPlayerBetsDao {
	void savePlayerBet(GtPlayerBets gtPlayerBet);
    List<GtPlayerBets> findAllByGameId(int gameId,int playerId);
    void updatePlayerBet(GtPlayerBets gtPlayerBet);
}
