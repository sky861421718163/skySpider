package com.sky.spider.advance.nio.io2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.sky.spider.advance.nio.io.ServerHandler;

public class Server {

    final static int PORT = 7788;

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        BufferedReader in = null;
        PrintWriter out = null;
            server = new ServerSocket(PORT);
            System.out.println("server start");
            Socket socket = null;
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
            while(true){
                socket = server.accept();
                executorPool.execute(new ServerHandler(socket));
            }
        }
        }
