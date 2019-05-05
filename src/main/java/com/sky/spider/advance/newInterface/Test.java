package com.sky.spider.advance.newInterface;

public class Test {

	public static void main(String[] args) {
		
		MyInterface mi= new MyInterface(){  //接口不能new ，不过可以生成一个匿名类，省略了写一个具体类实现接口的开销。
			                                //这种实现接口方式其实就是一个 实现了这个接口的匿名类。
			@Override
			public int add(int a, int b) {
				
				return a + b;
			}
		} ;
		
		int c =mi.add(2, 6);
		System.out.println(c); //8
	}

}
