package com.zj.modules.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;
import com.zj.modules.domain.UserChat;

public class ServerEncoder implements Encoder.Text<UserChat> {
 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public String encode(UserChat messagepojo) throws EncodeException {
		try {
			
			return  JSON.toJSONString(messagepojo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
 
}