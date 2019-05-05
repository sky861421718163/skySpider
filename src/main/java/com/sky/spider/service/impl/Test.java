package com.sky.spider.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.sky.spider.domain.ResponseResult;
import com.sky.spider.utils.HttpRequestClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

	public static void main1(String[] args) {
		
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1");
		
	    String 	visitUrl = "https://trends.so.com/index/overviewJson?area=全国&q=如家";
	    
		ResponseResult  responseResult = HttpRequestClient.sendGet(visitUrl, headerMap, 2);
		
		String ResponesStr = responseResult.getResponesStr() ;
		
		JSONObject   jsonResult = JSONObject.fromObject(ResponesStr) ;
		JSONArray dataArr =jsonResult.getJSONArray("data");
		JSONObject data = (JSONObject) dataArr.get(0) ;
		
		JSONObject resultData = data.getJSONObject("data");
		//dataArr.get(0);
		System.out.println(resultData.getString("month_index"));
		
		
	}

}
