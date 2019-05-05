package com.sky.spider.advance;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashTableTest {
	public static void main(String[] args) {
		
		Hashtable<String, Object> ht = new Hashtable<>();
		
		ht.put("a", "aval");
		ht.put(null, "avalnull");
		System.out.println(ht.get(null));
		System.out.println(ht.get("a"));
		
		Map map = new HashMap<>();
		map.put(null, "sss");
		map.put(null, "sss2");
		
		System.out.println(map.get(null));
		
	}

}
