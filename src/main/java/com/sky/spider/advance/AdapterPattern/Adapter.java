package com.sky.spider.advance.AdapterPattern;
/**
 * 适配器类
 * 通过适配器，我们可以让两个并不相关的类连接起来。
 * 这是第一种适配的方式
 * */
public class Adapter extends Adaptee implements Target{

    public void execute() {
        //将请求给目标接口的实现
        super.request();
    }

}