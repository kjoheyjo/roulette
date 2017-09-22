package com.gt.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gt.hibernate.Test;

@Component
public class DataDaoImpl implements DaoInterface {

	@Autowired
	protected SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Test getTestData() {
		System.out.println("Inside dao first line");
		List<Test> employeeList=null;
		try {
			// TODO Auto-generated method stub
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			System.out.println("Inside Dao layer");
			employeeList = session.createCriteria(Test.class).list();
			System.out.println(employeeList);
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return employeeList.get(0);
	}

}
