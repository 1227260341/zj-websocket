package com.zj.modules.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;
import com.zj.modules.domain.FriendUser;
import com.zj.modules.domain.Group;
import com.zj.modules.domain.GroupUser;
import com.zj.modules.domain.User;
import com.zj.modules.domain.UserChat;
import com.zj.modules.mapper.FriendUserMapper;
import com.zj.modules.mapper.GroupMapper;
import com.zj.modules.mapper.GroupUserMapper;
import com.zj.modules.mapper.UserChatMapper;
import com.zj.modules.mapper.UserMapper;
import com.zj.modules.service.FilesConfigService;
import com.zj.modules.util.FileUtil;
import com.zj.modules.util.PropertiesUtil;


@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserMapper userMapper;
	@Resource
	private FriendUserMapper friendUserMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private UserChatMapper userChatMapper;
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private GroupUserMapper groupUserMapper;
	@Resource
	private FilesConfigService filesConfigService;
	
	/**
	 * 注册用户
	 * @return
	 */
	@RequestMapping("/register")
	public Object register(@RequestParam("file") MultipartFile file, User user) {
//		String userName = request.getParameter("userName");
//		String password = request.getParameter("password");
		Map returnMap = new HashMap<>();
		
		try {
			Integer filePath = filesConfigService.upload(file, "head", request);
			user.setHead(filePath + "");
			user.setType("0");
			userMapper.add(user);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("code", -1);
			returnMap.put("message", "注册失败！");
			return returnMap;
		}
		
		returnMap.put("code", 0);
		returnMap.put("message", "注册成功！");
		return returnMap;
	}
	
	@RequestMapping("/login")
	public Object login() {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		Map returnMap = new HashMap<>();
		
		User user = userMapper.login(userName, password);
		if (user == null) {
			returnMap.put("code", -1);
			returnMap.put("message", "登录失败，用户名或密码失败！");
			return returnMap;
		} 
		
		if (PropertiesUtil.verifyString(user.getHead())) {
			String fileUrl = filesConfigService.getFullPathById(Integer.parseInt(user.getHead()));
			user.setHeadUrl(fileUrl);
		}
		
		//写入session
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user);
		
		System.out.println("sessionI=================" + session.getId());
		
		returnMap.put("code", 0);
		returnMap.put("data", user);
		returnMap.put("message", "登录成功！");
		return returnMap;
	}
	
	/**
	 * 获取陌生人
	 * @return
	 */
	@RequestMapping("/getStranger")
	public Object getStranger() {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		if (loginUser == null) {
			returnMap.put("code", 1);
			returnMap.put("message", "session过期，请重新登录！");
			return returnMap;
		}
		
		List<User> strangeUser = userMapper.getStranger(loginUser.getId());
		if (PropertiesUtil.verifyList(strangeUser)) {
			for (User user : strangeUser) {
				if (PropertiesUtil.isEmptyString(user.getHead())) {
					
				}
				String head = filesConfigService.getFullPathById(Integer.parseInt(user.getHead()));
				user.setHeadUrl(head);
			}
		}
		
		returnMap.put("code", 0);
		returnMap.put("data", strangeUser);
		returnMap.put("message", "获取陌生人列表成功！");
		return returnMap;
	}
	
	/**
	 * 获取好友列表
	 * @return
	 */
	@RequestMapping("/getFriends")
	public Object getFriends() {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		if (loginUser == null) {
			returnMap.put("code", 1);
			returnMap.put("message", "session过期，请重新登录！");
			return returnMap;
		}
		
		List<User> friends = userMapper.getFriends(loginUser.getId());
		List<Group> groups = groupUserMapper.getGroup(loginUser.getId());
		
		if (PropertiesUtil.verifyList(friends)) {
			for (User user : friends) {
				if (PropertiesUtil.isEmptyString(user.getHead())) {
					
				}
				String head = filesConfigService.getFullPathById(Integer.parseInt(user.getHead()));
				user.setHeadUrl(head);
			}
		}
		
		returnMap.put("code", 0);
		returnMap.put("data", friends);
		returnMap.put("group", groups);
		returnMap.put("message", "获取好友列表成功！");
		return returnMap;
	}
	
	/**
	 * 添加好友
	 * @return
	 */
	@RequestMapping("/addFriend")
	public Object addFriend(int objectId) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		if (loginUser == null) {
			returnMap.put("code", 1);
			returnMap.put("message", "session过期，请重新登录！");
			return returnMap;
		}
		
		//双方都记录上
		FriendUser friendUser = new FriendUser();
		friendUser.setUserId(loginUser.getId());
		friendUser.setObjectId(objectId);
		friendUser.setType(1);//说名是好友
		friendUserMapper.add(friendUser);
		
		friendUser.setId(null);
		friendUser.setUserId(objectId);
		friendUser.setObjectId(loginUser.getId());
		friendUser.setType(1);//说名是好友
		friendUserMapper.add(friendUser);
		
		returnMap.put("code", 0);
		returnMap.put("message", "添加好友成功！");
		return returnMap;
	}
	
	/**
	 * 删除好友
	 * @param objectId
	 * @return
	 */
	@RequestMapping("/delFriend")
	public Object delFriend(int objectId) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		if (loginUser == null) {
			returnMap.put("code", 1);
			returnMap.put("message", "session过期，请重新登录！");
			return returnMap;
		}
		
		friendUserMapper.delFriend(loginUser.getId(), objectId);
		friendUserMapper.delFriend(objectId, loginUser.getId());
		
		returnMap.put("code", 0);
		returnMap.put("message", "删除好友成功！");
		return returnMap;
	}
	
	/**
	 * 将发送的消息保存入库
	 * @param userChat
	 * @return
	 */
	@RequestMapping("/addChatMessage")
	public Object addChatMessage(UserChat userChat) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		userChat.setUserId(loginUser.getId());
		userChat.setUserName(loginUser.getUserName());
		userChat.setMakeUser(loginUser.getUserName());
		userChatMapper.add(userChat);
		returnMap.put("code", 0);
		returnMap.put("message", "入库消息成功");
		return returnMap;
	}
	
	/**
	 * 获取消息记录
	 * @param objectId
	 * @return
	 */
	@RequestMapping("/getMessageRecord")
	public Object getMessageRecord(int objectId, int isGroup, Integer pageIndex, Integer pageSize) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		if (loginUser == null) {
			returnMap.put("code", 1);
			returnMap.put("message", "session过期，请重新登录！");
			return returnMap;
		}
		
		if (pageIndex == null) {
			pageIndex = 1;
			pageIndex = 5;
		}
		
		//EntityWrapper ew = new EntityWrapper();
