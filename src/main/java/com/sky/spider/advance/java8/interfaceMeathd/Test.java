package com.sky.spider.advance.java8.interfaceMeathd;

public class Test {

	public static void main(String[] args) {
		Formula formula = new Formula() {
		    @Override
		    public double calculate(int a) {
		        return sqrt(a * 100);
		    }
		};
		System.out.println(  formula.calculate(100)  );     // 100.0
		System.out.println(  formula.sqrt(16)  );           // 4.0
		

	}

}
