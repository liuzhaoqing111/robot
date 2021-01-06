package com.lzq.robot.user.userprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author liuzhaoqing5@jd.com
 * @since 2020-12-16
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自增唯一主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 角色编码
     */
      private String code;

      /**
     * 名称
     */
      private String name;

      /**
     * 删除标记:0未删除，1删除
     */
      private Boolean isDelete;

      /**
     * 备注
     */
      private String mark;

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
    
    public String getCode() {
        return code;
    }

      public void setCode(String code) {
          this.code = code;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public Boolean getIsDelete() {
        return isDelete;
    }

      public void setIsDelete(Boolean isDelete) {
          this.isDelete = isDelete;
      }
    
    public String getMark() {
        return mark;
    }

      public void setMark(String mark) {
          this.mark = mark;
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
        return "SysRole{" +
              "id=" + id +
                  ", code=" + code +
                  ", name=" + name +
                  ", isDelete=" + isDelete +
                  ", mark=" + mark +
                  ", createTime=" + createTime +
                  ", createUser=" + createUser +
                  ", updateTime=" + updateTime +
                  ", updateUser=" + updateUser +
                  ", ts=" + ts +
              "}";
    }
}
