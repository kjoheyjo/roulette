package com.gt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gt.hibernate.GtBets;

@Repository
public class GtBetsDaoImpl extends BaseHibernateDAO implements GtBetsDao{

	Session session = null;
	Transaction tx = null;

	public List findByProperty(String propertyName, Object value) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			String queryString = "from GtBets as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	}

	@Override
	public void saveBet(GtBets gtBets) {
		session.persist(gtBets);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GtBets> findAllBets() {
		session =  getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtBets.class);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		}
		return (List<GtBets>) criteria.list();
	}

	@Override
	public void deleteByBetType(String betType) {
		session =  getSession();
		try {
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("delete from GtBets where betType = :betType");
			query.setString("betType", betType);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public GtBets findByBetType(String betType) {
		Criteria criteria = null;
		session =  getSession();
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtBets.class);
			criteria.add(Restrictions.eq("betType",betType));
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return (GtBets) criteria.uniqueResult();
	}

	@Override
	public void updateBet(GtBets gtBets) {
		session =  getSession();
		try {
			tx = session.beginTransaction();
			session.update(gtBets);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<GtBets> getAllBetsByConfigId(int configId) {
		return findByProperty("gameConfigId", configId);
		
	}
}
