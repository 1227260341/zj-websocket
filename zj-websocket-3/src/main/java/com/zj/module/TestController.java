package com.zj.module;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/hello")
    public String hello() {
    	return "aaaa";
    }
	
	@RequestMapping("/sendMsg")
    public void sendMsg(String msg) {
    	try {
			WebSocket.sendInfo(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
}