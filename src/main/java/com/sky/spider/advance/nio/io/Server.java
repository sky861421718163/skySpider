package com.sky.spider.advance.nio.io;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	final static int PROT = 7788;

	public static void main(String[] args) throws IOException {
		ServerSocket server = null;
		server = new ServerSocket(PROT);
		System.out.println(" server start .. ");
		// 进行阻塞
		Socket socket = server.accept();
		// 新建一个线程执行客户端的任务
		new Thread(new ServerHandler(socket)).start();
	}

}
