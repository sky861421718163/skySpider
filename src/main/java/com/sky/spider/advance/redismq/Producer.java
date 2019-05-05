package com.sky.spider.advance.redismq;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
 
/**
 * 消息生产者
 * @author yamikaze
 */
public class Producer extends Thread {
 
    public static final String MESSAGE_KEY = "message:queue";
    private Jedis jedis;
    private String producerName;
    private volatile int count;
 
    public Producer(String name) {
        this.producerName = name;
        init();
    }
 
    private void init() {
        jedis = MyJedisFactory.getJedis();
    }
 
    public void putMessage(String message) {
        Long size = jedis.lpush(MESSAGE_KEY, message);
        System.out.println(producerName + ": 当前未被处理消息条数为:" + size);
        count++;
    }
 
    public int getCount() {
        return count;
    }
 
    @Override
    public void run() {
        try {
                putMessage(UUID.randomUUID().toString());
                TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) throws InterruptedException{
        Producer producer = new Producer("myProducer");
        producer.start();
 
        for(; ;) {
            System.out.println("main : 已存储消息条数:" + producer.getCount());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}