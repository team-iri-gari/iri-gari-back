package com.ssafy.irigari;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.ssafy.*")
@MapperScan(basePackages = "com.ssafy.**.mapper")
@SpringBootApplication
public class IriGariBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(IriGariBackApplication.class, args);
	}
	
}
