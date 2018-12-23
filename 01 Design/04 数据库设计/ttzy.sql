/*
Navicat MySQL Data Transfer

Source Server         : 47.92.166.33
Source Server Version : 50635
Source Host           : 47.92.166.33:3306
Source Database       : ttzy

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-11-25 10:45:32
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', '8', '2018-08-31 20:57:40', '注册', '张三于20180831 20:57:40注册成功');
INSERT INTO `t_log` VALUES ('2', '9', '2018-08-31 20:58:51', '注册', '张三于20180831 20:58:51注册成功');
INSERT INTO `t_log` VALUES ('3', '10', '2018-08-31 21:00:14', '注册', '张三于20180831 21:00:14注册成功');
INSERT INTO `t_log` VALUES ('4', '11', '2018-09-27 23:34:29', '注册', '薛利飞于20180927 23:34:29注册成功');

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
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('admin', '2', '管理员', null);
INSERT INTO `t_role` VALUES ('checker', '4', '审核人员', null);
INSERT INTO `t_role` VALUES ('common', '5', '一般员工', null);
INSERT INTO `t_role` VALUES ('root', '1', '超级管理员', null);
INSERT INTO `t_role` VALUES ('visitor', '6', '游客', null);
INSERT INTO `t_role` VALUES ('writer', '3', '录入人员', null);

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
-- Records of t_session
-- ----------------------------
INSERT INTO `t_session` VALUES ('11111', '22222', '33333');
INSERT INTO `t_session` VALUES ('37B259C8909A2D2B4B8669614D652B23', 'ge3iyhlewZjDu4KRqMnODQ==', 'oVEQd0W7jgUobhqDk-Q8IINdoiCY');
INSERT INTO `t_session` VALUES ('79.83180038157984', '22222|33333', null);
INSERT INTO `t_session` VALUES ('7BFDCD3A7F7EBA496D9DE1A70F535602', 'DDiO1lF82ezex6a1UkAE3A==', 'oVEQd0W7jgUobhqDk-Q8IINdoiCY');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `openId` varchar(200) DEFAULT NULL COMMENT '微信ID号，唯一',
  `nickName` varchar(40) DEFAULT NULL COMMENT '昵称',
  `createTime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `lastTime` datetime DEFAULT NULL COMMENT '上次访问时间',
  `status` int(1) DEFAULT NULL COMMENT '用户审核状态：0待审核 1已审核 2被拒绝',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('9', '1', '1', null, null, null);
INSERT INTO `t_user` VALUES ('10', '1', '薛利飞', '2018-09-27 23:34:29', '2018-09-27 23:34:29', '0');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `roleKey` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('0', '9', 'root');
INSERT INTO `t_user_role` VALUES ('2', '10', 'admin');
INSERT INTO `t_user_role` VALUES ('11', '10', 'root');
