package com.sky.spider.advance.dynamicProxy_cglib;
 
 
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
 
import java.lang.reflect.Method;
 
 
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
    	
        System.out.println("++++++前置++++++");
        
      //  System.out.println(method.getName());
        
        Object o1 = methodProxy.invokeSuper(o, args);
        
        System.out.println("++++++后置++++++");
        
        return o1;
        
    }
}
