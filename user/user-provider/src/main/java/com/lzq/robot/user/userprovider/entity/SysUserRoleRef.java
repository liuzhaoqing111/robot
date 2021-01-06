package com.lzq.robot.user.userprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author liuzhaoqing5@jd.com
 * @since 2020-12-16
 */
public class SysUserRoleRef implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 用户角色关联
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户主键
     */
      private Integer userId;

      /**
     * 角色主键
     */
      private Integer roleId;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public Integer getRoleId() {
        return roleId;
    }

      public void setRoleId(Integer roleId) {
          this.roleId = roleId;
      }

    @Override
    public String toString() {
        return "SysUserRoleRef{" +
              "id=" + id +
                  ", userId=" + userId +
                  ", roleId=" + roleId +
              "}";
    }
}
