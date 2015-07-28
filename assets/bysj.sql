/*
Navicat MySQL Data Transfer

Source Server         : TEST
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : bysj

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-28 21:36:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for changes
-- ----------------------------
DROP TABLE IF EXISTS `changes`;
CREATE TABLE `changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` text COLLATE utf8_bin,
  `number` text COLLATE utf8_bin,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of changes
-- ----------------------------
INSERT INTO `changes` VALUES ('1', 0x323031352D372D31382031323A34, 0x3131, '-1', '1');
INSERT INTO `changes` VALUES ('2', 0x323031352D372D31382031323A34, 0x3131, '-1', '1');
INSERT INTO `changes` VALUES ('3', 0x323031362D372D31382031323A3239, 0x313233, '-1', '1');
INSERT INTO `changes` VALUES ('4', 0x323031352D372D31382031323A3235, 0x3132, '-1', '2');
INSERT INTO `changes` VALUES ('5', 0x323031362D372D31382031323A3235, 0x3132, '-1', '2');
INSERT INTO `changes` VALUES ('6', 0x323031372D372D31382031323A3239, 0x333335, '-1', '2');
INSERT INTO `changes` VALUES ('7', 0x323031352D372D31382031323A3236, 0x31313131, '1', '9');
INSERT INTO `changes` VALUES ('8', 0x323031352D372D31382031323A3237, 0x313233, '1', '9');
INSERT INTO `changes` VALUES ('9', 0x323031352D372D31382031323A3237, 0x777A67, '-1', '7');
INSERT INTO `changes` VALUES ('10', 0x323031352D372D31382031323A3237, 0x776572, '-1', '7');
INSERT INTO `changes` VALUES ('11', 0x323031352D372D31382031323A3437, 0x6464646464, '-1', '8');
INSERT INTO `changes` VALUES ('12', 0x323031352D372D31382031323A3437, 0x6464646464, '-1', '6');
INSERT INTO `changes` VALUES ('13', 0x323031352D372D31382031323A3437, 0x32333232, '-1', '5');
INSERT INTO `changes` VALUES ('14', 0x323031352D372D31382031323A3438, 0x323334, '-1', '5');
INSERT INTO `changes` VALUES ('15', 0x323031352D372D31382031323A3438, 0x31323334, '-1', '5');
INSERT INTO `changes` VALUES ('16', 0x323031352D372D32362031323A3530, 0x32323830, '-1', '10');
INSERT INTO `changes` VALUES ('17', 0x323031352D372D32362031323A3530, 0x32373539, '-1', '10');
INSERT INTO `changes` VALUES ('18', 0x323031352D372D32362031323A3532, 0x32353931, '-1', '10');
INSERT INTO `changes` VALUES ('19', 0x323031352D372D32362031323A3533, 0x32383339, '-1', '10');
INSERT INTO `changes` VALUES ('20', 0x323031352D372D323620363A3237, 0x323232, '-1', '1');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `messageId` int(11) DEFAULT NULL,
  `name` longtext COLLATE utf8_bin,
  `message` longtext COLLATE utf8_bin,
  `photo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('15', '11', 0x65, 0x67686A6867, '1438004488634.jpg');
INSERT INTO `comment` VALUES ('16', '12', 0x31, 0x646464, '1438079690063.jpg');
INSERT INTO `comment` VALUES ('17', '13', 0x31, 0x646464, '1438080876762.jpg');

-- ----------------------------
-- Table structure for intreduce
-- ----------------------------
DROP TABLE IF EXISTS `intreduce`;
CREATE TABLE `intreduce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of intreduce
-- ----------------------------
INSERT INTO `intreduce` VALUES ('1', 'http://img5.imgtn.bdimg.com/it/u=1262755576,89964397&fm=21&gp=0.jpg');
INSERT INTO `intreduce` VALUES ('2', 'http://pic.nipic.com/2008-02-21/2008221174526749_2.jpg');
INSERT INTO `intreduce` VALUES ('3', 'http://pic.58pic.com/uploadfilepic/sheji/2010-01-14/58PIC_1982zpwang407_20100114ad9ff64a480162fa.jpg');
INSERT INTO `intreduce` VALUES ('4', 'http://pic.58pic.com/00/91/98/68bOOOPIC62.jpg');
INSERT INTO `intreduce` VALUES ('5', 'http://s7.sinaimg.cn/orignal/4b001f87e02fb0ee88de6');

-- ----------------------------
-- Table structure for knownadge
-- ----------------------------
DROP TABLE IF EXISTS `knownadge`;
CREATE TABLE `knownadge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` longtext COLLATE utf8_bin,
  `message` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of knownadge
-- ----------------------------
INSERT INTO `knownadge` VALUES ('1', 0x687474703A2F2F7777772E333135362E636E2F757066696C65732F323031322F30332F30332F31312F31322F31352F31623864303137362E6A7067, 0xE4BDA0E8BF99E4B88DE698AFE5AFB9E88DAFE789A9E8BF87E6958FE38082E88DAFE789A9E8BF87E6958FE4BC9AE69C89E5BE88E4B8A5E9878DE79A84E8BF87E38082);
INSERT INTO `knownadge` VALUES ('2', 0x687474703A2F2F696D67302E696D67746E2E6264696D672E636F6D2F69742F753D323535323034363139362C3133383036363238373026666D3D31352667703D302E6A7067, 0xE998BFE88EABE8A5BFE69E97EFBC8CE58F88E5908DE5AE89E88EABE8A5BFE69E97E68896E5AE89E9BB98E8A5BFE69E97EFBC8CE698AFE4B880E7A78DE69C80E5B8B8E794A8E79A84E58D8AE59088E68890E99D92E99C89E7B4A0E7B1BBE5B9BFE8B0B1CEB22DE58685E985B0E883BAE7B1BBE68A97E7949FE7B4A0EFBC8CE4B8BAE4B880E7A78DE799BDE889B2E7B289E69CABEFBC8CE58D8AE8A1B0E69C9FE7BAA6E4B8BA36312E33E58886E9929FE38082E59CA8E985B8E680A7E69DA1E4BBB6E4B88BE7A8B3E5AE9AEFBC8CE88383E882A0E98193E590B8E694B6E78E87E8BEBE393025E38082E998BFE88EABE8A5BFE69E97E69D80E88F8CE4BD9CE794A8E5BCBAEFBC8CE7A9BFE9808FE7BB86E8839EE8869CE79A84E883BDE58A9BE4B99FE5BCBA);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8_bin,
  `message` longtext COLLATE utf8_bin,
  `time` longtext COLLATE utf8_bin,
  `userId` int(11) DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('11', 0x65, 0x64796375766976757675, 0x323031352D30372D32382031383A3233, null, '1438004488634.jpg');
INSERT INTO `message` VALUES ('12', 0x31, 0x776F646464, 0x323031352D30372D32382031303A3237, null, '1438079690063.jpg');
INSERT INTO `message` VALUES ('13', 0x31, 0x313131, 0x323031352D30372D32382031303A3436, null, '1438080876762.jpg');

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` longtext COLLATE utf8_bin,
  `url` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('1', 0xE9AB98E6B885E5AD97E6AF8DE781ABE784B0E5A381E7BAB82DE781ABE784B0E5AD97E6AF8DE4B88EE781ABE784B0E695B0E5AD97E8AEBEE8AEA1E5A381E7BAB8E6A18CE99DA2E5A381E7BAB831E9AB98E6B885E5AD97E6AF8DE781ABE784B0E5A381E7BAB82DE781ABE784B0E5AD97E6AF8DE4B88EE781ABE784B0E695B0E5AD97E8AEBEE8AEA1E5A381E7BAB8E6A18CE99DA2E5A381E7BAB831, 0x687474703A2F2F696D67332E696D67746E2E6264696D672E636F6D2F69742F753D333637353238343031372C3239333537323339363826666D3D32312667703D302E6A7067);
INSERT INTO `msg` VALUES ('2', 0xE9AB98E6B885E5AD97E6AF8DE781ABE784B0E5A381E7BAB82DE781ABE784B0E5AD97E6AF8DE4B88EE781ABE784B0E695B0E5AD97E8AEBEE8AEA1E5A381E7BAB8E6A18CE99DA2E5A381E7BAB831E9AB98E6B885E5AD97E6AF8DE781ABE784B0E5A381E7BAB82DE781ABE784B0E5AD97E6AF8DE4B88EE781ABE784B0E695B0E5AD97E8AEBEE8AEA1E5A381E7BAB8E6A18CE99DA2E5A381E7BAB831E9AB98E6B885E5AD97E6AF8DE781ABE784B0E5A381E7BAB82DE781ABE784B0E5AD97E6AF8DE4B88EE781ABE784B0E695B0E5AD97E8AEBEE8AEA1E5A381E7BAB8E6A18CE99DA2E5A381E7BAB831E9AB98E6B885E5AD97E6AF8DE781ABE784B0E5A381E7BAB82DE781ABE784B0E5AD97E6AF8DE4B88EE781ABE784B0E695B0E5AD97E8AEBEE8AEA1E5A381E7BAB8E6A18CE99DA2E5A381E7BAB831, 0x687474703A2F2F696D67332E696D67746E2E6264696D672E636F6D2F69742F753D333637353238343031372C3239333537323339363826666D3D32312667703D302E6A7067);

-- ----------------------------
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text COLLATE utf8_bin,
  `msg` text COLLATE utf8_bin,
  `sub` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deal` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `check` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of search
-- ----------------------------
INSERT INTO `search` VALUES ('1', 0xE6849FE58692, 0xE79787E78AB6EFBC9AE58F91E783A7EFBC8CE6B581E9BCBBE6B695, '一般', '如何处理呀', '检查');
INSERT INTO `search` VALUES ('2', 0xE58F91E783A7, 0xE79787E78AB6EFBC9AE58F91E783A7EFBC8CE6B581E9BCBBE6B695, '一般', '如何处理', '检查信息');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8_bin,
  `password` longtext COLLATE utf8_bin,
  `nameS` longtext COLLATE utf8_bin,
  `age` longtext COLLATE utf8_bin,
  `high` longtext COLLATE utf8_bin,
  `xx` longtext COLLATE utf8_bin,
  `tel` longtext COLLATE utf8_bin,
  `ads` longtext COLLATE utf8_bin,
  `sex` longtext COLLATE utf8_bin,
  `photo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('359', 0x31, 0x31, 0x727567, 0x353637, 0x353738, 0x546875, 0x3133353737, 0x657569797265, 0x31, '1438080876762.jpg');
