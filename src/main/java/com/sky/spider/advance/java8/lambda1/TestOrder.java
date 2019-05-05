package com.sky.spider.advance.java8.lambda1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TestOrder {

	public static void main(String[] args) {
		List<User> list = new ArrayList<User>();
		list.add(new User("shangshan",98));
		
		list.add(new User("lisi",60));
		
		list.add(new User("wangwu",30));
		
		//排序 根据分数排序
		
		Collections.sort(list, Comparator.comparing(User::getScore));
		
		 Iterator<User> i = list.iterator();
		 while (i.hasNext()){
			 System.out.println(i.next());
		 }

	}

}
