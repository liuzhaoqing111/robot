package com.lzq.robot.starter.websocket.cluster;

import com.lzq.robot.starter.websocket.WebsocketSender;
import com.lzq.robot.starter.websocket.destination.BaseWebsocketDestination;
import com.lzq.robot.starter.websocket.dto.WsDefaultMessage;
import com.lzq.robot.starter.websocket.dto.WsUserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * websocket 事件监听
 *
 * @author liuzhaoqing5
 * @date 2020/9/1 14:51
 */
@Slf4j
@RequiredArgsConstructor
public class WsDefaultMessageSendToClusterHandler implements WsMessageSendToClusterHandler {

    /**
     * websocket sender
   */
  final WebsocketSender websocketSender;

  /**
   * 发送消息到集群
   */
  @Override
  public void sendMessageToCluster(WsUserMessage userMessage) {
    final String username = userMessage.getUsername();
    final WsDefaultMessage message = userMessage.getMessage();
    BaseWebsocketDestination destination = new BaseWebsocketDestination() {
      @Override
      public String getDestination() {
        return message.getDestination();
      }
    };
    websocketSender.doSend(username, destination, message.getPayload());
  }

}
