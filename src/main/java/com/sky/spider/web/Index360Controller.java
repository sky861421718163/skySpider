package com.sky.spider.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sky.spider.service.Index360Service;

@RestController
public class Index360Controller {
	

	@Autowired
	private Index360Service index360Service;
	
	
	/**
	 * 抓取同城旅游网点评数据及总点评数
	 *@ClassName:TongchengController.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2018年1月26日 上午11:27:35
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 */
	@RequestMapping(value = "collect360Index" , method = RequestMethod.GET)
	public void collectTc(){
		
		index360Service.collect360Index();
		
	}
	

}
