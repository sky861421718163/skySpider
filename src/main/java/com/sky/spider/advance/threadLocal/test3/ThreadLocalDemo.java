package com.sky.spider.advance.threadLocal.test3;


/**
 * ThreadLocal，很多地方叫做线程本地变量，也有些地方叫做线程本地存储，其实意思差不多。可能很多朋友都知道ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。
 * 本地启动3个线程。都去访问  threadlocal 存储的信息，并各自去改变里面的值，但是却不会出现  错误，即（三个线程改动的值，出现了相互影响）。
 * 
 * @author sky
 *
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Integer> local=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;//初始值
        }
    };

    public static void main(String[] args) {
        Thread[] threads=new Thread[3];
        for(int i=0;i<3;i++){
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                 int mun=local.get();
                 for(int j=0;j<10;j++){
                     mun=mun+1;
                 }
                 local.set(mun);
                 System.out.println(Thread.currentThread().getName()+"==="+local.get());

                }
            });
        }
        for(Thread t:threads){
            t.start();
        }

    }


}