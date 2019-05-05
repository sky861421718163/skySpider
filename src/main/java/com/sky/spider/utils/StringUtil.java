package com.sky.spider.utils;

import java.util.regex.Pattern;

public class StringUtil {
	
	
	/**
	 * 对网址进行处理，1.去除网址前的http/https协议字符串 2.除去域名后的多余字符串
	 *@ClassName:StringUtil.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2018年1月24日 上午9:40:00
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@param url
	 *@return
	 */
	public static String handleUrl(String url){
		
		String newUrl = "";
		
		newUrl= url.replace("https://", "");
		newUrl = newUrl.replace("http://", "");
		int index =newUrl.indexOf("/");
		if (index != -1){
			newUrl =newUrl.substring(0, index);
		}
		
		
		return newUrl ;
		
		
	}
	

	
	/**
	 * 将页面取到的字符串PV转为int
	 *@ClassName:StringUtil.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2018年1月24日 上午10:44:45
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@param str
	 *@return
	 */
     public static int transPvNum(String str){
		
		int num  = 0;
		String re = "";
		
		re= str.replace(",", "");
		re = re.replaceAll("\\s*", ""); 
		
		num = Integer.valueOf(re);
		return num ;
		
	}
     

     
     /**
      * 判断网址是否合法
      *@ClassName:StringUtil.java
      *@ClassDescribe:
      *@auth:sky
      *@createDate:2018年1月24日 上午10:56:57
      *@updateAuth:
      *@updateDate:
      *@version
      *@param str
      *@return
      */
     public static boolean validateWebSite(String str){
    	 String reg = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$";
    	 
         Pattern pattern = Pattern.compile(reg); 
         
         return pattern.matcher(str).matches();
    	 
     }

	

}
