package com.sky.spider.advance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 计算 1+..100 的结果 分段计算
 *@ClassName:ThreadAddFuture.java
 *@ClassDescribe:
 *@createPerson:SKY
 *@createDate:2018年6月6日 上午10:50:49
 *@version
 */
public class ThreadAddFuture {
	
	public static List<Future<Integer>> list = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadAddFuture add = new ThreadAddFuture();
		ExecutorService pool = Executors.newFixedThreadPool(4);
		for (int i =1 ;i<=76;){
			CounterThread thread = add.new CounterThread(i,i+24);
			Future<Integer> future = pool.submit(thread);
			i+=25;
			list.add(future);
		}
		int sum = 0;
		for (int i =0 ;i<list.size() ;i++){
			sum += list.get(i).get();
		}
		System.out.println("最终结果：" + sum);
		pool.shutdown();
		
	}
	
	

	class CounterThread implements Callable<Integer>{
		
		private int begin;
		private int end;
		private int sum = 0;
		
		public CounterThread(int begin,int end){
			this.begin = begin;
			this.end = end;
			
		}

		@Override
		public Integer call() throws Exception {
			for (int i =begin ;i<= end ;i++){
				sum += i;
			}
			System.out.println(Thread.currentThread().getName()+"  sum:"+ sum);
			return sum;
		}
		
		
	}

}
