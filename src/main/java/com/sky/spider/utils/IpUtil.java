package com.sky.spider.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;

import javax.servlet.http.HttpServletRequest;

import com.sky.spider.domain.ProxyIp;

public class IpUtil {
	/**
	 * 得到访问者ip 一
	 * 
	 * @ClassDescribe:
	 * @author:sky
	 * @createDate:2017年9月12日 下午2:16:27
	 * @updateAuthor:
	 * @updateDate:
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * 得到访问者ip 二
	 * 
	 * @ClassDescribe:
	 * @author:sky
	 * @createDate:2017年9月12日 下午2:16:49
	 * @updateAuthor:
	 * @updateDate:
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static Type int2Type(int typeStr) {

		Type type = null;

		switch (typeStr) {
		case 1:
			type = Type.HTTP;
			break;

		case 3:
			type = Type.SOCKS;
			break;
		default:
			throw new RuntimeException("ipType错误！" + typeStr);
		}

		return type;
	}

	/**
	 * 验证ip对于某网站是否有效
	 *@ClassName:IpUtil.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2017年11月16日 下午2:38:14
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@param ip
	 *@param url
	 *@return
	 */
	public static boolean validateIp(ProxyIp ip, String url) {

		boolean flag = false ;
		URL realUrl = null;
		int responseCode = 200;
		
		   // 创建代理服务器
			InetSocketAddress addr = new InetSocketAddress(ip.getIp(), ip.getPort());
			Proxy proxy = new Proxy(IpUtil.int2Type(ip.getType()) , addr); // http 代理
		try {
			realUrl = new URL(url);
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) realUrl.openConnection(proxy);
			connection.connect();
			responseCode = connection.getResponseCode();

		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
		
		if (responseCode == 200){
			flag =  true ;
		}
		
		return flag ;

	}

}
