package com.lzq.robot.starter.websocket.dto;

import lombok.Data;

/**
 * websocket 消息发送事件
 *
 * @author liuzhaoqing5
 * @date 2020/9/8 15:04
 */
@Data
public class WsUserMessage {

  /**
   * 消息接收人
   */
  private String username;

  /**
   * 消息内容
   */
  WsDefaultMessage message;


}
