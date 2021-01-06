package com.lzq.robot.user.userprovider.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户令牌表
 * </p>
 *
 * @author liuzhaoqing5@jd.com
 * @since 2020-12-16
 */
public class SysToken implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 用户表主键
     */
        private Integer userId;

      /**
     * 过期时间
     */
      private LocalDateTime expireTime;

      /**
     * 令牌
     */
      private String token;

      /**
     * 更新时间
     */
      private LocalDateTime updateTime;

    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public LocalDateTime getExpireTime() {
        return expireTime;
    }

      public void setExpireTime(LocalDateTime expireTime) {
          this.expireTime = expireTime;
      }
    
    public String getToken() {
        return token;
    }

      public void setToken(String token) {
          this.token = token;
      }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

      public void setUpdateTime(LocalDateTime updateTime) {
          this.updateTime = updateTime;
      }

    @Override
    public String toString() {
        return "SysToken{" +
              "userId=" + userId +
                  ", expireTime=" + expireTime +
                  ", token=" + token +
                  ", updateTime=" + updateTime +
              "}";
    }
}
