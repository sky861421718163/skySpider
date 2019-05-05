package com.sky.spider.advance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class ChangeArgsTest {
	
	public static void main(String[] args) {
		ChangeArgsTest changeArgsTest = new ChangeArgsTest();
		//changeArgsTest.advance("1","2");
		changeArgsTest.testRemove7();
		
		
		LinkedList<Object> list = new LinkedList<Object>();
		ArrayList<Object> arrayList = new ArrayList<>();
		
		 TreeSet<Object> treeSet = new TreeSet<Object>();
		 
		 TreeMap treeMap = new TreeMap();
		
		
		
	}
	
	/**
	 *@ClassName:ChangeArgsTest.java
	 *@ClassDescribe:
	 *@auth:SKY
	 *@createDate:2018年6月26日 上午9:23:32
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@param str
	 */
	private void test(String... str){
		 for (int i = 0; i < str.length; i++) {
	            System.out.println(str[i]);
	        }
	} 
	
	
	@Test
	public void testRemove2(){
	    List<Integer> integers = new ArrayList<>(5);
	    integers.add(1);
	    integers.add(2);
	    integers.add(2);
	    integers.add(4);
	    integers.add(5);

	    for (int i = 0; i < integers.size(); i++) {
	        if (integers.get(i)%2==0){
	            integers.remove(i);
	        }
	    }

	    System.out.println(integers);
	}
	
	
	@Test
	public void testRemove4(){
	    List<String> strings = new ArrayList<>();
	    strings.add("a");
	    strings.add("b");
	    strings.add("c");
	    strings.add("d");

	    for (String string : strings) {
	    	if(string.equals("b")){
	    		strings.remove(string);
	    	}
	        
	    }
	}
	
	
	@Test
	public void testRemove6(){
	    List<String> strings = new ArrayList<>();
	    strings.add("a");
	    strings.add("b");
	    strings.add("c");
	    strings.add("d");

	    Iterator<String> iterator = strings.iterator();
	    while (iterator.hasNext()){
	        String next = iterator.next();
	        strings.remove(next);
	    }

	    System.out.println(strings);
	}
	
	@Test
	public void testRemove7(){
	    List<String> strings = new ArrayList<>();
	    strings.add("a");
	    strings.add("b");
	    strings.add("c");
	    strings.add("d");

	    Iterator<String> iterator = strings.iterator();
	    while (iterator.hasNext()){
	        String next = iterator.next();
	        if(next.equals("b")){
	        	iterator.remove();
	        }
	        
	    }

	    System.out.println(strings);
	}
	
	
	@Test
	public void testRemove8(){
        ArrayList<String> arr=new ArrayList<String>();  
        arr.add("a");  
        arr.add("b");  
        arr.add("c");  
        for(int i=0;i<arr.size();i++){  
            if(arr.get(i).equals("a")||arr.get(i).equals("b")){  
                arr.remove(arr.get(i));  
            }  
        }  
        System.out.println(arr.toString());  
	}
	
	@Test
	public void testRemove9(){
        ArrayList<String> arr=new ArrayList<String>();  
        arr.add("a");  
        arr.add("b");  
        arr.add("c");  
        for(int i=arr.size()-1;i>=0;i--){  
            if(arr.get(i).equals("a")||arr.get(i).equals("b")){  
                arr.remove(i);  
            }  
        }  
        System.out.println(arr.toString());  
	}
	
	
	@Test
	public void testRemove10(){
		
		 ArrayList<String> arr=new ArrayList<String>();  
	        arr.add("a");  
	        arr.add("b");  
	        arr.add("c");  
	          
	        Iterator<String> it=arr.iterator();  
	        while(it.hasNext()){  
	            String s=it.next();  
	            if(s.equals("b")){  
	                it.remove();  
	            }  
	        }  
	        System.out.println(arr.toString());  
		
	}
	
	
	

}
