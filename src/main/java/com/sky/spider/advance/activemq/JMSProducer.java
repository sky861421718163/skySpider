package com.sky.spider.advance.activemq;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;


/**
 * Created by CY on 2017/8/31.
 */
public class JMSProducer {
    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKER_URL = "tcp://116.62.34.79:61616";


    public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

        try {
            //连接
            Connection connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //消息目的地
            Destination destination = session.createQueue("hello");
            //消息生产者
            MessageProducer producer = session.createProducer(destination);
            //设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //发送消息
            for (int i = 0; i < 10; i++) {
                //创建一条文本消息
                TextMessage message = session.createTextMessage("ActiveMQ啦啦啦：这是第 " + i + " 条消息");
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 120*1000);
                //生产者发送消息
                producer.send(message);
            }

            session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