//		Page page=new Page(1,2);
//		ew.setEntity(new Bulletin());
//		//ew.where("name = {0}",name).andNew("age > {0}",age).orderBy("age");
////		List<Group> list = bulletinService.selectList(ew);
//		Page page2 = bulletinService.selectPage(page, ew);
		
		Page<UserChat> page = new Page<UserChat>(pageIndex,pageSize);
//		List<String> descs = new ArrayList<>();
//		descs.add("create_time");
//		page.setDescs(descs);
		List<UserChat> messageRecord =  null;
		if (isGroup == 2) {
			//此处objectId 值得是 群id 即 groupId
			messageRecord =  userChatMapper.getGroupMessageRecord(page, objectId);
		} else {
			messageRecord =  userChatMapper.getMessageRecord(page, loginUser.getId(), objectId);
		}
		
		if (PropertiesUtil.verifyList(messageRecord)) {
			for (UserChat user : messageRecord) {
				if (PropertiesUtil.isEmptyString(user.getHead())) {
					
				}
				String head = filesConfigService.getFullPathById(Integer.parseInt(user.getHead()));
				user.setHead(head);
			}
		}
		
		returnMap.put("code", 0);
		returnMap.put("page", page);
		returnMap.put("data", messageRecord);
		returnMap.put("message", "获取消息成功！");
		return returnMap;
	}
	
	/**
	 * 注销（退出）
	 * @param objectId
	 * @return
	 */
	@RequestMapping("/signOut")
	public Object signOut() {
		Map returnMap = new HashMap<>();
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", null);
		
		returnMap.put("code", 0);
		returnMap.put("message", "退出成功！");
		return returnMap;
	}
	
	
	@RequestMapping("/addGroup")
	public Object addGroup(Group group) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		group.setMakeUser(loginUser.getId() + "");
		groupMapper.add(group);
		//同时把自己加入群里面
		GroupUser gu = new GroupUser();
		gu.setGroupId(group.getId());
		gu.setUserId(loginUser.getId());
		gu.setType(1);//设置群主
		groupUserMapper.add(gu);
		
		returnMap.put("code", 0);
		returnMap.put("message", "添加群成功！");
		return returnMap;
	}
	
	/**
	 * 获取未加群的用户
	 * @param group
	 * @return
	 */
	@RequestMapping("/getStrangerGroupUser")
	public Object getStrangerGroupUser(int groupId) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		List<User> strangerUser = groupUserMapper.getStrangerGroupUser(groupId);
		
		returnMap.put("code", 0);
		returnMap.put("data", strangerUser);
		returnMap.put("message", "获取成功成功！");
		return returnMap;
	}
	
	/**
	 * 获取群成员
	 * @param groupId
	 * @return
	 */
	@RequestMapping("/getGroupUser")
	public Object getGroupUser(int groupId) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		List<User> groupUser = groupUserMapper.getGroupUser(groupId);
		
		returnMap.put("code", 0);
		returnMap.put("data", groupUser);
		returnMap.put("message", "获取成功成功！");
		return returnMap;
	}
	
	/**
	 * 添加群成员
	 * @param groupId
	 * @return
	 */
	@RequestMapping("/addGroupUser")
	public Object addGroupUser(GroupUser groupUser) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		groupUser.setMakeUser(loginUser.getId() + "");
		groupUserMapper.add(groupUser);
		
		returnMap.put("code", 0);
		returnMap.put("message", "获取成功成功！");
		return returnMap;
	}
	
	/**
	 * 删除群友
	 * @param groupUser
	 * @return
	 */
	@RequestMapping("/deleteGroupUser")
	public Object deleteGroupUser(int groupId, int userId) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		groupUserMapper.deleteGroupUser(groupId, userId);
		
		returnMap.put("code", 0);
		returnMap.put("message", "移除成功！");
		return returnMap;
	}
	
	/**
	 * 获取群组用户 -并排除自身
	 * @param groupId
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getGroupById")
	public Object getGroupById(int groupId) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		String groupUserIds = groupUserMapper.getGroupById(groupId);
		
		returnMap.put("code", 0);
		returnMap.put("data", groupUserIds);
		returnMap.put("message", "移除成功！");
		return returnMap;
	}
	
	@RequestMapping("/uploadBase64Pic")
	public Object uploadBase64Pic(@RequestParam("imgDatas[]") String[] imgDatas, Integer imgLength) {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		List imgSrcs = new ArrayList<>();
		try {
			if (imgLength != null && imgLength > 1) {
				for (int i = 0; i < imgDatas.length; i ++) {
					String filePath = filesConfigService.uploadBase64Pic(imgDatas[i], "chat");
					imgSrcs.add(filePath);
				}
			} else {
				String filePath = filesConfigService.uploadBase64Pic(imgDatas[0] + "," + imgDatas[1], "chat");
				imgSrcs.add(filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("code", -1);
			returnMap.put("message", "上传图片失败！");
			return returnMap;
		}
		
		returnMap.put("code", 0);
		returnMap.put("data", imgSrcs);
		returnMap.put("message", "上传图片成功！");
		return returnMap;
	}
	
	/*
	@RequestMapping("/getFirstNewData")
	public Object getFirstNewData() {
		Map returnMap = new HashMap<>();
		User loginUser = getLoginUser();
		
		
		
		returnMap.put("code", 0);
		returnMap.put("data", imgSrcs);
		returnMap.put("message", "上传图片成功！");
		return returnMap;
	}*/
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public User getLoginUser() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		return user;
	}
	
}
