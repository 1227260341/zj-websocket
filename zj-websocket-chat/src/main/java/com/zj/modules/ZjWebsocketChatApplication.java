package com.zj.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.zj.modules.mapper","com.zj.modules.domain","com.zj.modules.controller"})
//@MapperScan("com.zj.modules.mapper")
public class ZjWebsocketChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZjWebsocketChatApplication.class, args);
	}
}
