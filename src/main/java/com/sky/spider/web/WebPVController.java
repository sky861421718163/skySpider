package com.sky.spider.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sky.spider.service.WebPVService;

@RestController
public class WebPVController {
	

	@Autowired
	private WebPVService webPVService;
	
	
	@RequestMapping(value = "collectPvNum" , method = RequestMethod.GET)
	public void collectPvNum(){
		
	    webPVService.collectPvNum();
		
	}
	

}
