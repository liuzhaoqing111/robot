/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50721
Source Host           : mysql-internet-cn-east-2-5f9003a01ed24b73.rds.jdcloud.com:3306
Source Database       : jt_rap

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-10-23 17:41:28
*/

use mysql;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456';
flush privileges;

CREATE DATABASE IF NOT EXISTS pvuv ;
USE pvuv;



SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `track_basic_activity`
-- ----------------------------
DROP TABLE IF EXISTS `track_basic_activity`;
CREATE TABLE `track_basic_activity` (
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道',
  `biz_id` varchar(50) DEFAULT NULL COMMENT '业务ID',
  `client_id` varchar(50) DEFAULT NULL COMMENT '客户端ID',
  `inserted` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_basic_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `track_basic_banner`
-- ----------------------------
DROP TABLE IF EXISTS `track_basic_banner`;
CREATE TABLE `track_basic_banner` (
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道',
  `biz_id` varchar(50) DEFAULT NULL COMMENT '业务ID',
  `client_id` varchar(50) DEFAULT NULL COMMENT '客户端ID',
  `inserted` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_basic_banner
-- ----------------------------

-- ----------------------------
-- Table structure for `track_basic_home`
-- ----------------------------
DROP TABLE IF EXISTS `track_basic_home`;
CREATE TABLE `track_basic_home` (
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道',
  `client_id` varchar(50) DEFAULT NULL COMMENT '客户端ID',
  `inserted` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_basic_home
-- ----------------------------

-- ----------------------------
-- Table structure for `track_basic_item`
-- ----------------------------
DROP TABLE IF EXISTS `track_basic_item`;
CREATE TABLE `track_basic_item` (
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道',
  `biz_id` varchar(50) DEFAULT NULL COMMENT '业务ID',
  `client_id` varchar(50) DEFAULT NULL COMMENT '客户端ID',
  `inserted` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_basic_item
-- ----------------------------

-- ----------------------------
-- Table structure for `track_basic_pay`
-- ----------------------------
DROP TABLE IF EXISTS `track_basic_pay`;
CREATE TABLE `track_basic_pay` (
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道',
  `client_id` varchar(50) DEFAULT NULL COMMENT '客户端ID',
  `inserted` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_basic_pay
-- ----------------------------

-- ----------------------------
-- Table structure for `track_basic_store`
-- ----------------------------
DROP TABLE IF EXISTS `track_basic_store`;
CREATE TABLE `track_basic_store` (
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道',
  `biz_id` varchar(50) DEFAULT NULL COMMENT '业务ID',
  `client_id` varchar(50) DEFAULT NULL COMMENT '客户端ID',
  `inserted` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_basic_store
-- ----------------------------

-- ----------------------------
-- Table structure for `track_lock`
-- ----------------------------
DROP TABLE IF EXISTS `track_lock`;
CREATE TABLE `track_lock` (
  `id` bigint(15) NOT NULL,
  `lock_name` varchar(50) DEFAULT NULL COMMENT '锁名称',
  `lock_code` varchar(20) NOT NULL COMMENT '锁类型 1：pv\\uv，2：不同渠道pv\\uv，3：商品\\店铺，4：数据清理',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据库分布式锁';

-- ----------------------------
-- Records of track_lock
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_activity`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_activity`;
CREATE TABLE `track_statis_activity` (
  `uv` int(8) DEFAULT NULL COMMENT 'uv',
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_banner`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_banner`;
CREATE TABLE `track_statis_banner` (
  `uv` int(8) DEFAULT NULL COMMENT 'uv',
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_banner
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_home`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_home`;
CREATE TABLE `track_statis_home` (
  `uv` int(8) DEFAULT NULL COMMENT 'uv',
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_home
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_item`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_item`;
CREATE TABLE `track_statis_item` (
  `uv` int(8) DEFAULT NULL COMMENT 'uv',
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_item
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_pay`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_pay`;
CREATE TABLE `track_statis_pay` (
  `uv` int(8) DEFAULT NULL COMMENT 'uv',
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_pay
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_store`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_store`;
CREATE TABLE `track_statis_store` (
  `uv` int(8) DEFAULT NULL COMMENT 'uv',
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `chan` varchar(50) DEFAULT NULL COMMENT '渠道'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_store
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_top_item`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_top_item`;
CREATE TABLE `track_statis_top_item` (
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `biz_id` varchar(50) DEFAULT NULL COMMENT '业务ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_top_item
-- ----------------------------

-- ----------------------------
-- Table structure for `track_statis_top_store`
-- ----------------------------
DROP TABLE IF EXISTS `track_statis_top_store`;
CREATE TABLE `track_statis_top_store` (
  `pv` int(8) DEFAULT NULL COMMENT 'pv',
  `times` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `biz_id` varchar(50) DEFAULT NULL COMMENT '业务ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of track_statis_top_store
-- ----------------------------
