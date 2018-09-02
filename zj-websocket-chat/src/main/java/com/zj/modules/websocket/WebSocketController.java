package com.zj.modules.websocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.modules.domain.User;
import com.zj.modules.domain.UserChat;
import com.zj.modules.mapper.UserChatMapper;
import com.zj.modules.mapper.UserMapper;
import com.zj.modules.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

//configurator = WebsocketConfig.class 该属性就是我上面配置的信息
@ServerEndpoint(value = "/chat", configurator = WebSocketConfig.class)
//@ServerEndpoint(value = "/chat", configurator = WebSocketConfig.class, encoders = { ServerEncoder.class })
@Component    //此注解千万千万不要忘记，它的主要作用就是将这个监听器纳入到Spring容器中进行管理
public class WebSocketController {
	private static final Log log = LogFactory.getLog(WebSocketController.class);
	
  //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
  private static int onlineCount = 0;

  //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//  private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
  private static Map<Integer, WebSocketController> webSocketSet = new HashMap<>();

  //与某个客户端的连接会话，需要通过它来给客户端发送数据
  private Session session;
  
	private HttpServletRequest request;

  /**
   * 连接建立成功调用的方法
   * <p>
   * config用来获取WebsocketConfig中的配置信息
   */
  @OnOpen
  public void onOpen(Session session, EndpointConfig config) {

    //获取WebsocketConfig.java中配置的“sessionId”信息值
    String httpSessionId = (String) config.getUserProperties().get("sessionId");
    if (config.getUserProperties().get("userId") == null) {
    	return ;
    }
    int userId = (int) config.getUserProperties().get("userId");
    
    System.out.println("----userId-----" + userId + "====sessionId=" + httpSessionId);
    
    //session.getUserPrincipal().getName();
    this.session = session;
//    WebSocket b = this;
    //webSocketSet.add(this);     //加入set中
    
    //加入map
    webSocketSet.put(userId, this);
    
    addOnlineCount();           //在线数加1
    System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    try {
      sendMessage("Hello world");
    } catch (IOException e) {
      System.out.println("IO异常");
    }
  }

  /**
   * 连接关闭调用的方法
   */
  @OnClose
  public void onClose() {
    webSocketSet.remove(this);  //从set中删除
    subOnlineCount();           //在线数减1
    System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
  }

  /**
   * 收到客户端消息后调用的方法
   *
   * @param message 客户端发送过来的消息
   */
  @OnMessage
  public void onMessage(String message, Session session) {
	  if (message == null || "".equals(message)) {
		  System.out.println("------------没有消息--------------");
		  return ;
	  }
	  
	  try {
		  
		  JSONObject jsonObj = JSON.parseObject(message);
		  int type = (int) jsonObj.get("type");
		  int objectId = (int) jsonObj.get("objectId");
		  String content = (String) jsonObj.get("content");
		  Object groupUserIds = jsonObj.get("groupUserIds");
		  
		  System.out.println("来自客户端的消息:" + content);
		  Principal p = session.getUserPrincipal();
		  Map sessionMap = session.getUserProperties();
		  int userId = (int) sessionMap.get("userId");//获取到当前登陆人id
		  User sendUser = (User) sessionMap.get("user");//获取到当前登陆人id
		  
		  
		  if (type == 2 && groupUserIds != null && groupUserIds != "") {//说明是群发
			  String[] userIds = groupUserIds.toString().split(",");
			  for (String groupUserId : userIds) {
				  int groupUserIdInt = Integer.parseInt(groupUserId);
				  if (groupUserIdInt == userId) {//是自身则无需发送消息
					  continue ;
				  }
				  WebSocketController item = webSocketSet.get(Integer.parseInt(groupUserId));
				  if (item == null) {//未在线 则无需发送消息
					  System.out.println("不存在websocket对象，即对方不在线");
					  continue ;
				  }
				  Map sendMessage = new HashMap<>();
				  sendMessage.put("type", type);
				  sendMessage.put("sendUserId", userId);//发送的对象id（即接受消息的人）
				  sendMessage.put("groupId", objectId);//存入groupId
				  sendMessage.put("sendUserName", sendUser.getUserName());
				  sendMessage.put("sendUserHead", sendUser.getHeadUrl());
				  sendMessage.put("message", content);
				  item.sendMessage(MapToJson(sendMessage));
			  }
			  
			  
		  } else {//好友消息实现
			  WebSocketController item = webSocketSet.get(objectId);
			  if (item == null) {//未在线 则无需发送消息
				  System.out.println("不存在websocket对象，即对方不在线");
				  return ;
			  }
			  Map sendMessage = new HashMap<>();
			  sendMessage.put("type", type);
			  sendMessage.put("sendUserId", userId);//发送的对象id（即接受消息的人）
			  sendMessage.put("sendUserName", sendUser.getUserName());
			  sendMessage.put("sendUserHead", sendUser.getHeadUrl());
			  sendMessage.put("message", content);
			  item.sendMessage(MapToJson(sendMessage));
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  //群发消息
//	  for (WebSocketController item : webSocketSet) {
//		  try {
//			  item.sendMessage(content);
//		  } catch (IOException e) {
//			  e.printStackTrace();
//		  }
//	  }
  }

  /**
   * 发生错误时调用
   */
  @OnError
  public void onError(Session session, Throwable error) {
    System.out.println("发生错误");
    error.printStackTrace();
  }


  public void sendMessage(String message) throws IOException {
    this.session.getBasicRemote().sendText(message);
    //this.session.getAsyncRemote().sendText(message);
  }
  
  /**
   * 发送object 类型数据
   * @param message
   * @throws IOException
   */
  public void sendMessageObj(Object message) throws Exception {
//	  getAsyncRemote()和getBasicRemote()确实是异步与同步的区别，
    this.session.getBasicRemote().sendObject(message);
    //this.session.getAsyncRemote().sendText(message);
  }


  /**
   * 群发自定义消息
   */
  public static void sendInfo(String message) throws IOException {
//	  for (int key : webSocketSet.keySet()) {
//		  
//	  }
	  
	  try {
		  for (WebSocketController item : webSocketSet.values()) {
			  item.sendMessage(message);
		  }
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
  }

  public static synchronized int getOnlineCount() {
    return onlineCount;
  }

  public static synchronized void addOnlineCount() {
    WebSocketController.onlineCount++;
  }

  public static synchronized void subOnlineCount() {
    WebSocketController.onlineCount--;
  }
  
  /**
   * 将 map 转换成json
   * @param map
   * @return
   */
  public String MapToJson(Map<String, Object> map) {
	  String jsonStr = "{\"\":\"\"}";
	  String json = "{";
	  boolean isFirst = true;
	  for (String key : map.keySet()) {
		  Object value = map.get(key);
		  if (isFirst) {
			  isFirst = false;
			  json += "\"" + key + "\":\"" + value + "\"";
		  } else {
			  json += ",\"" + key + "\":\"" + value + "\"";
		  }
	  }
	  json += "}";
	  log.info("MapToJson 转换好的json=" + json);
	  return json;
  }
  
}