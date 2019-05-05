package com.sky.spider.advance.AdapterPattern;
public class Client {
    public static void main(String[] args) {
        Target t = new Adapter();//重点就是这句
        t.execute();

    }
}