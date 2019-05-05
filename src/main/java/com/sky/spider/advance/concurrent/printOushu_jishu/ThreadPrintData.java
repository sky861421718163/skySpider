package com.sky.spider.advance.concurrent.printOushu_jishu;

//两个线程交替打印1-100的整数，一个打印奇数，一个打印偶数，要求输出结果有序。
public class ThreadPrintData {


    private static Object lock = new Object();

    private static volatile Integer i = 0;
    private static final int TOTAL = 100;


    public static void main(String[] args) {

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                while (i <= TOTAL) {
                    synchronized (lock) {
                        if (i % 2 == 1) {
                            System.out.println(Thread.currentThread().getName() + "打印：   " + i++);
                        } else {
                            lock.notifyAll();
                        }
                    }

                }
            }
        };

        thread1.setName("奇数线程");
        Thread thread2 = new Thread() {

            @Override
            public void run() {
                while (i <= TOTAL) {
                    synchronized (lock) {
                        if (i % 2 == 0) {
                            System.out.println(Thread.currentThread().getName()+ "打印：  " + i++);
                        } else {
                            lock.notifyAll();
                        }
                    }

                }
            }
        };
        thread2.setName("偶数线程");

        thread1.start();
        thread2.start();
    }
}