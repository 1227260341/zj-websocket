package com.zj.modules.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zj.modules.domain.UserChat;
import com.zj.modules.mapper.UserChatMapper;

@Service
public class UserService {

	@Resource
	private UserChatMapper userChatMapper;
	
	public void addChatMessage(UserChat userChat) {
		userChatMapper.add(userChat);
	}
}
