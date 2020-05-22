/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50729
Source Host           : 127.0.0.1:3306
Source Database       : api_test

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-05-22 11:01:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for api_attribute
-- ----------------------------
DROP TABLE IF EXISTS `api_attribute`;
CREATE TABLE `api_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) DEFAULT NULL COMMENT '关联api_info表id列',
  `tid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '属性名称',
  `value` varchar(255) DEFAULT NULL COMMENT '属性值',
  `type` int(2) DEFAULT NULL COMMENT '1：头；\n2：参数',
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_aid` (`aid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for api_info
-- ----------------------------
DROP TABLE IF EXISTS `api_info`;
CREATE TABLE `api_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `pid` int(11) DEFAULT NULL COMMENT '关联project表id列',
  `api_name` varchar(255) DEFAULT NULL COMMENT '接口名称',
  `protocol` int(2) DEFAULT NULL COMMENT '1:http 2:https 3:其他',
  `url` varchar(255) DEFAULT NULL COMMENT 'ip+port+path',
  `method` varchar(255) DEFAULT NULL COMMENT '方法',
  `data_type` int(2) unsigned DEFAULT NULL COMMENT '0：普通，\n1：json',
  `files_upload` int(3) DEFAULT '0' COMMENT '0:不上传；1上传',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_pid` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for assert_info
-- ----------------------------
DROP TABLE IF EXISTS `assert_info`;
CREATE TABLE `assert_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) DEFAULT NULL COMMENT '关联api_info表id列',
  `tid` int(11) DEFAULT NULL COMMENT '关联testcase_info表的id',
  `assert_name` varchar(255) DEFAULT '' COMMENT '断言名称',
  `assert_regular` varchar(255) DEFAULT '' COMMENT '断言正则表达式',
  `expect_value` varchar(255) DEFAULT NULL COMMENT '期望值',
  `comments` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_aid` (`aid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for debug_result
-- ----------------------------
DROP TABLE IF EXISTS `debug_result`;
CREATE TABLE `debug_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) DEFAULT NULL COMMENT '关联api_info表id列',
  `debug_request` varchar(2000) DEFAULT NULL COMMENT '调试请求',
  `debug_response` varchar(2000) DEFAULT NULL COMMENT '调试返回',
  `debug_post` varchar(2000) DEFAULT NULL,
  `debug_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_aid` (`aid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for extractor_info
-- ----------------------------
DROP TABLE IF EXISTS `extractor_info`;
CREATE TABLE `extractor_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) DEFAULT NULL COMMENT '关联api_info表id列',
  `tid` int(11) DEFAULT NULL,
  `extractor_name` varchar(255) DEFAULT NULL COMMENT '提取器名称',
  `variable_name` varchar(255) DEFAULT NULL COMMENT '变量名称',
  `regular_expression` varchar(255) DEFAULT NULL COMMENT '正则表达式',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_aid` (`aid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `pid` int(11) DEFAULT NULL COMMENT '关联project表id列',
  `group_name` varchar(255) DEFAULT NULL COMMENT '分组名称',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_pid` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for permission_group
-- ----------------------------
DROP TABLE IF EXISTS `permission_group`;
CREATE TABLE `permission_group` (
  ` id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '权限，关联peoject_info表中的id',
  `uid` int(11) DEFAULT NULL COMMENT '关联user_info中的id',
  PRIMARY KEY (` id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for project_info
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `sign` int(2) DEFAULT NULL COMMENT '是否签名：0不签名；1网关；2平台；3APP',
  `encrypt` int(2) DEFAULT NULL COMMENT '是否加密：0不加密；1加密',
  `uid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for project_result
-- ----------------------------
DROP TABLE IF EXISTS `project_result`;
CREATE TABLE `project_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'not null主键，自增',
  `pid` int(11) DEFAULT NULL COMMENT '关联project表id列，如果不为空则按照项目执行用例',
  `gid` int(11) DEFAULT NULL COMMENT '如果不为空则按照分组执行用例',
  `aid` int(11) DEFAULT NULL COMMENT '关联api_info表id列，如果不为空则按照接口执行用例',
  `ttid` int(11) DEFAULT NULL COMMENT '定时任务id,如果不为空则按照定时任务执行用例',
  `tid` int(11) NOT NULL COMMENT '关联testcase表id，',
  `case_name` varchar(255) DEFAULT NULL COMMENT '接口名称',
  `request` varchar(255) DEFAULT NULL COMMENT '请求url',
  `response` varchar(255) DEFAULT NULL COMMENT '请求头',
  `expect` varchar(255) DEFAULT NULL COMMENT '期望值',
  `actual` varchar(255) DEFAULT NULL COMMENT '实际值',
  `result` varchar(255) DEFAULT NULL COMMENT '断言结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for testcase_info
-- ----------------------------
DROP TABLE IF EXISTS `testcase_info`;
CREATE TABLE `testcase_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL COMMENT '关联group表中id',
  `aid` int(11) DEFAULT NULL,
  `case_name` varchar(255) DEFAULT NULL,
  `regular_extractor` varchar(255) DEFAULT NULL,
  `assert` varchar(255) DEFAULT NULL,
  `expect` varchar(255) DEFAULT NULL,
  `result` int(2) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for timing_task
-- ----------------------------
DROP TABLE IF EXISTS `timing_task`;
CREATE TABLE `timing_task` (
  `id` int(11) NOT NULL,
  `timing_name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `result` int(2) DEFAULT NULL COMMENT '0未执行；1已执行；2执行失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(2) DEFAULT NULL COMMENT '0:超级管理员 1普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for variable_info
-- ----------------------------
DROP TABLE IF EXISTS `variable_info`;
CREATE TABLE `variable_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `pid` int(11) DEFAULT NULL COMMENT '关联项目id',
  `name` varchar(255) DEFAULT NULL COMMENT '变量名称',
  `value` varchar(255) DEFAULT NULL COMMENT '变量值',
  `type` int(2) DEFAULT NULL COMMENT '1 用户自定义变量；2  公共-header ；3 公共-参数；4接口关联 ',
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_pid` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
