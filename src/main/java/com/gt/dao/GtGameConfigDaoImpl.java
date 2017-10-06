package com.gt.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gt.hibernate.GtGameConfig;

@Repository
public class GtGameConfigDaoImpl extends BaseHibernateDAO implements GtGameConfigDao{
	
	Session session = null;
	Transaction tx = null;
	
	public List findByProperty(String propertyName, Object value) {
		try {
			session = getSession();
			Query queryObject = null;
			try {
				tx = session.beginTransaction();
				String queryString = "from GtGameConfig as model where model." + propertyName + "= ? and model.status=1";
				queryObject = session.createQuery(queryString);
				queryObject.setParameter(0, value);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public void saveGameConfig(GtGameConfig gtGameConfig) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			session.persist(gtGameConfig);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GtGameConfig> findAllGameConfig() {
		session = getSession();
		try {
			tx = session.beginTransaction();
			String queryString = "from GtGameConfig";
			Query queryObject = session.createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	}

	@Override
	public GtGameConfig findByGameConfigId(int gameConfigId) {
		return (GtGameConfig) findByProperty("gameConfigId", gameConfigId).get(0);
	}

	@Override
	public GtGameConfig findByDomain(String domain) {
		return (GtGameConfig) findByProperty("domain", domain).get(0);
	}
}
