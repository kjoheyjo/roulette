package com.gt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gt.hibernate.GtGameAccount;

@Repository
public class GtGameAccountDaoImpl extends BaseHibernateDAO implements GtGameAccountDao {
	
	Session session = null;
	Transaction tx = null;

	public List<GtGameAccount> findByProperty(String propertyName, Object value) {
		session = getSession();
		System.out.println("game account find dao for player id " + value + " session - " + session.toString());
		try {
			tx = session.beginTransaction();
			String queryString = "from GtGameAccount as model where model." + propertyName + "= ? and model.state=1";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			if(!tx.wasCommitted()) {
				tx.commit();
			}
			return queryObject.list();
		} catch (Exception re) {
			tx.rollback();
			throw re;
		}
	}
	
	@Override
	public void saveGameAccount(GtGameAccount gtGameAccount) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			session.persist(gtGameAccount);
			if(!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GtGameAccount> findAllGameAccount() {
		session = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtGameAccount.class);
			if(!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return (List<GtGameAccount>) criteria.list();
	}

	@Override
	public GtGameAccount findByGameId(int gameId) {
		session = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtGameAccount.class);
			criteria.add(Restrictions.eq("gameId",gameId));
			if(!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
        return (GtGameAccount) criteria.uniqueResult();
	}

	@Override
	public void updateGameAccount(GtGameAccount gtGameAccount) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			session.update(gtGameAccount);
			if(!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (Exception e) {
			tx.commit();
			e.printStackTrace();
		}
	}

	@Override
	public GtGameAccount findActiveGameByPlayerId(int playerId) {
		System.out.println("game account DAO for player id " + playerId);
		session = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtGameAccount.class);
			criteria.add(Restrictions.eq("playerId", playerId));
			criteria.add(Restrictions.eq("state", 1));
			criteria.add(Restrictions.eq("spinId", 0L));
			criteria.addOrder(Order.desc("id"));
			if(!tx.wasCommitted()) {
				tx.commit();
			}
			return (GtGameAccount) criteria.uniqueResult();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	
	
	}

	@Override
	public List<GtGameAccount> findRecentGames() {
		// TODO Auto-generated method stub
		session = getSession();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(GtGameAccount.class);
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(5);
			if(!tx.wasCommitted()) {
				tx.commit();
			}
			return criteria.list();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	}

	@Override
	public GtGameAccount findActiveSpinGameByPlayerId(int playerId) {
		session = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtGameAccount.class);
			criteria.add(Restrictions.eq("playerId", playerId));
			criteria.add(Restrictions.eq("state", 1));
			criteria.add(Restrictions.ne("spinId", 0L));
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			if(!tx.wasCommitted()) {
				tx.commit();
			}
			return (GtGameAccount) criteria.uniqueResult();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	
	}
}
