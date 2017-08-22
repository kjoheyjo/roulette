package com.gt.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gt.beans.GameResponse;

@RestController
@RequestMapping("/init")
public class RouletteController {


 @RequestMapping(value = "/{name}", method = RequestMethod.GET)
 public @ResponseBody GameResponse getInit(@PathVariable String name) {
 GameResponse game =new GameResponse();
 return game;
 }
}