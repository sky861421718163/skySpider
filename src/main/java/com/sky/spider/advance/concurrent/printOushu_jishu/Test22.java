package com.sky.spider.advance.concurrent.printOushu_jishu;

public class Test22 {

	 private static final int total = 100;
	 
	 private static volatile int i = 0 ;
	 
	 private static Object lockObj = new Object();
	 
	public static void main(String[] args) {
		
		Thread one = new Thread(){
			@Override
			public void run() {
				synchronized (lockObj) {
					while(i<=total){
						if(i%2 ==0){
							System.out.println("偶数："+i);
							i++;
							lockObj.notifyAll();
							try {
								lockObj.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					}
				}
				
				
			};
		};
		
		
		Thread two = new Thread(){
			@Override
			public void run() {
				synchronized (lockObj) {
					while(i<=total){
						if(i%2 ==1){
							System.out.println("奇数："+i);
							i++;
							lockObj.notifyAll();
							try {
								lockObj.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					}
				}
				
				
			};
		};
		
		one.start();
		two.start();
		
		
		

	}

}
