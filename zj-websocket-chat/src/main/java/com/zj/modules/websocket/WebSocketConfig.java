package com.zj.modules.websocket;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.zj.modules.domain.User;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

@Configuration
public class WebSocketConfig extends Configurator {

  @Override
  public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
    /*如果没有监听器,那么这里获取到的HttpSession是null*/
    StandardSessionFacade ssf = (StandardSessionFacade) request.getHttpSession();
    if (ssf != null) {
      HttpSession httpSession = (HttpSession) request.getHttpSession();
      User user = (User) httpSession.getAttribute("loginUser");
      //关键操作
      if (user != null) {
    	  sec.getUserProperties().put("userId", user.getId());
      }
      sec.getUserProperties().put("sessionId", httpSession.getId());
      System.out.println("获取到的SessionID：" + httpSession.getId());
    }
  }

  /**
   * 引入shiro框架下的session，获取session信息
   */
  /*
  @Override
  public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
    Session shiroSession = ShiroUtils.getSubjct().getSession();
    sec.getUserProperties().put("sessionId", shiroSession.getId());
  }
  */

  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    //这个对象说一下，貌似只有服务器是tomcat的时候才需要配置,具体我没有研究
    return new ServerEndpointExporter();
  }
}