package com.lzq.robot.starter.websocket;

import com.lzq.robot.starter.websocket.cluster.WsMessageSendToClusterHandler;
import com.lzq.robot.starter.websocket.config.WsCommandType;
import com.lzq.robot.starter.websocket.context.WsSessionManager;
import com.lzq.robot.starter.websocket.context.WsUserSubscribeManager;
import com.lzq.robot.starter.websocket.destination.BaseWebsocketDestination;
import com.lzq.robot.starter.websocket.dto.WsDefaultMessage;
import com.lzq.robot.starter.websocket.dto.WsUserMessage;
import com.lzq.robot.starter.websocket.util.JacksonUtil;
import java.io.IOException;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 发送websocket message
 *
 * @author liuzhaoqing5
 * @date 2020/9/3 09:47
 */
@Slf4j
@Component
public class WebsocketSender {

  /**
   * 广播对应的名称
   */
  private final String BROADCAST_USERNAME = "broadCast";

  /**
   * cluster message handler
   */
  @Autowired
  WsMessageSendToClusterHandler wsMessageSendToClusterHandler;

  /**
   * 发送给user客户端
   *
   * @param username
   * @param message
   */
  public void doSend(String username, String message) {
    final WsDefaultMessage wsDefaultMessage = JacksonUtil.toObject(message, WsDefaultMessage.class);
    if (wsDefaultMessage == null) {
      log.error("websocket message from mq error, message:{}", message);
      return;
    }

    BaseWebsocketDestination destination = new BaseWebsocketDestination() {
      @Override
      public String getDestination() {
        return wsDefaultMessage.getDestination();
      }
    };
    doSend(username, destination, wsDefaultMessage.getPayload());

  }

  /**
   * 发送给客户端
   *
   * @param username
   * @param destination
   * @param payload
   */
  public void doSend(String username, BaseWebsocketDestination destination, Object payload) {
    // 0. 广播，找到具体发送人。
    if (BROADCAST_USERNAME.equals(username)) {

      // 1. find user who subscribe the destination
      final Collection<String> usersList = WsUserSubscribeManager.getUsersSet(destination);

      // 2. send to user
      usersList.forEach(name -> {
        if (!BROADCAST_USERNAME.equals(name)) {
          doSend(name, destination, payload);
        }
      });
      return;
    }

    // 1. get session
    final WebSocketSession webSocketSession = WsSessionManager.get(username);
    if (webSocketSession == null) {
      log.warn("webSocketSession not found, username:{}", username);
      return;
    }

    // 2. check whether subscribe
    final boolean exist = WsUserSubscribeManager.exist(username, destination);
    if (!exist) {
      log.warn("user does not subscribe destination, user:{}, dest:{}", username, destination.getDestination());
      return;
    }

    // 3. create message
    TextMessage message = createTextMessage(destination, payload);

    // 4. push to client
    try {
      webSocketSession.sendMessage(message);
      log.debug("send message to user:{}, message:{}", username, JacksonUtil.toJson(message));
    } catch (IOException e) {
      log.error("websocket sendMessage error", e);
    }
  }

  /**
   * 构建 text message
   *
   * @param destination
   * @param payload
   * @return
   */
  private TextMessage createTextMessage(BaseWebsocketDestination destination, Object payload) {
    // 3. create message
    final WsDefaultMessage wsDefaultMessage = new WsDefaultMessage();
    wsDefaultMessage.setCommandType(WsCommandType.SEND);
    wsDefaultMessage.setDestination(destination.getDestination());
    wsDefaultMessage.setPayload(payload);
    return wsDefaultMessage.toTextMessage();
  }

  /**
   * 发送到某个地址
   *
   * @param destination
   * @param payload
   */
  public void send(BaseWebsocketDestination destination, Object payload) {
    // 广播
    send(BROADCAST_USERNAME, destination, payload);
  }

  /**
   * 发送到某个地址
   *
   * @param destination
   * @param payload
   */
  public void send(String username, BaseWebsocketDestination destination, Object payload) {
    WsDefaultMessage message = new WsDefaultMessage();
    message.setCommandType(WsCommandType.SEND);
    message.setDestination(destination.getDestination());
    message.setPayload(payload);

    final WsUserMessage event = new WsUserMessage();
    event.setUsername(username);
    event.setMessage(message);
    wsMessageSendToClusterHandler.sendMessageToCluster(event);
  }

}
