package com.lzq.robot.user.userprovider.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzq.robot.common.utils.HashUtils;
import com.lzq.robot.user.userprovider.entity.SysUser;
import com.lzq.robot.user.userprovider.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author liuzhaoqing5@jd.com
 * @since 2020-12-16
 */
@RestController
@Tag(name = "用户管理")
@RequestMapping("/sysUser")
@RequiredArgsConstructor
public class SysUserController {

    final SysUserService sysUserService;


    @Operation(summary = "用户列表", description = "用户列表desc")
    @GetMapping
    public IPage<SysUser> list(@RequestParam @NotNull Integer pageNum, @RequestParam @NotNull Integer pageSize,
                               @RequestParam(required = false) String username,
                               @RequestParam(required = false) String realName) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(username)) {
            wrapper.lambda().like(SysUser::getUsername, username);
        }
        if (StringUtils.hasText(realName)) {
            wrapper.lambda().like(SysUser::getRealname, realName);
        }
        return sysUserService.page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Operation(summary = "添加用户", description = "")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@RequestBody SysUser user){
//        SysUser currentUser = SessionUtils.getUser();
//        user.setCreateUser(currentUser.getUsername());
//        user.setUpdateUser(currentUser.getUsername());
        sysUserService.saveUser(user);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "明细", description = "")
    @GetMapping(value = "/{id}")
    public SysUser detail(@PathVariable Integer id){
        return sysUserService.getById(id);
    }


//    @RequiresPermissions("sysUser:password")
//    @Operation(summary = "修改密码", description = "")
//    @PutMapping(value = "/password", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> modifyPassword(@RequestBody ChangePasswordVO password) {
//        SysUser user = SessionUtils.getUser();
//        if (!HashUtils.sha256(password.getOldPassword(), user.getSecretkey())
//                .equals(user.getPassword())) {
//            throw new ApiException(HttpStatus.BAD_REQUEST, "密码有误");
//        }
//
//        // 重新更新salt
//        user.setSecretkey(HashUtils.sha256(UUID.randomUUID().toString()));
//        user.setPassword(HashUtils.sha256(password.getPassword(), user.getSecretkey()));
//        sysUserService.updateById(user);
//        return ResponseEntity.ok().build();
//    }
}

