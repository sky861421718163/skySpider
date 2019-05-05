package com.sky.spider.advance.asynTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 1、场景
　　最近做项目的时候遇到了一个小问题:从前台提交到服务端A,A调用服务端B处理超时,原因是前端一次请求往db插1万数据,插完之后会去清理缓存、发送消息。

服务端的有三个操作 a、插DB b、清理cache  c、发送消息。1万条数据,说多不多,说少不少.况且不是单单insert。出现超时现象,不足为奇了吧~~

2、分析
　　如何避免超时呢?一次请求处理辣么多数据,可分多次请求处理,每次请求处理100条数据,可以有效解决超时问题. 为了不影响用户的体验,请求改为ajax 异步请求。

除此之外,仔细分析下场景. a 操作是本次处理的核心. 而b、c操作可以异步处理。换句话说,a操作处理完可以马上返回给结果, 不必等b、c处理完再返回。b、c操作可以放在一个异步任务去处理。
 * 
 * 
 * task2是个异步任务,执行到task2,主线程不会在task2 阻塞,不用等待task2 处理完,可以往下执行task3.
 * 
 * 其实   spring中使用@Async注解也可以 进行异步处理
 * 
 * @author sky
 *
 */
public class ExecutorDemo {

    private ExecutorService executor = Executors.newFixedThreadPool(1);
    
    public void asynTask() throws InterruptedException {
        
        
        executor.submit(new Runnable() {
            
            @Override
            public void run() {
                
                try {
                    Thread.sleep(10000);//方便观察结果
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                
                int sum = 0;
                for(int i = 0; i < 1000; i++) {
                    
                    sum += i;
                }
                
                System.out.println(sum);
            }
        });
        
    }
}