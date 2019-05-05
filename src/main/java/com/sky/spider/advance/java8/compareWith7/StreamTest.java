package com.sky.spider.advance.java8.compareWith7;

import java.util.ArrayList;
import java.util.List;

/**
 * 在 i 的数量级 在千万以上 流的处理快于 普通循环
 * 在i 小于千万 时 ，普通循环快于流的处理
 * @author sky
 *
 */

public class StreamTest {
	static List<Integer> myList = new ArrayList<>();

	public static void main(String[] args) {
		for (int i = 0; i < 500000; i++)
			myList.add(i);
		int result = 0;
		
		
		long loopStartTime = System.currentTimeMillis();
		for (int i : myList) {
			if (i % 2 == 0)
				result += i;
		}
		long loopEndTime = System.currentTimeMillis();
		System.out.println(result);
		System.out.println("Loop total Time = " + (loopEndTime - loopStartTime));
		
		
		long streamStartTime = System.currentTimeMillis();
		System.out.println(myList.stream().filter(value -> value % 2 == 0).mapToInt(Integer::intValue).sum());
		long streamEndTime = System.currentTimeMillis();
		System.out.println("Stream total Time = " + (streamEndTime - streamStartTime));
		
		
		
		long parallelStreamStartTime = System.currentTimeMillis();
		System.out.println(myList.parallelStream().filter(value -> value % 2 == 0).mapToInt(Integer::intValue).sum());
		long parallelStreamEndTime = System.currentTimeMillis();
		System.out.println("Parallel Stream total Time = " + (parallelStreamEndTime - parallelStreamStartTime));
	}
}