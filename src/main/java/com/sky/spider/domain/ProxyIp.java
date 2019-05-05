package com.sky.spider.domain;

import java.io.Serializable;
import java.util.Date;

public class ProxyIp implements  Serializable{
	
	/**
	 * 类型  1 ： http    2:https   3:socks4/5
	 */
	private int type;
	
	/**
	 * ip地址
	 */
	private String ip;
	
	/**
	 * 端口
	 */
	private int port;
	
	/*ip和port
	 * 
	 */
	private String ipPort;
	
	
	
	
	/**
	 * ip来源
	 */
	private String webSite;
	
	
	/**
	 * 是匿名？  true： 匿名     false ：非匿名
	 */
	private boolean isAnonymous;
	
	/**
	 * 存活时长  单位为分钟
	 */
	private int aliveTimeLength ;
	
	/**
	 * 响应速度  单位为秒
	 */
	private float responseSpeed ;
	
	
	/**
	 * 服务器地址 国家
	 */
	private String address_nation ;
	
	/**
	 * 服务器地址 省
	 */
	private String address_province ;
	
	
	/**
	 * 服务器地址 市
	 */
	private String address_city ;
	
	
	/**
	 * 服务器地址 区
	 */
	private String address_district ;
	
	/**
	 * 服务器地址全称
	 */
	private String address_str ;
	
	
	/**
	 *  运营商
	 */
	private String netOperator ;
	
	
	/**
	 *  爬取时间
	 */
	private Date crawlerTime ;
	
	
	/**
	 *  自己验证是否有效
	 */
	private boolean selfIsValid ;
	
	/**
	 *  自己验证时间
	 */
	private Date selfValidTime ;
	
	/**
	 *  使用时是否有效
	 */
	private boolean useIsValid ;
	
	/**
	 *  被有效使用的次数
	 */
	private int countUseIsValid ;
	
	/**
	 *  上一次有效使用的时间
	 */
	private Date lastValidUseTime ;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public int getAliveTimeLength() {
		return aliveTimeLength;
	}

	public void setAliveTimeLength(int aliveTimeLength) {
		this.aliveTimeLength = aliveTimeLength;
	}

	public float getResponseSpeed() {
		return responseSpeed;
	}

	public void setResponseSpeed(float responseSpeed) {
		this.responseSpeed = responseSpeed;
	}

	public String getAddress_nation() {
		return address_nation;
	}

	public void setAddress_nation(String address_nation) {
		this.address_nation = address_nation;
	}

	public String getAddress_province() {
		return address_province;
	}

	public void setAddress_province(String address_province) {
		this.address_province = address_province;
	}

	public String getAddress_city() {
		return address_city;
	}

	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}

	public String getAddress_district() {
		return address_district;
	}

	public void setAddress_district(String address_district) {
		this.address_district = address_district;
	}

	public String getNetOperator() {
		return netOperator;
	}

	public void setNetOperator(String netOperator) {
		this.netOperator = netOperator;
	}

	public Date getCrawlerTime() {
		return crawlerTime;
	}

	public void setCrawlerTime(Date crawlerTime) {
		this.crawlerTime = crawlerTime;
	}

	public boolean isSelfIsValid() {
		return selfIsValid;
	}

	public void setSelfIsValid(boolean selfIsValid) {
		this.selfIsValid = selfIsValid;
	}

	public Date getSelfValidTime() {
		return selfValidTime;
	}

	public void setSelfValidTime(Date selfValidTime) {
		this.selfValidTime = selfValidTime;
	}

	public boolean isUseIsValid() {
		return useIsValid;
	}

	public void setUseIsValid(boolean useIsValid) {
		this.useIsValid = useIsValid;
	}

	public int getCountUseIsValid() {
		return countUseIsValid;
	}

	public void setCountUseIsValid(int countUseIsValid) {
		this.countUseIsValid = countUseIsValid;
	}

	public Date getLastValidUseTime() {
		return lastValidUseTime;
	}

	public void setLastValidUseTime(Date lastValidUseTime) {
		this.lastValidUseTime = lastValidUseTime;
	}

	public String getAddress_str() {
		return address_str;
	}

	public void setAddress_str(String address_str) {
		this.address_str = address_str;
	}

	public String getIpPort() {
		return ipPort;
	}

	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}

	@Override
	public String toString() {
		return "ProxyIp [ip=" + ip + ", port=" + port + ", ipPort=" + ipPort + ", type=" + type + ", webSite=" + webSite
				+ ", isAnonymous=" + isAnonymous + ", aliveTimeLength=" + aliveTimeLength + ", responseSpeed="
				+ responseSpeed + ", address_nation=" + address_nation + ", address_province=" + address_province
				+ ", address_city=" + address_city + ", address_district=" + address_district + ", address_str="
				+ address_str + ", netOperator=" + netOperator + ", crawlerTime=" + crawlerTime + ", selfIsValid="
				+ selfIsValid + ", selfValidTime=" + selfValidTime + ", useIsValid=" + useIsValid + ", countUseIsValid="
				+ countUseIsValid + ", lastValidUseTime=" + lastValidUseTime + "]";
	}
	
	

}
