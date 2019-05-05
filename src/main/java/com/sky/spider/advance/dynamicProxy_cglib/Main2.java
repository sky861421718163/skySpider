package com.sky.spider.advance.dynamicProxy_cglib;
 
import net.sf.cglib.proxy.Enhancer;
 
 
 
public class Main2 {
    public static void main(String[] args) {
    	//System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        CglibProxy cglibProxy = new CglibProxy();
 
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);
 
        UserService o = (UserService)enhancer.create();
        o.getName(1);
        o.getAge(1);
    }
}
