package com.lzq.robot.starter.websocket.cluster;


import com.lzq.robot.starter.websocket.dto.WsUserMessage;

/**
 * websocket cluster handler
 *
 * @author liuzhaoqing5
 * @date 2020/9/8 15:46
 */
public interface WsMessageSendToClusterHandler {

  /**
   * 发送消息到集群
   */
  void sendMessageToCluster(WsUserMessage userMessage);

}
