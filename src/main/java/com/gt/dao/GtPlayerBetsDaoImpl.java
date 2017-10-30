package com.gt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gt.hibernate.GtGameAccount;
import com.gt.hibernate.GtPlayerBets;

@Repository
public class GtPlayerBetsDaoImpl extends BaseHibernateDAO implements GtPlayerBetsDao{
	
	Session session = null;
	Transaction tx = null;
	@Override
	public void savePlayerBet(GtPlayerBets gtPlayerBet) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			session.persist(gtPlayerBet);
			tx.commit();
		} catch (Exception e) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			tx.rollback();
			e.printStackTrace();
		}
	}
	@Override
	public List<GtPlayerBets> findAllByGameId(int gameId, int playerId) {
		Criteria criteria = null;
		session = getSession();
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtPlayerBets.class);
			criteria.add(Restrictions.eq("gameId", gameId));
			criteria.add(Restrictions.eq("playerId", playerId));
			tx.commit();
		} catch (Exception e) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			tx.rollback();
			e.printStackTrace();
		}
		return (List<GtPlayerBets>) criteria.list();
	}
	@Override
	public void updatePlayerBet(GtPlayerBets gtPlayerBet) {
		// TODO Auto-generated method stub
		session = getSession();
		try {
			tx = session.beginTransaction();
			session.update(gtPlayerBet);
			tx.commit();
		} catch (Exception e) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			tx.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
