package com.lzq.robot.user.userprovider.service;

import com.lzq.robot.user.userprovider.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author liuzhaoqing5@jd.com
 * @since 2020-12-16
 */
public interface SysUserService extends IService<SysUser> {

    void saveUser(SysUser user);

    SysUser getByUsername(String username);

    @Transactional(rollbackFor = Exception.class)
    void createSuperAdmin();
}
