package com.sky.spider.domain;

import java.io.Serializable;

/**
 * 同城旅游网酒店实体
 *@ClassName:MonitorMainTc.java
 *@ClassDescribe:
 *@createPerson:sky
 *@createDate:2018年1月26日 下午1:29:56
 *@version
 */
public class MonitorMainTc implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7483195461446798265L;

	/**
     * id-监测对象
     */
    private long id;
    
    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 同城旅游网酒店编号
     */
    private String tcId;
    
    
    /**
     * 所在城市
     */
    private String city;
    
    /**
     * 对应同城旅游网址
     */
    private String tcUrl;


    /**
     * 评分
     */
    private String dpScore;

    /**
     * 点评总数
     */
    private String dpTotalNum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTcId() {
		return tcId;
	}

	public void setTcId(String tcId) {
		this.tcId = tcId;
	}

	public String getTcUrl() {
		return tcUrl;
	}

	public void setTcUrl(String tcUrl) {
		this.tcUrl = tcUrl;
	}

	

	public String getDpScore() {
		return dpScore;
	}

	public void setDpScore(String dpScore) {
		this.dpScore = dpScore;
	}

	public String getDpTotalNum() {
		return dpTotalNum;
	}

	public void setDpTotalNum(String dpTotalNum) {
		this.dpTotalNum = dpTotalNum;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return " 酒店名称=" + hotelName + ", 所在城市=" + city + ", 同城旅游网链接="
				+ tcUrl + ", 评分=" + dpScore + ", 评论总数=" + dpTotalNum ;
	}



	

    
    

}