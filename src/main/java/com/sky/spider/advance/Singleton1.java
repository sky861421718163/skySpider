package com.sky.spider.advance;

public class Singleton1 {
	
	private static Singleton1 singletObj = null;
	
	private Singleton1(){	
	};
	
	public static synchronized Singleton1 getInstance(){
		
		if(null ==singletObj ){
			singletObj = new Singleton1();
		}
		return singletObj ;
		
	}
	
	

}
