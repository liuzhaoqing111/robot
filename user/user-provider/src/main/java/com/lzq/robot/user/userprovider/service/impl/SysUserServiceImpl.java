package com.lzq.robot.user.userprovider.service.impl;

import com.lzq.robot.common.utils.HashUtils;
import com.lzq.robot.user.userprovider.entity.SysRole;
import com.lzq.robot.user.userprovider.entity.SysUser;
import com.lzq.robot.user.userprovider.entity.SysUserRoleRef;
import com.lzq.robot.user.userprovider.mapper.SysUserMapper;
import com.lzq.robot.user.userprovider.service.SysRoleService;
import com.lzq.robot.user.userprovider.service.SysUserRoleRefService;
import com.lzq.robot.user.userprovider.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;

import static com.lzq.robot.user.userprovider.constants.Constants.*;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author liuzhaoqing5@jd.com
 * @since 2020-12-16
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    final SysRoleService sysRoleService;
    final SysUserRoleRefService sysUserRoleRefService;

    @Override
    public void saveUser(SysUser user) {
        // 1. check param
        Assert.hasText(user.getUsername(), "用户名不正确");
        // 4到20位（字母，数字，下划线，减号
        Assert.isTrue(user.getUsername().matches("^[a-zA-Z0-9_-]{4,20}$"),"用户名不正确");
        Assert.hasText(user.getPassword(), "密码不能为空");

        // 设置密码加密
        user.setSecretkey(HashUtils.sha256(UUID.randomUUID().toString()));
        user.setPassword(HashUtils.sha256(user.getPassword(), user.getSecretkey()));
        user.setLocked(false);
        user.setIsDelete(false);
        this.save(user);
    }

    @Override
    public SysUser getByUsername(String username) {
        return this.lambdaQuery().eq(SysUser::getUsername, username).one();
    }

    /**
     * 系统第一次启动时，检查是否有超级管理员用户，没有则创建超级管理员账号，包括角色
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSuperAdmin(){
        SysUser admin = this.getByUsername(USER_SUPERADMIN_USERNAME);
        String system = "system";
        if (admin == null) {
            // 创建用户
            SysUser sysUser = new SysUser();
            sysUser.setUsername(USER_SUPERADMIN_USERNAME);
            sysUser.setRealname(USER_SUPERADMIN_USERNAME);
            sysUser.setCreateUser(system);
            sysUser.setPassword(USER_SUPERADMIN_PASSWORD);
            this.saveUser(sysUser);

            // 创建超级管理员角色
            SysRole role = new SysRole();
            role.setCode(ROLE_SUPERADMIN_CODE);
            role.setName(ROLE_SUPERADMIN_NAME);
            role.setCreateUser(system);
            sysRoleService.save(role);

            // 授予超级管理员角色
            SysUserRoleRef ref = new SysUserRoleRef();
            ref.setUserId(sysUser.getId());
            ref.setRoleId(role.getId());
            sysUserRoleRefService.save(ref);
        }
    }
}
