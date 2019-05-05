package com.sky.spider.advance.redismq;

import redis.clients.jedis.Jedis;

public class MyJedisFactory {
	
	public static Jedis  getJedis(){
		
		 Jedis jedis = new Jedis("10.10.50.228",6379);
		 jedis.auth("123456");
		 return jedis;
	}
	
	public static void main(String[] args) {
		Jedis jedis = MyJedisFactory.getJedis();
		jedis.set("qwe","1234");
		System.out.println(jedis.get("qwe"));
		
	}

}
