package com.gt.services;

import java.util.List;
import com.gt.hibernate.GtSpinBets;

public interface GtSpinBetsService {
	void saveSpinBets(GtSpinBets gtSpinBets);
	List<GtSpinBets> findBets(long spinId);
	List<GtSpinBets> findBetsBySpinIdPlayerId(long spinId, long playerId);
}
