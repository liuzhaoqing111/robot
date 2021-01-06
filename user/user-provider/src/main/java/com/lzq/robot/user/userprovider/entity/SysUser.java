package com.lzq.robot.user.userprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author liuzhaoqing5@jd.com
 * @since 2020-12-16
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自增唯一主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 登录账号
     */
      private String username;

      /**
     * 登录密码
     */
      private String password;

      /**
     * 盐值，密码秘钥
     */
      private String secretkey;

      /**
     * 锁定标记:0未锁定，1锁定
     */
      private Boolean locked;

      /**
     * 真实姓名
     */
      private String realname;

      /**
     * 删除标记:0未删除，1删除
     */
      private Boolean isDelete;

      /**
     * 创建日期
     */
      private LocalDateTime createTime;

      /**
     * 创建用户
     */
      private String createUser;

      /**
     * 修改日期
     */
      private LocalDateTime updateTime;

      /**
     * 修改用户
     */
      private String updateUser;

      /**
     * 时间戳
     */
      private LocalDateTime ts;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getSecretkey() {
        return secretkey;
    }

      public void setSecretkey(String secretkey) {
          this.secretkey = secretkey;
      }
    
    public Boolean getLocked() {
        return locked;
    }

      public void setLocked(Boolean locked) {
          this.locked = locked;
      }
    
    public String getRealname() {
        return realname;
    }

      public void setRealname(String realname) {
          this.realname = realname;
      }
    
    public Boolean getIsDelete() {
        return isDelete;
    }

      public void setIsDelete(Boolean isDelete) {
          this.isDelete = isDelete;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public String getCreateUser() {
        return createUser;
    }

      public void setCreateUser(String createUser) {
          this.createUser = createUser;
      }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

      public void setUpdateTime(LocalDateTime updateTime) {
          this.updateTime = updateTime;
      }
    
    public String getUpdateUser() {
        return updateUser;
    }

      public void setUpdateUser(String updateUser) {
          this.updateUser = updateUser;
      }
    
    public LocalDateTime getTs() {
        return ts;
    }

      public void setTs(LocalDateTime ts) {
          this.ts = ts;
      }

    @Override
    public String toString() {
        return "SysUser{" +
              "id=" + id +
                  ", username=" + username +
                  ", password=" + password +
                  ", secretkey=" + secretkey +
                  ", locked=" + locked +
                  ", realname=" + realname +
                  ", isDelete=" + isDelete +
                  ", createTime=" + createTime +
                  ", createUser=" + createUser +
                  ", updateTime=" + updateTime +
                  ", updateUser=" + updateUser +
                  ", ts=" + ts +
              "}";
    }
}
