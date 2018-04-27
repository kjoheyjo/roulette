package com.gt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.gt.hibernate.GtSpinBets;

@Repository
public class GtSpinBetsDaoImpl extends BaseHibernateDAO implements GtSpinBetsDao{
	Session session = null;
	Transaction tx = null;
	
	@Override
	public void saveSpinBets(GtSpinBets gtSpinBets) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			session.persist(gtSpinBets);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			
		}finally {
			if( session!= null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<GtSpinBets> findBets(long spinId) {
		session = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			System.out.println("spinbet dao " + spinId);
			criteria = session.createCriteria(GtSpinBets.class);
			criteria.add(Restrictions.eq("spinId", spinId));
			criteria.addOrder(Order.asc("playerId"));
			System.out.println("bets = " +  criteria.list().size());
			tx.commit();
			return (List<GtSpinBets>)criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally {
			if( session!= null && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	@Override
	public List<GtSpinBets> findBetsBySpinIdPlayerId(long spinId, long playerId) {

		session = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			System.out.println("spinbet dao " + spinId);
			criteria = session.createCriteria(GtSpinBets.class);
			criteria.add(Restrictions.eq("spinId", spinId));
			criteria.add(Restrictions.eq("playerId", playerId));
			tx.commit();
			return (List<GtSpinBets>)criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally {
			if( session!= null && session.isOpen()) {
				session.close();
			}
		}
		return null;
	
	}

}
