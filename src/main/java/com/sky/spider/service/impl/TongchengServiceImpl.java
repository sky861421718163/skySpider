package com.sky.spider.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.spider.dao.DbDao;
import com.sky.spider.domain.MonitorMainTc;
import com.sky.spider.domain.ResponseResult;
import com.sky.spider.service.TongchengService;
import com.sky.spider.utils.FileUtil;
import com.sky.spider.utils.HttpRequestClient;
import com.sky.spider.utils.StringUtil;

import net.sf.json.JSONObject;

@Service
public class TongchengServiceImpl  implements TongchengService{
	
	@Autowired
	private DbDao dbDao;


	@Override
	public void collectTc() {
		
		long staStartTime=System.currentTimeMillis(); //计算开始时间
		
		Map<String,String> map = new HashMap<String,String>();	
		
		map.put("otaType", "29");
		
		List<MonitorMainTc> monitorList  = dbDao.getMonitorForTc(map);
		
	/*	MonitorMainTc m = new MonitorMainTc();
		m.setId(1L);
		m.setHotelName("漫心酒店(太原亲贤街店)");
		m.setTcUrl("https://www.ly.com/HotelInfo-1358242.html");
	
		List<MonitorMainTc> monitorList  =   new ArrayList<>();
		monitorList.add(m);
		*/
		
		
		List<MonitorMainTc> successList  = new ArrayList<>(); //爬取成功的监测对象
		
		List<MonitorMainTc> errorList  =  new ArrayList<>(); //爬取失败的监测对象
		
		List<MonitorMainTc> nullUrlList  =  new ArrayList<>();//错误官网地址的监测对象
		
		MonitorMainTc monitorMain = null ;
		
		String tcId ="";
		String tcUrl =""; 
		String visitUrl ="";
		ResponseResult responseResult =null ;
		//headers
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1");
		headerMap.put("Accept", "application/json");
		headerMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headerMap.put("Accept-Encoding", "gzip, deflate, br");
		headerMap.put("Referer", "https://m.ly.com");
		headerMap.put("X-Requested-With", "XMLHttpRequest");
		headerMap.put("Cookie", "17uCNRefId=18024458;wangba=1516863894773");
		headerMap.put("Connection", "keep-alive");
		
		try {
				for (int i=0 ;i < 200 ;i++){
					
					
					monitorMain = monitorList.get(i);
					System.out.println( monitorList.size());
					System.out.println( i);
					
					System.out.println("即将爬：\n"+monitorMain);
					
					
					tcUrl = monitorMain.getTcUrl();
					tcId = getTcIdfromUrl(tcUrl);
					
					MonitorMainTc tempMonitorMain = new MonitorMainTc();
					
					tempMonitorMain.setId(monitorMain.getId());
					tempMonitorMain.setHotelName(monitorMain.getHotelName());
					tempMonitorMain.setTcId(tcId);
					tempMonitorMain.setTcUrl(tcUrl);
					tempMonitorMain.setCity(monitorMain.getCity());
					
					boolean websiteTrue = StringUtil.validateWebSite(tcUrl);
					
					if (websiteTrue){
						
						visitUrl = "https://m.ly.com/hotel/api/HotelDetail/?hotelId="+tcId+"&antitoken=f3664849dbec96417e08be7e281fd2f6";
						
						int count =0 ;
						int responseCode =0;
						//共访问5次，若5次都没有结果，则不再访问
						while (responseCode!=200 && count <5){ 
							
							/*//线程暂停1.5秒
							try {
								Thread.sleep(1500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
							responseResult = HttpRequestClient.sendGet(visitUrl, headerMap, 2);
							responseCode = responseResult.getResponesCode();
		                   if(responseCode!=200 && count >0){
		                	   System.out.println("重试...第"+ count +"次");
							}
							count ++ ;
			
						}
						
						if (responseCode==200 ){ //成功
							
							String ResponesStr ="";
							ResponesStr = responseResult.getResponesStr() ;
							if (!ResponesStr.equals("null") && ! ResponesStr.equals("")){
								JSONObject   jsonResult = JSONObject.fromObject(responseResult.getResponesStr()) ;
								String dpScoreStr  = jsonResult.getString("DpScore"); //评分
								String dpNumStr  = jsonResult.getString("MixedDpTotalNum"); //点评总数
								
								tempMonitorMain.setDpScore(dpScoreStr);
								tempMonitorMain.setDpTotalNum(dpNumStr);
								
								successList.add(tempMonitorMain);
								
								System.out.println( tempMonitorMain);
							}else {
								errorList.add(tempMonitorMain);
							}
							
							
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
				 
				 Iterator<MonitorMainTc> suIt = successList.iterator();
				 while(suIt.hasNext()){  
				     
				     sBuffer.append(suIt.next()+"\n");
				 }
				 
				 sBuffer.append("---------未采集到有效数据监测对象：\n");
				 Iterator<MonitorMainTc> errIt = errorList.iterator();
				 while(errIt.hasNext()){  
				     
				     sBuffer.append(errIt.next()+"\n");
				 }
				 
				
				 FileUtil.WriteStringToFile("d:/同城旅游网点评数据2.txt", sBuffer.toString());
				 
				 long staEndTime=System.currentTimeMillis(); //获取结束时间
				 
	
				 System.err.println("爬取结束...爬取花费时间："+(staEndTime-staStartTime)/1000+"秒");
				
				
				 int total = monitorList.size() - nullUrlList.size() ;
				 int successTotal = successList.size() ;
						 
						 
				StringBuffer reportStrBuffer = new StringBuffer();
				reportStrBuffer.append("------爬取报告-------\n");
				
				reportStrBuffer.append("若首次爬取不到数据，重试4次\n");
				
				reportStrBuffer.append("爬取结束...爬取花费时间："+(staEndTime-staStartTime)/1000/60+"分钟\n");
				
				reportStrBuffer.append("总品牌个数："+ monitorList.size()+"\n");
				reportStrBuffer.append("拥有有效网址的酒店个数："+ total+"\n");
				
				reportStrBuffer.append("爬取成功，并采集到有效数值总个数："+ successList.size()+"\n");
				reportStrBuffer.append("爬取失败个数："+ errorList.size()+"\n");
				
				 DecimalFormat df = new DecimalFormat("0.00");//格式化小数    
			        String rate = df.format((float)successTotal/total);//返回的是String类型   
			        
				reportStrBuffer.append("占比,采集到有效数值总个数/拥有有效网址的总个数："+ rate +"\n");
				
				FileUtil.WriteStringToFile("d:/同城旅游网点评数据2.txt", reportStrBuffer.toString());
				
				
			
			
			
		}catch (Exception e){
			System.err.println(e.toString());
		}
		

		
		
		
		
		
		
	}
	

	
	public String getTcIdfromUrl (String tcUrl){
		
		 String TcId = "";
		 
		 if (!tcUrl.equals("")){
			 
			 int a = tcUrl.indexOf("-");
			 int b = tcUrl.indexOf(".html");
				
			 TcId = tcUrl.substring(a+1,b);
			 
		 }
		
		return TcId ;
		
		
	}

}
