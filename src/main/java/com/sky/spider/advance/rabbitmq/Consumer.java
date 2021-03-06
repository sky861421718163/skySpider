package com.sky.spider.advance.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer {

	private final static String QUEUE_NAME = "test_queue";

	public static void main(String[] argv) throws Exception {

		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
		Channel channel = connection.createChannel();

		// 声明队列(如果你已经明确的知道有这个队列,那么下面这句代码可以注释掉,如果不注释掉的话,也可以理解为消费者必须监听一个队列,如果没有就创建一个)
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		/*
		 * 监听队列 参数1:队列名称 参数2：是否发送ack包，不发送ack消息会持续在服务端保存，直到收到ack。
		 * 可以通过channel.basicAck手动回复ack 参数3：消费者
		 */
		channel.basicConsume(QUEUE_NAME, true, consumer);

		// 获取消息
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [消费者] Received '" + message + "'");
		}
	}
}