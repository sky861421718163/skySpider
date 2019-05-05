package com.sky.spider.advance.concurrent.synchronizedd;

//三个线程 按顺序 执行2 使用join
public class OrderRunTest2 {


	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "run");
				
			}

		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "run");
			}

		};

		Thread t3 = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "run");
			}

		};

		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		t1.start();
		t1.join();//t1抢cpu。与main线程 竞争
		t2.start();
		t2.join();
		t3.start();

	}

}
