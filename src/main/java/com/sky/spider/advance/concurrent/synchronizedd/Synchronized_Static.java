package com.sky.spider.advance.concurrent.synchronizedd;

/**
 * 
 * Synchronized 对 static 方法 
 * 相当于对一个 当前类 加锁 ，如果 两个线程获得这个类锁。
 * public synchronized static void m1 (){} 表示 必须获得类锁 才能执行此方法 
 * A线程获得了类锁  ，B线程就必须等到A线程运行完成释放了锁 ，才能执行B线程
 * 
 * 类似 Synchronized static 的还有   Synchronized（某一类.class）,也是获得类锁
 * @author sky
 *
 */
public class Synchronized_Static {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(){
			@Override
			public void run() {
				m1 ();
				
			};
		};
		
		Thread t2 = new Thread(){
			@Override
			public void run() {
				m2 ();
				
			};
		};
		
		
		t1.setName("A");
		t2.setName("B");
		t1.start();
		t2.start();
		
		
		

	}
	
	public synchronized static void m1 (){
		
		System.out.println(Thread.currentThread().getName() + "--start--"+ System.currentTimeMillis());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "--end--" + System.currentTimeMillis());
		
	} 
	
	public synchronized static void m2 (){
		
		System.out.println(Thread.currentThread().getName() + "--start--"+ System.currentTimeMillis());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "--end--" + System.currentTimeMillis());
		
	} 

}
