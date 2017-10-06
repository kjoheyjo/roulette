package com.gt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gt.hibernate.GtGameAccount;

@Repository
public class GtGameAccountDaoImpl extends BaseHibernateDAO implements GtGameAccountDao {
	
	Session session = null;
	Transaction tx = null;

	public List findByProperty(String propertyName, Object value) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			String queryString = "from GtGameAccount as model where model." + propertyName + "= ? and model.status=1";
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
	public void saveGameAccount(GtGameAccount gtGameAccount) {
		session = getSession();
		try {
			tx = session.beginTransaction();
			session.persist(gtGameAccount);
			tx.commit();
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
			tx.commit();
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
			tx.commit();
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
			tx.commit();
		} catch (Exception e) {
			tx.commit();
			e.printStackTrace();
		}
	}
}
