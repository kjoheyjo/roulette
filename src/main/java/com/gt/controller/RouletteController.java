package com.gt.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gt.beans.GameResponse;
import com.gt.beans.Header;
import com.gt.dao.DataDaoImpl;
import com.gt.services.DataServiceImpl;
import com.gt.services.ServiceInterface;

@EnableWebMvc
@RestController
public class RouletteController {

	

	/*@Autowired
	@Qualifier("dataServiceImpl")*/
	private DataServiceImpl dataServiceImpl;
	//DataServiceImpl dataServiceImpl = new DataServiceImpl();
	
	@Autowired
	public void setDataServiceImpl(DataServiceImpl dataServiceImpl) {
		this.dataServiceImpl = dataServiceImpl;
	}

	@RequestMapping(value = "/init", method = RequestMethod.POST, consumes = "application/xml")
	public @ResponseBody GameResponse getInit(@RequestBody(required = true) Header header) {
		System.out.println("1111111111111111");
		System.out.println("In controller "+dataServiceImpl.getTestData());
		GameResponse game = new GameResponse();
		return game;
	}

}