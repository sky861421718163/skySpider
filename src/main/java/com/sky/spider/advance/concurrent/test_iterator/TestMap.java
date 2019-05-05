package com.sky.spider.advance.concurrent.test_iterator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestMap {
	
	public static void main(String[] args) {
		// 这样做的话 类似与 hashtable,是对所有调用map 的方法进行加锁
		Map map1 = Collections.synchronizedMap(new HashMap<>());
		
		// LinkedHashMap (遍历时 ，是按放入的顺序 输出的)
		Map<String,Integer> map2 = new LinkedHashMap<String,Integer>();
		
		map2.put("1", 1);
		map2.put("2", 2);
		map2.put("34", 3);
		map2.put("4", 4);
		map2.put("5", 5);
		
		
		Iterator<Map.Entry<String, Integer>> i =map2.entrySet().iterator();
		while (i.hasNext()){
			Map.Entry<String, Integer> entry = i.next();
			System.out.println(entry.getKey()  + "-"  + entry.getValue());
			
		}
		System.out.println( "---------------" );
		// 1-1
		// 2-2
		// 34-3
		// 4-4
		// 5-5 
		
		//// HashMap(遍历时 ，非按放入的顺序 输出的)
		
		
	Map<String,Integer> map3 = new HashMap<String,Integer>();
		
		map3.put("1", 1);
		map3.put("2", 2);
		map3.put("34", 3);
		map3.put("4", 4);
		map3.put("5", 5);
		
		Iterator<Map.Entry<String, Integer>> i2 =map3.entrySet().iterator();
		while (i2.hasNext()){
			Map.Entry<String, Integer> entry = i2.next();
			System.out.println(entry.getKey()  + "-"  + entry.getValue());
			
		}
		
		//1-1
		//34-3
		//2-2
		//4-4
		//5-5
		
		
	}

}
