package com.lzq.robot.starter.websocket.messagehandler;


import com.lzq.robot.starter.websocket.context.WsUserSubscribeManager;
import com.lzq.robot.starter.websocket.destination.BaseWebsocketDestination;
import com.lzq.robot.starter.websocket.dto.WsDefaultMessage;

/**
 * 订阅消息处理
 *
 * @author liuzhaoqing5
 * @date 2020/9/3 11:03
 */
public class SubscribeMessageTypeHandler {

  /**
   * 处理订阅
   *
   * @param message
   * @param username
   */
  public static void handler(WsDefaultMessage<String> message, String username) {

    // 添加
    WsUserSubscribeManager.add(username, new BaseWebsocketDestination() {
      @Override
      public String getDestination() {
        return message.getDestination();
      }
    });

  }
}
