package com.sky.spider.domain;

/**
 * 响应结果
 *@ClassName:ResponseResult.java
 *@ClassDescribe:
 *@createPerson:sky
 *@createDate:2018年1月26日 上午11:08:56
 *@version
 */
public class ResponseResult {
	
	/**
	 * 响应编码 200为成功   
	 */
	private int responesCode ;
	
	/**
	 * 成功响应的结果
	 */
	private String responesStr;
	
	//返回的cookie
	private String responesCookie;
	
	

	/**
	 * 访问失败的错误信息
	 */
	private String errorMsg ;



	public int getResponesCode() {
		return responesCode;
	}



	public void setResponesCode(int responesCode) {
		this.responesCode = responesCode;
	}


	

	public String getResponesCookie() {
		return responesCookie;
	}



	public void setResponesCookie(String responesCookie) {
		this.responesCookie = responesCookie;
	}



	public String getResponesStr() {
		return responesStr;
	}



	public void setResponesStr(String responesStr) {
		this.responesStr = responesStr;
	}



	public String getErrorMsg() {
		return errorMsg;
	}



	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	@Override
	public String toString() {
		return "ResponseResult [responesCode=" + responesCode + ", responesStr=" + responesStr + ", errorMsg="
				+ errorMsg + "]";
	}
	
	
	

}
