package com.gt.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gt.hibernate.GtSpins;

@Repository
public class GtSpinsDaoImpl extends BaseHibernateDAO implements GtSpinsDao{
	
	Session session = null;
	Transaction tx = null;
	
	@Override
	public void saveSpinSchedule(GtSpins gtSpin) {
		session  = getSession();
		try {
			tx = session.beginTransaction();
			System.out.println("============ GtSpin" + gtSpin.toString() + " =================");
			session.persist(gtSpin);
			System.out.println("============ GtSpin" + gtSpin.toString() + " =================");
			tx.commit();
		}catch(Exception e){
			if(session!=null && session.isOpen()) {
				session.close();
			}
			tx.rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateSpinSchedule(GtSpins gtSpin) {
		session  = getSession();
		try {
			tx = session.beginTransaction();
			System.out.println("============ GtSpin update " + gtSpin.toString() + " =================");
			session.update(gtSpin);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally {
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}
		return;
	}

	@Override
	public GtSpins findSpinById(long id) {
		session  = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			System.out.println("============ GtSpin - id "+ id +" =================");
			criteria = session.createCriteria(GtSpins.class);
			criteria.add(Restrictions.eq("id", id));
			tx.commit();
		}catch(Exception e){
			if(session!=null && session.isOpen()) {
				session.close();
			}
			tx.rollback();
			e.printStackTrace();
		}
		return (GtSpins) criteria.list().get(0);
	}

	@Override
	public GtSpins findNextSpin() {
		session = getSession();
		Criteria criteria = null;
		try {
			tx = session.beginTransaction();
			criteria = session.createCriteria(GtSpins.class);
			criteria.add(Restrictions.eq("status", 0));
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			return (GtSpins) criteria.uniqueResult();
		}catch(Exception e) {
			tx.rollback();
		}
		return null;
	}
	
}
