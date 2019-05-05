package com.sky.spider.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sky.spider.service.Index360Service;
import com.sky.spider.service.ShougouweinxinService;

@RestController
public class ShougoWeixinController {
	
	@Autowired
	private ShougouweinxinService shougouweinxinService;

	/**
	 * 搜狗微信文章数
	 *@ClassName:TongchengController.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2018年4月4日 上午15:27:35
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 */
	@RequestMapping(value = "collectShougouWeinxinIndex" , method = RequestMethod.GET)
	public void collectShougouWeinxin(){
		
		shougouweinxinService.collectShougouWeinxinIndex();
		
	}
	
	@RequestMapping(value = "updateShougouWeinxinIndex" , method = RequestMethod.GET)
	public void updateShougouWeinxin(){
		
		shougouweinxinService.updateShougouWeinxinIndex();
		
	}
}
