package com.gt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gt.dao.DaoInterface;
import com.gt.dao.DataDaoImpl;
import com.gt.hibernate.Test;

@Component
public class DataServiceImpl  {
	
	 /*@Autowired  
	 @Qualifier("dataDaoImpl")*/
	 DaoInterface dataDaoImpl;  
	 
	 
	 @Autowired
	public void setDataDaoImpl(DaoInterface dataDaoImpl) {
		this.dataDaoImpl = dataDaoImpl;
	}



	public Test getTestData() {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("Inside Service layer");
		return dataDaoImpl.getTestData();
	}

}
