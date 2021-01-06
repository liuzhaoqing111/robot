package com.lzq.robot.starter.websocket.config;

import com.lzq.robot.starter.websocket.context.WsSessionManager;
import com.lzq.robot.starter.websocket.dto.WsDefaultMessage;
import com.lzq.robot.starter.websocket.messagehandler.SubscribeMessageTypeHandler;
import com.lzq.robot.starter.websocket.messagehandler.UnSubscribeMessageTypeHandler;
import com.lzq.robot.starter.websocket.util.JacksonUtil;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * message dispather
 *
 * @author liuzhaoqing5
 * @date 2020/9/2 17:47
 */
@Component
@Slf4j
public class WsMessageDispatcherHandler extends TextWebSocketHandler {

  /**
   * socket 建立成功事件
   *
   * @param session
   * @throws Exception
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    final String username = session.getPrincipal().getName();
    if (username != null) {
      // 用户连接成功，放入在线用户缓存
      log.debug("afterConnectionEstablished, user:{}", username);
      WsSessionManager.add(username, session);
    } else {
      throw new RuntimeException("用户登录已经失效!");
    }
  }

  /**
   * 接收消息事件
   *
   * @param session
   * @param message
   * @throws Exception
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // 1. 获得客户端传来的消息
    String payload = message.getPayload();
    final WsDefaultMessage wsDefaultMessage = JacksonUtil.toObject(payload, WsDefaultMessage.class);
    if (wsDefaultMessage == null) {
      sendErrorMessage(session, "message format error, msg:" + payload);
      return;
    }
    final WsCommandType commandType = wsDefaultMessage.getCommandType();
    final String username = session.getPrincipal().getName();

    // 2. 订阅消息处理
    if (WsCommandType.SUBSCRIBE == commandType) {
      SubscribeMessageTypeHandler.handler(wsDefaultMessage, username);
    }
    // 取消订阅
    else if (WsCommandType.UNSUBSCRIBE == commandType) {
      UnSubscribeMessageTypeHandler.handler(wsDefaultMessage, username);
    }
    // 关闭连接
    else if (WsCommandType.DISCONNECT == commandType) {
      session.close(CloseStatus.NORMAL);
    }
    // ack响应
    else if (WsCommandType.ACK == commandType) {
      log.info("received ack, msg:{}", payload);
    } else {
      sendErrorMessage(session, "the command type is not currently supported");
      return;
    }

    log.info("server 接收到 " + username + " 发送的 " + payload);
    sendAckMessage(session, wsDefaultMessage);
  }

  /**
   * 发送ack消息
   *
   * @param session
   * @param requestMsg
   * @throws IOException
   */
  private void sendAckMessage(WebSocketSession session, WsDefaultMessage<?> requestMsg) throws IOException {
    final WsDefaultMessage<String> msg = new WsDefaultMessage<>();
    msg.setCommandType(WsCommandType.ACK);
    msg.setDestination(requestMsg.getDestination());
    msg.setRequestId(requestMsg.getRequestId());
    session.sendMessage(msg.toTextMessage());
  }


  /**
   * 发送错误消息
   *
   * @param session
   * @throws IOException
   */
  private void sendErrorMessage(WebSocketSession session, String msg) throws IOException {
    final WsDefaultMessage<String> errormsg = new WsDefaultMessage<>();
    errormsg.setCommandType(WsCommandType.ERROR);
    errormsg.setPayload(msg);
    session.sendMessage(errormsg.toTextMessage());
  }

  /**
   * socket 断开连接时
   *
   * @param session
   * @param status
   * @throws Exception
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    final String username = session.getPrincipal().getName();
    if (username != null) {
      // 用户退出，移除缓存
      log.info("afterConnectionClosed, username:{}, status:{}", username, status);
      WsSessionManager.removeAndClose(username);
    }
  }
}
