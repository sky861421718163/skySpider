package com.sky.spider.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.spider.dao.DbDao;
import com.sky.spider.domain.BaseMonitorMain;
import com.sky.spider.domain.MonitorKey;
import com.sky.spider.domain.ResponseResult;
import com.sky.spider.domain.WxCrawIndex;
import com.sky.spider.service.ShougouweinxinService;
import com.sky.spider.utils.HttpRequestClient;

@Service
public class ShougouWeinxinServiceImpl implements ShougouweinxinService {

	@Autowired
	private DbDao dbDao;

	@Override
	public void collectShougouWeinxinIndex() {
		
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
		headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headerMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headerMap.put("Accept-Encoding", "gzip, deflate");
		headerMap.put("Referer", "http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E5%A6%82%E5%AE%B6&tsn=1");
		headerMap.put("Connection", "keep-alive");
		String IPLOC = "CN4690";
		
		headerMap.put("Cookie", IPLOC + "SUV=00DD0B877AE0A0B25ADD4D5E5F033147; ABTEST=0|1524452704|v1;SUID=B2A0E07A6E2F940A000000005ADD4D60");


		String visitUrl = "";
		ResponseResult responseResult = null;
		
		List<BaseMonitorMain> monitorList = dbDao.getMCIMonitor();
		
		for(int i =0 ;i < monitorList.size() ;i++){
			BaseMonitorMain  monitor = monitorList.get(i);
			
			List<MonitorKey> monitorKeyList = dbDao.getMonitorKeys(monitor.getId());
			
			float resultIndex = 0f ;
			
			try{
				for(int j =0 ;j < monitorKeyList.size() ;j++){
					
					String mkey = monitorKeyList.get(j).getMonitorKey();
					visitUrl = "http://weixin.sogou.com/weixin?type=2&tsn=3&ie=utf8&query=" + mkey;
					responseResult = HttpRequestClient.sendGetforZipWithProxy(visitUrl, headerMap, 1);
					
					String responesStr  = responseResult.getResponesStr();
					IPLOC = responseResult.getResponesCookie();
					System.out.println("responesStr----" + responesStr);
					System.out.println("IPLOC----" + IPLOC);
					Document doc= Jsoup.parse(responesStr);
					Elements zwmcEls = doc.getElementsByClass("mun");
					System.out.println("文章数：" + zwmcEls.text());
					
					long num = 0;
					Elements numEles = doc.select("div[class=mun]");
					if (numEles.size() > 0) {
						num = Long.valueOf(getIntegerFromString(numEles.get(0).text()));
					} else {
						// 获取文章列表
						Elements newsList = doc.select("ul[class=news-list]");
						if (newsList.size() != 0) {
							Elements newsLiTag = newsList.get(0).getElementsByTag("li");
							num = newsLiTag.size();
						}
					}
					
					resultIndex =resultIndex + num * monitorKeyList.get(j).getKeyRate();
					
				}
			}catch(Exception e){
				WxCrawIndex wxCrawIndex = new WxCrawIndex();
				
				wxCrawIndex.setMonitorId(String.valueOf(monitor.getId()));
				wxCrawIndex.setMonitorName(monitor.getFdName());
				wxCrawIndex.setWxIndex(resultIndex);
				
				dbDao.saveWxCrawIndex_err(wxCrawIndex);
			}
			
			
			
			WxCrawIndex wxCrawIndex = new WxCrawIndex();
			
			wxCrawIndex.setMonitorId(String.valueOf(monitor.getId()));
			wxCrawIndex.setMonitorName(monitor.getFdName());
			wxCrawIndex.setWxIndex(resultIndex);
			
			dbDao.saveWxCrawIndex(wxCrawIndex);
			
			
		}

	
		
	}
	
	public static String getIntegerFromString(String context) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(context);
		return m.replaceAll("").trim();
	}

	@Override
	public void updateShougouWeinxinIndex() {
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
		headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headerMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headerMap.put("Accept-Encoding", "gzip, deflate");
		headerMap.put("Referer", "http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E5%A6%82%E5%AE%B6&tsn=1");
		headerMap.put("Connection", "keep-alive");
		String IPLOC = "CN3301";
		
		headerMap.put("Cookie", IPLOC + "SUV=00CF33B37AE0A0B25ADDC159A36D6409; ABTEST=7|1524482393|v1; SNUID=766425BFC4C1AE46F2450586C596306D");


		String visitUrl = "";
		ResponseResult responseResult = null;
		
		List<BaseMonitorMain> monitorList = dbDao.getMCIMonitorZero();
		
		for(int i =0 ;i < monitorList.size() ;i++){
			System.err.println("当前进度----" + i);
			BaseMonitorMain  monitor = monitorList.get(i);
			
			List<MonitorKey> monitorKeyList = dbDao.getMonitorKeys(monitor.getId());
			
			float resultIndex = 0f ;
			if(monitorKeyList.size()>0){
				
				try{
					for(int j =0 ;j < monitorKeyList.size() ;j++){
						
						String mkey = monitorKeyList.get(j).getMonitorKey();
						visitUrl = "http://weixin.sogou.com/weixin?type=2&tsn=3&ie=utf8&query=" + mkey;
						responseResult = HttpRequestClient.sendGetforZipWithProxy(visitUrl, headerMap, 1);
						
						String responesStr  = responseResult.getResponesStr();
						if(responesStr.contains("您的访问过于频繁")){
							System.err.println("访问被限制！");
						}
						IPLOC = responseResult.getResponesCookie();
						System.out.println("responesStr----" + responesStr);
						System.out.println("IPLOC----" + IPLOC);
						Document doc= Jsoup.parse(responesStr);
						Elements zwmcEls = doc.getElementsByClass("mun");
						System.out.println("文章数：" + zwmcEls.text());
						
						long num = 0;
						Elements numEles = doc.select("div[class=mun]");
						if (numEles.size() > 0) {
							num = Long.valueOf(getIntegerFromString(numEles.get(0).text()));
						} else {
							// 获取文章列表
							Elements newsList = doc.select("ul[class=news-list]");
							if (newsList.size() != 0) {
								Elements newsLiTag = newsList.get(0).getElementsByTag("li");
								num = newsLiTag.size();
							}
						}
						
						resultIndex =resultIndex + num * monitorKeyList.get(j).getKeyRate();
						
					}
				}catch(Exception e){
					WxCrawIndex wxCrawIndex = new WxCrawIndex();
					
					wxCrawIndex.setMonitorId(String.valueOf(monitor.getId()));
					wxCrawIndex.setMonitorName(monitor.getFdName());
					wxCrawIndex.setWxIndex(resultIndex);
					
					dbDao.saveWxCrawIndex_err(wxCrawIndex);
				}
			}
			
			
			WxCrawIndex wxCrawIndex = new WxCrawIndex();
			
			wxCrawIndex.setMonitorId(String.valueOf(monitor.getId()));
			wxCrawIndex.setMonitorName(monitor.getFdName());
			wxCrawIndex.setWxIndex(resultIndex);
			
			dbDao.updateWxCrawIndex(wxCrawIndex);
			
		}
		System.err.println("collect complete!");
	}

	
}
