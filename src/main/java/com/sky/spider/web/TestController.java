package com.sky.spider.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sky.spider.service.TestService;

@RestController
public class TestController {
	
	@Autowired
   private TestService testService;

	@RequestMapping(value = "welcome" , method = RequestMethod.GET)
	@ResponseBody 
	public String welcome(){
		
		return "welcome8888"; 
	}
	
	
	@RequestMapping(value = "trans" , method = RequestMethod.GET)
	public void trans(){
		testService.save();
	}
	
	

}
