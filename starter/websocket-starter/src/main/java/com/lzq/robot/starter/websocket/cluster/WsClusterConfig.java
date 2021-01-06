package com.lzq.robot.starter.websocket.cluster;

import com.lzq.robot.starter.websocket.WebsocketSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * cluster config
 *
 * @author liuzhaoqing5
 * @date 2020/9/8 16:44
 */
@Configuration
@Slf4j
public class WsClusterConfig {

  @Bean
  @ConditionalOnMissingBean(value = WsMessageSendToClusterHandler.class)
  public WsMessageSendToClusterHandler clusterMessageHandler(WebsocketSender websocketSender) {
    log.info("WsDefaultClusterMessageHandler init, websocket single model start");
    return new WsDefaultMessageSendToClusterHandler(websocketSender);
  }
}
