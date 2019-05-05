package com.sky.spider.advance.java8.interfaceMeathd;

interface Formula {
	double calculate(int a);

	//定义默认方法 用 default
	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}