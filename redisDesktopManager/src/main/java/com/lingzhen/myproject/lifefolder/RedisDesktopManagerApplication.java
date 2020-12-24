package com.lingzhen.myproject.lifefolder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lingzhen.myproject.lifefolder.mapper")
public class RedisDesktopManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisDesktopManagerApplication.class, args);
	}

}
