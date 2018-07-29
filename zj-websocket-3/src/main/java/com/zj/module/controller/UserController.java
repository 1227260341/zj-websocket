package com.zj.module.controller;




import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zj.module.mapper.UserMapper;



//import com21cnjy.crowdsourcing.biz.service.UserService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-07-28
 */
@RestController
@RequestMapping("/user")
public class UserController{

	@Resource
	private UserMapper mapper;
//	//@Resource
//	//private UserService userService;
//	
//	
//
//	/**
//     *  获取列表
//     * ---------------------------------
//     *  zhouzhenjang123
//     *  Create in 2018-07-28
//     */
//	@RequestMapping("/queryByPage")
//	public @ResponseBody Object queryByPage(User user){
//		Map returnMap = new HashMap();
//		try {
//			List<User> users = mapper.queryByPage(user);
//			return users;
//		} catch (Exception e) {
//	       	e.printStackTrace();
//		}
//		
//		return null;
//	}

	
	
}

