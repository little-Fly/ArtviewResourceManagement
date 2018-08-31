/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : ttzy

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2018-08-31 21:02:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，日志ID',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `logTime` timestamp NULL DEFAULT NULL COMMENT '发生时间',
  `operation` varchar(50) DEFAULT NULL COMMENT '操作',
  `remark` varchar(200) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleKey` varchar(40) NOT NULL COMMENT '主键，角色key',
  `roleType` int(1) DEFAULT '0' COMMENT '0:其他用户 1:公司用户',
  `roleName` varchar(40) DEFAULT NULL COMMENT '角色名',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`roleKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_session
-- ----------------------------
DROP TABLE IF EXISTS `t_session`;
CREATE TABLE `t_session` (
  `third_session` varchar(50) NOT NULL,
  `session_key` varchar(50) DEFAULT NULL,
  `openId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`third_session`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `openId` varchar(200) DEFAULT NULL COMMENT '微信ID号，唯一',
  `nickName` varchar(40) DEFAULT NULL COMMENT '昵称',
  `roleKey` varchar(40) DEFAULT NULL COMMENT '角色Key',
  `createTime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `lastTime` datetime DEFAULT NULL COMMENT '上次访问时间',
  `status` int(1) DEFAULT NULL COMMENT '用户审核状态：0待审核 1已审核 2被拒绝',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
