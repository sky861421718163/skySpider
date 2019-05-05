package com.sky.spider.advance;


import static  java.lang.Math.sqrt;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * JDK6 提供了一个简单的Http Server API,据此我们可以构建自己的嵌入式Http Server,(写一个自己的tomcat)
 * 它支持Http和Https协议,提供了HTTP1.1的部分实现，没有被实现的那部分可以通过扩展已有的Http Server API来实现,
 * 程序员必须自己实现HttpHandler接口,HttpServer会调用HttpHandler实现类的回调方法来处理客户端请求,
 * 在 这里,我们把一个Http请求和它的响应称为一个交换,包装成HttpExchange类,
 * HttpServer负责将HttpExchange传给 HttpHandler实现类的回调方法. 
 * @author my
 *
 */
//jdk1.6新特性
//jdk自带轻量级http server例子  http://127.0.0.1:8080/server
public class HttpServerDemo {
    public static void main(String[] args) throws IOException {
    	sqrt(2);
        InetSocketAddress addr = new InetSocketAddress(8080);
        HttpServer server = HttpServer.create(addr, 0);
 
        server.createContext("/server", new MyHandler());
        server.createContext("/server2", new MyHandler2());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server is listening on port 8080");
    }
}
 
class MyHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("GET")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain");
            // responseHeaders.set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, 0);
 
            OutputStream responseBody = exchange.getResponseBody();
            Headers requestHeaders = exchange.getRequestHeaders();
            Set<String> keySet = requestHeaders.keySet();
            Iterator<String> iter = keySet.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                List values = requestHeaders.get(key);
                String s = key + " = " + values.toString() + "\n";
                responseBody.write(s.getBytes());
            }
            responseBody.write("jdk自带轻量级http server例子".getBytes());
            responseBody.close();
        }
    }
}

class MyHandler2 implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("GET")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain");
            // responseHeaders.set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, 0);
 
            OutputStream responseBody = exchange.getResponseBody();
            Headers requestHeaders = exchange.getRequestHeaders();
            Set<String> keySet = requestHeaders.keySet();
            Iterator<String> iter = keySet.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                List values = requestHeaders.get(key);
                String s = key + " = " + values.toString() + "\n";
                responseBody.write(s.getBytes());
            }
            responseBody.write("jdk自带轻量级http server例子2".getBytes());
            responseBody.close();
        }
    }
}