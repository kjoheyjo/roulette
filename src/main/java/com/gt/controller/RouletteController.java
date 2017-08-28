package com.gt.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gt.beans.GameResponse;
import com.gt.beans.Header;

@EnableWebMvc
@RestController
public class RouletteController {

//@RequestParam("initXml")
 @RequestMapping(value= "/init",method = RequestMethod.POST, consumes= "application/xml")
 public @ResponseBody GameResponse getInit(  @RequestBody(required = true) Header header) {
	 System.out.println("1111111111111111");
 GameResponse game =new GameResponse();
 return game;
 }

}