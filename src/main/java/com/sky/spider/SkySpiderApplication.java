package com.sky.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages = "com.sky.spider.dao")
@ComponentScan({ "com.sky.spider"})
public class SkySpiderApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SkySpiderApplication.class, args);
	}

}
