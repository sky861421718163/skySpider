package com.sky.spider.advance.testCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestCollection {

	public static void main(String[] args) {

		Set set = new HashSet();
		int a = 2;
		
		set.add(a);
		Iterator ir = set.iterator();
		while (ir.hasNext()){
			System.out.println(ir.next());
			
		}
		
		List list = new ArrayList<>();
	}

}
