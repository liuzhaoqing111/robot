package com.lzq.robot.starter.websocket.dto;

import com.lzq.robot.starter.websocket.config.WsCommandType;
import com.lzq.robot.starter.websocket.util.JacksonUtil;
import java.util.Objects;

import lombok.Data;
import org.springframework.web.socket.TextMessage;

/**
 * websocket client message
 *
 * @author liuzhaoqing5
 * @date 2020/9/3 10:46
 */
@Data
public class WsDefaultMessage<T> {

  /**
   * 负载
   */
  private T payload;

  /**
   * 请求ID
   */
  private String requestId;

  /**
   * 目标地址
   */
  private String destination;

  /**
   * 命令类型
   */
  private WsCommandType commandType;

  /**
   * to text message
   *
   * @return
   */
  public TextMessage toTextMessage() {
    return new TextMessage(Objects.requireNonNull(JacksonUtil.toJson(this)));
  }
}
