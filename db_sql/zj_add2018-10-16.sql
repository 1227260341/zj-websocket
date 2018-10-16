/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : zj_websocket

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2018-10-16 20:50:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for files_config
-- ----------------------------
DROP TABLE IF EXISTS `files_config`;
CREATE TABLE `files_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `size` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `validFlag` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `comment` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `makeTime` datetime DEFAULT NULL,
  `makeUser` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifyUser` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='文件配置表';
