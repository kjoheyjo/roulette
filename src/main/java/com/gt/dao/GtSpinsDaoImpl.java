package com.gt.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gt.hibernate.GtSpins;

@Repository
public class GtSpinsDaoImpl extends BaseHibernateDAO implements GtSpinsDao{
	
	Session session = null;
	Transaction tx = null;
	@Override
	public void createSpinSchedule(Date spinTime) {
		session  = getSession();
		try {
			tx = session.beginTransaction();
			GtSpins gtSpin = new GtSpins(spinTime,-1,0);
			System.out.println("============ GtSpin" + gtSpin.toString() + " =================");
			session.persist(gtSpin);
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
			System.out.println("============ GtSpin" + gtSpin.toString() + " =================");
			session.update(gtSpin);
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
	
}
