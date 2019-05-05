package com.sky.spider.domain;

import java.io.Serializable;

public class TempMonitorMain implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7483195461446798265L;

	/**
     * id-监测对象
     */
    private long id;

    /**
     * 名称
     */
    private String name;


    /**
     * 官网链接
     */
    private String websit;

    /**
     * 官网访问数
     */
    private int pvNum;
    
    /**
     * 360月指数
     */
    private String index360;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsit() {
		return websit;
	}

	public void setWebsit(String websit) {
		this.websit = websit;
	}

	public int getPvNum() {
		return pvNum;
	}

	public void setPvNum(int pvNum) {
		this.pvNum = pvNum;
	}

	public String getIndex360() {
		return index360;
	}

	public void setIndex360(String index360) {
		this.index360 = index360;
	}

	@Override
	public String toString() {
		return "id=" + id + ", 品牌名称=" + name + " 360指数=" + index360 ;
	}

	
    
    

}