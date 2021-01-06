package com.lzq.robot.starter.websocket.config;

import java.security.Principal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * websocket 配置
 *
 * @author liuzhaoqing5
 * @date 2020/9/2 17:54
 */
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  /**
   * auth handle
   */
  @Autowired
  private WsMessageDispatcherHandler wsMessageDispatcherHandler;
  /**
   * ws interceptor
   */
  @Autowired
  private WsInterceptor myInterceptor;

  /**
   * register websocket handler
   *
   * @param registry
   */
  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry
        .addHandler(wsMessageDispatcherHandler, WebsocketConst.WEBSOCKET_ENDPOINT)
        .setHandshakeHandler(new DefaultHandshakeHandler() {
          @Override
          protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
            final Object username = attributes.get(WebsocketConst.USERNAME);
            return username::toString;
          }
        })
        .addInterceptors(myInterceptor)
        .setAllowedOrigins("*");
  }

}
