package com.gt.services;

import java.util.List;

import com.gt.hibernate.GtPlayerBets;

public interface GtPlayerBetsService {
	void savePlayerBet(GtPlayerBets gtPlayerBet);
    List<GtPlayerBets> findAllByGameId(int gameId,int playerId);
    void updatePlayerBet(GtPlayerBets gtPlayerBet);
}
