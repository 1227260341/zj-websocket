/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : zj_websocket

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2018-07-29 22:40:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zw_friend_user
-- ----------------------------
DROP TABLE IF EXISTS `zw_friend_user`;
CREATE TABLE `zw_friend_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `object_id` int(11) NOT NULL COMMENT '对象id',
  `type` int(2) NOT NULL COMMENT '1 好友，2 群',
  `valid_flag` varchar(2) DEFAULT NULL,
  `make_time` datetime DEFAULT NULL,
  `make_user` varchar(20) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户好友或群表';

-- ----------------------------
-- Records of zw_friend_user
-- ----------------------------

-- ----------------------------
-- Table structure for zw_group
-- ----------------------------
DROP TABLE IF EXISTS `zw_group`;
CREATE TABLE `zw_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) NOT NULL COMMENT '群名',
  `introduce` varchar(200) DEFAULT NULL COMMENT '介绍',
  `type` int(2) DEFAULT NULL,
  `valid_flag` varchar(2) DEFAULT NULL,
  `make_time` datetime DEFAULT NULL,
  `make_user` varchar(20) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群表';

-- ----------------------------
-- Records of zw_group
-- ----------------------------

-- ----------------------------
-- Table structure for zw_group_user
-- ----------------------------
DROP TABLE IF EXISTS `zw_group_user`;
CREATE TABLE `zw_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `valid_flag` varchar(2) DEFAULT NULL,
  `make_time` datetime DEFAULT NULL,
  `make_user` varchar(20) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群用户表';

-- ----------------------------
-- Records of zw_group_user
-- ----------------------------

-- ----------------------------
-- Table structure for zw_user
-- ----------------------------
DROP TABLE IF EXISTS `zw_user`;
CREATE TABLE `zw_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(36) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `actual_name` varchar(20) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `valid_flag` varchar(2) DEFAULT NULL,
  `make_time` datetime DEFAULT NULL,
  `make_user` varchar(20) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zw_user
-- ----------------------------
INSERT INTO `zw_user` VALUES ('1', '周振江', '123456', null, null, null, null, null, null, null);
INSERT INTO `zw_user` VALUES ('2', '额母鸡啊', '123456', null, null, null, null, null, null, null);


-- 聊天记录表
CREATE TABLE `zw_user_chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(36) NOT NULL,
  `object_id` int(11) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `valid_flag` varchar(2) DEFAULT NULL,
  `make_time` datetime DEFAULT NULL,
  `make_user` varchar(20) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聊天记录表';








