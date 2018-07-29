package com.zj.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zj.module.controller", "com.zj.module.domain"
		, "com.zj.module.mapper"})
public class ZjWebsocket3Application {

	public static void main(String[] args) {
		SpringApplication.run(ZjWebsocket3Application.class, args);
	}
}
