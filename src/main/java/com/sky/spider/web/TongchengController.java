package com.sky.spider.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sky.spider.service.TongchengService;

@RestController
public class TongchengController {
	

	@Autowired
	private TongchengService tongchengService;
	
	
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
	@RequestMapping(value = "collectTc" , method = RequestMethod.GET)
	public void collectTc(){
		
		tongchengService.collectTc();
		
	}
	

}
