package com.lzq.robot.starter.websocket.context;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

/**
 * 保存websocket session
 *
 * @author liuzhaoqing5
 * @date 2020/9/2 17:50
 */
@Slf4j
public class WsSessionManager {

  /**
   * 保存连接 session 的地方
   */
  private static ConcurrentHashMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

  /**
   * 添加 session
   *
   * @param key
   */
  public static void add(String key, WebSocketSession session) {
    // 添加 session
    SESSION_POOL.put(key, session);
  }

  /**
   * 删除 session,会返回删除的 session
   *
   * @param key
   * @return
   */
  private static WebSocketSession remove(String key) {
    // 删除 session
    return SESSION_POOL.remove(key);
  }

  /**
   * 删除并同步关闭连接
   *
   * @param key
   */
  public static void removeAndClose(String key) {
    WebSocketSession session = remove(key);
    WsUserSubscribeManager.remove(key);
    if (session != null) {
      try {
        // 关闭连接
        session.close();
      } catch (IOException e) {
        log.error("ws session close error", e);
      }
    }
  }

  /**
   * 获得 session
   *
   * @param key
   * @return
   */
  public static WebSocketSession get(String key) {
    // 获得 session
    return SESSION_POOL.get(key);
  }

}
