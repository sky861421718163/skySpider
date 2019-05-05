package com.sky.spider.advance.newInterface;

public class Test2 {

	public static void main(String[] args) {
		
		//因为 Thread 有这个构造方法 Thread(Runnable target)
		Thread t = new Thread(  new  Runnable() {
			public void run() {
				System.out.println("run");
			}
		});
		
		
		t.start();

	}

}
