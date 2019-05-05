package com.sky.spider.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.sky.spider.dao.DbDao;
import com.sky.spider.domain.BaseMonitorMain;
import com.sky.spider.domain.TempMonitorMain;
import com.sky.spider.service.WebPVService;
import com.sky.spider.utils.FileUtil;
import com.sky.spider.utils.HtmlUnitUtil;
import com.sky.spider.utils.StringUtil;

@Service
public class WebPVServiceImpl  implements WebPVService{
	
	@Autowired
	private DbDao dbDao;


	@Override
	public void collectPvNum() {
		
		 long staStartTime=System.currentTimeMillis(); //计算开始时间
		
		Map<String,String> map = new HashMap<String,String>();	
		
		map.put("fdType", "1");
		
		List<BaseMonitorMain> monitorList  = dbDao.getMonitorForPV(map);
	/*	BaseMonitorMain m = new BaseMonitorMain();
		m.setId(1L);
		m.setFdName("测试监测对象");
		m.setFdObjectWebsit("http://www.meadin.com/");
		
		BaseMonitorMain m2 = new BaseMonitorMain();
		m2.setId(2L);
		m2.setFdName("测试监测对象2");
		m2.setFdObjectWebsit("http://www.1meadin.com/");
		
		List<BaseMonitorMain> monitorList  =   new ArrayList<>();
		monitorList.add(m);
		monitorList.add(m2);*/
		
		List<TempMonitorMain> successList  = new ArrayList<>(); //爬取成功的监测对象
		
		List<TempMonitorMain> errorList  =  new ArrayList<>(); //爬取失败的监测对象
		
		List<TempMonitorMain> nullUrlList  =  new ArrayList<>();//错误官网地址的监测对象
		
		WebClient  webClient  = HtmlUnitUtil.createWebClient();
		
		BaseMonitorMain monitorMain = null ;
		String url ="";
		for (int i=0 ;i < monitorList.size() ;i++){
			
			
			monitorMain = monitorList.get(i);
			System.out.println( i);
			System.out.println( monitorMain);
			
			url = monitorMain.getFdObjectWebsit();
			
			TempMonitorMain tempMonitorMain = new TempMonitorMain();
			
			tempMonitorMain.setId(monitorMain.getId());
			tempMonitorMain.setName(monitorMain.getFdName());
			tempMonitorMain.setWebsit(url);
			
			boolean websiteTrue = StringUtil.validateWebSite(url);
			
			if (websiteTrue){
				url = StringUtil.handleUrl(url);
				Document document = null ;
				String result ="";
				int count =0 ;
				//共访问5次，若5次都没有结果，则不再访问
				while (result.equals("") && count <5){ 
					
					document = HtmlUnitUtil.visit(webClient, "https://www.aizhan.com/cha/"+url);
					result = getPvNum(document);
                   if(result.equals("") && count >0){
                	   System.out.println("重试...第"+ count +"次");
					}
					count ++ ;
					
					
				}
				
				if (!result.equals("")){ //成功
					int pvNum = StringUtil.transPvNum(result);
					tempMonitorMain.setPvNum(pvNum);
					successList.add(tempMonitorMain);
					
				}else {
					errorList.add(tempMonitorMain);
					
				}
				

				
			}else {
				nullUrlList.add(tempMonitorMain);
				
			}
			
		}
		
		//写入文件中：
		
		 StringBuffer sBuffer = new StringBuffer();
		 
		 sBuffer.append("---------采集到有效数据的监测对象：\n");
		 
		 Iterator<TempMonitorMain> suIt = successList.iterator();
		 while(suIt.hasNext()){  
		     
		     sBuffer.append(suIt.next()+"\n");
		 }
		 
		 sBuffer.append("---------未采集到有效数据监测对象：\n");
		 Iterator<TempMonitorMain> errIt = errorList.iterator();
		 while(errIt.hasNext()){  
		     
		     sBuffer.append(errIt.next()+"\n");
		 }
		 
		
		 FileUtil.WriteStringToFile("d:/官网PV.txt", sBuffer.toString());
		 
		 long staEndTime=System.currentTimeMillis(); //获取结束时间
		 

		 System.err.println("爬取结束...爬取花费时间："+(staEndTime-staStartTime)/1000+"秒");
		
		
		 int total = monitorList.size() - nullUrlList.size() ;
		 int successTotal = successList.size() ;
				 
				 
		StringBuffer reportStrBuffer = new StringBuffer();
		reportStrBuffer.append("------爬取报告-------\n");
		
		reportStrBuffer.append("若首次爬取不到数据，重试4次\n");
		
		reportStrBuffer.append("爬取结束...爬取花费时间："+(staEndTime-staStartTime)/1000/60+"分钟\n");
		
		reportStrBuffer.append("总品牌个数："+ monitorList.size()+"\n");
		reportStrBuffer.append("拥有有效网址的品牌个数："+ total+"\n");
		
		reportStrBuffer.append("爬取成功，并采集到有效数值总个数："+ successList.size()+"\n");
		reportStrBuffer.append("网站显示 暂无排名或相关数据不充分 的个数："+ errorList.size()+"\n");
		
		 DecimalFormat df = new DecimalFormat("0.00");//格式化小数    
	        String rate = df.format((float)successTotal/total);//返回的是String类型   
	        
		reportStrBuffer.append("占比,采集到有效数值总个数/拥有有效网址的品牌总个数："+ rate +"\n");
		
		FileUtil.WriteStringToFile("d:/官网PV.txt", reportStrBuffer.toString());
		
		
		
		
		
		
		
		
		
		
	}
	
	/**
	 * 解析页面，获取官网PV
	 *@ClassName:WebPVServiceImpl.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2018年1月24日 上午10:27:45
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@param document
	 *@return
	 */
	public String getPvNum (Document document){
		
		 String resultStr = "";
		 
		 if (null != document){
			 
			 Elements  e =document.select("#alexa_ip");
			 
			 boolean status = !e.toString().equals("") ;  
			 
			 //true : 访问成功且解析完成
			 if (status){ 
				 resultStr = e.text() ;
				 
			 }
			 
		 }
		
		
		 
		
		return resultStr ;
		
		
	}

}
