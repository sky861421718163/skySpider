package com.sky.spider.advance.concurrent.synchronizedd;

//三个线程 按顺序 执行 使用 synchronized
public class OrderRunTest {

	private static volatile int i = 1;
	private static String str = "lock";

	public static void main(String[] args) {

		Thread t1 = new Thread() {
			@Override
			public void run() {
				synchronized (str) {
					while (i != 1) {
						try {
							str.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					if (i == 1) {
						System.out.println(Thread.currentThread().getName() + "run");
						i++;
						str.notifyAll();
					}
				}

			}

		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				synchronized (str) {
					while (i != 2) {
						try {
							str.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					if (i == 2) {
						System.out.println(Thread.currentThread().getName() + "run");
						i++;
						str.notifyAll();
					}
				}

			}

		};

		Thread t3 = new Thread() {
			@Override
			public void run() {
				synchronized (str) {
					while (i != 3) {
						try {
							str.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					if (i == 3) {
						System.out.println(Thread.currentThread().getName() + "run");
						i++;
						str.notifyAll();
					}
				}

			}

		};

		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		t1.start();
		t2.start();
		t3.start();

	}

}
