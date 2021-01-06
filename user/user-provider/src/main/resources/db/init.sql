use mysql;
create user 'robot'@'%' IDENTIFIED BY '123456';
create database robot;
GRANT ALL PRIVILEGES ON robot.* TO 'robot'@'%';
FLUSH PRIVILEGES;
use robot;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增唯一主键',
  `code` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记:0未删除，1删除',
  `mark` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `create_user` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  `update_user` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '修改用户',
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色信息表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增唯一主键',
  `username` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '登录账号',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '登录密码',
  `secretkey` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '盐值，密码秘钥',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '锁定标记:0未锁定，1锁定',
  `realname` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '真实姓名',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记:0未删除，1删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `create_user` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  `update_user` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '修改用户',
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';


DROP TABLE IF EXISTS `sys_user_role_ref`;
CREATE TABLE `sys_user_role_ref` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色关联',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户主键',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';


DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token` (
  `user_id` int(11) unsigned NOT NULL COMMENT '用户表主键',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `token` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '令牌',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`), unique key(`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户令牌表';
