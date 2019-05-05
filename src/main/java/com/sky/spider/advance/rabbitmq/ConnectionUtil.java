package com.sky.spider.advance.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class ConnectionUtil {
	
	public static Connection  getConnection(){
		
		 ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("127.0.0.1");
	        factory.setPort(5672);
	        factory.setUsername("root");
	        factory.setPassword("root");
	        factory.setVirtualHost("/");

	        Connection connection =null;
			try {
				connection = factory.newConnection();
			} catch (Exception e) {
				e.printStackTrace();
			} 
	        
	        return connection ;
	}

}
