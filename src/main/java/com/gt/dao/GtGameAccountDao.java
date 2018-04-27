package com.gt.dao;

import java.util.List;

import com.gt.hibernate.GtGameAccount;

public interface GtGameAccountDao {
	void saveGameAccount(GtGameAccount gtGameAccount);
    List<GtGameAccount> findAllGameAccount();
    GtGameAccount findByGameId(int gameId);
    void updateGameAccount(GtGameAccount gtGameAccount);
    GtGameAccount findActiveGameByPlayerId(int playerId);
	List<GtGameAccount> findRecentGames();
	GtGameAccount findActiveSpinGameByPlayerId(int playerId);
}
