package com.zj.modules.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zj.modules.domain.User;
import com.zj.modules.mapper.UserMapper;


@RestController
@RequestMapping("/test")
public class test {

	@Resource
	private UserMapper userMapper;
	
	@RequestMapping("/bb")
	public Object bb(User user) {
//		List<User> users = userMapper.queryByPage(user);
		return user;
	}
}
