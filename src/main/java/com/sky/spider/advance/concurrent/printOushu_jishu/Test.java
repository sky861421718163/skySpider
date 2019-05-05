package com.sky.spider.advance.concurrent.printOushu_jishu;

public class Test {

	private static final int total = 100;

	private static volatile int i = 0;

	private static Object lockObj = new Object();

	public static void main(String[] args) {
		// 你结束了吗？ 等一下 我看看，好的 ,行了 写完了，好的

		Thread one = new Thread() {

			@Override
			public void run() {
				while (i <= total) {
					synchronized (lockObj) {

						if (i % 2 == 1) {

							System.out.println("奇数：" + i);
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

			}

		};
		Thread two = new Thread() {

			@Override
			public void run() {
				while (i <= total) {
					synchronized (lockObj) {

						if (i % 2 == 0) {

							System.out.println("偶数：" + i);
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