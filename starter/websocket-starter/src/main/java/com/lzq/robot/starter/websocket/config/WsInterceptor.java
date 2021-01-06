package com.lzq.robot.starter.websocket.config;

//import com.jd.aiov.commons.shiro.JwtSessionKit;
//import com.jd.aiov.commons.shiro.JwtUser;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * websocket 认证拦截
 *
 * @author liuzhaoqing5
 * @date 2020/9/2 17:52
 */
@Slf4j
@Component
public class WsInterceptor implements HandshakeInterceptor {

  /**
   * token
   */
  public static final String TOKEN = "Token";

  /**
   * 握手前
   *
   * @param request
   * @param response
   * @param wsHandler
   * @param attributes
   * @return
   * @throws Exception
   */
  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes)
      throws Exception {
    log.debug("beforeHandshake: 握手开始");
    // 获得请求参数
    if (request instanceof ServletServerHttpRequest) {
//      final String token = ((ServletServerHttpRequest) request).getServletRequest().getParameter(TOKEN);
//      final JwtUser jwtUser = JwtSessionKit.getUser(token);
//      if (jwtUser == null) {
//        log.warn("failed to check jwt, cannot establish websocket");
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        return false;
//      }
//      attributes.put(WebsocketConst.USERNAME, jwtUser.getUsername());
//      log.debug("握手成功，username:{}", jwtUser.getUsername());
      return true;
    } else {
      log.error("request type is illegal");
    }
    return false;
  }

  /**
   * 握手后
   *
   * @param request
   * @param response
   * @param wsHandler
   * @param exception
   */
  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    log.debug("afterHandshake: 握手完成");
  }
}
