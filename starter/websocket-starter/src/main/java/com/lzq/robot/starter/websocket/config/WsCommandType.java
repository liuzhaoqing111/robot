package com.lzq.robot.starter.websocket.config;

/**
 * websocket 消息类型
 *
 * @author liuzhaoqing5
 * @date 2020/9/3 10:34
 */
public enum WsCommandType {
  /**
   * 发送消息
   */
  SEND,

  /**
   * 确认消息
   */
  ACK,

  /**
   * 不想确认消息
   */
  NACK,

  /**
   * 订阅消息
   */
  SUBSCRIBE,

  /**
   * 取消订阅
   */
  UNSUBSCRIBE,

  /**
   * 错误
   */
  ERROR,

  /**
   * 关闭连接
   */
  DISCONNECT
}
