package com.sky.spider.advance.java8.acepetMethod;

import java.util.Arrays;
import java.util.List;

public class AcceptMethod {
 
    public static void  printValur(String str){
        System.out.println("println value : "+str);
    }
 
    public static void main(String[] args) {
        List<String> al = Arrays.asList("a","b","c","d");
        for (String a: al) {
            AcceptMethod.printValur(a);
        }
      //下面的for each循环和上面的循环是等价的 
        al.forEach(x->{
            AcceptMethod.printValur(x);
        });
    }
}