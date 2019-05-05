package com.sky.spider.advance.concurrent.myThreadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @program: skySpider
 * @description:
 * @author: sky
 * @create: 2018-12-18 19:30
 **/
public class TestThreadPool {
    public static void main(String[] args) {
        /*ExecutorService service = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new BlockingDeque<Runnable>(1024),);
*/
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("example-%d").setDaemon(true).build();
        BlockingQueue blockingQueue=new ArrayBlockingQueue<>(2);
        ExecutorService poolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, blockingQueue, threadFactory);
       //线程放入队列的原因：提交到线程池的任务数，大于当前闲置的线程数。
        TaskThread thread1 = new TaskThread();
        TaskThread thread2 = new TaskThread();
        TaskThread thread3 = new TaskThread();
        poolExecutor.execute(thread1);
        poolExecutor.execute(thread2);
        poolExecutor.execute(thread3);

        System.out.println("22222222");

    }



}
