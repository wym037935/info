/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : csinfo

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2014-09-22 08:59:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `courses`
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_number` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `begin_date` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `max_allowance` int(11) NOT NULL DEFAULT '0',
  `current_count` int(11) NOT NULL DEFAULT '0',
  `location` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `course_of_day` int(11) DEFAULT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `hour` varchar(255) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courses
-- ----------------------------

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', 'asss', 'a', 'http://v.youku.com/v_show/id_XNzQwMjAyNTE2.html?f=22863713', null, null, null);

-- ----------------------------
-- Table structure for `student_course`
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_number` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'current',
  PRIMARY KEY (`id`),
  KEY `course` (`course_number`) USING BTREE,
  KEY `student` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_course
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `permision` varchar(255) NOT NULL DEFAULT 'user',
  `status` varchar(255) NOT NULL DEFAULT 'needactive',
  `mobile` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `contact_mobile` varchar(255) DEFAULT NULL,
  `year_of_birth` varchar(255) DEFAULT NULL,
  `month_of_birth` varchar(255) DEFAULT NULL,
  `day_of_birth` varchar(255) DEFAULT NULL,
  `posting_permision` varchar(255) NOT NULL DEFAULT 'enabled',
  `acticode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'root@root', '63a9f0ea7bb98050796b649e85481845', 'moderator', 'enabled', '', '', '', '', '', '', '', '', '', 'enabled', null);
INSERT INTO `users` VALUES ('2', 'nouser@nouser', '9d191e65f34700d9bda864979f2b4e4f', 'user', 'enabled', null, null, null, null, null, null, null, null, null, 'disabled', null);
