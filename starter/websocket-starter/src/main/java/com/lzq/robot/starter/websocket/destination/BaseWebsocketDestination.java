package com.lzq.robot.starter.websocket.destination;

import java.util.Objects;

/**
 * websocket destination
 *
 * @author liuzhaoqing5
 * @date 2020/9/2 22:32
 */
public abstract class BaseWebsocketDestination {

  /**
   * 获取destination
   *
   * @return
   */
  public abstract String getDestination();

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof BaseWebsocketDestination)) {
      return false;
    }
    return ((BaseWebsocketDestination) obj).getDestination().equals(this.getDestination());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDestination());
  }

}
