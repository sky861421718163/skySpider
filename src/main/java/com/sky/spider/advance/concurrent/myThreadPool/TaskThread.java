package com.sky.spider.advance.concurrent.myThreadPool;

public class TaskThread implements  Runnable{


        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"running");
            System.out.println("------------");
        }
    }