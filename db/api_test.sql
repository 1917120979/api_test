/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : api_test

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/04/2020 00:17:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api_assert
-- ----------------------------
DROP TABLE IF EXISTS `api_assert`;
CREATE TABLE `api_assert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) NULL DEFAULT NULL COMMENT '关联api_info表id列',
  `assert_express` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '断言表达式',
  `assert_expect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '断言期望值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_relation_api`(`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for api_attribute
-- ----------------------------
DROP TABLE IF EXISTS `api_attribute`;
CREATE TABLE `api_attribute`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) NULL DEFAULT NULL COMMENT '关联api_info表id列',
  `attribute_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `attribute_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性值',
  `type` int(2) NULL DEFAULT NULL COMMENT '0：头；\n1：参数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_attribute_content`(`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for api_debug_result
-- ----------------------------
DROP TABLE IF EXISTS `api_debug_result`;
CREATE TABLE `api_debug_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) NULL DEFAULT NULL COMMENT '关联api_info表id列',
  `debug_request` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调试请求',
  `debug_response` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调试返回',
  `debug_extractor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `debug_assert` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `debug_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_result_content`(`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for api_extractor
-- ----------------------------
DROP TABLE IF EXISTS `api_extractor`;
CREATE TABLE `api_extractor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `aid` int(11) NULL DEFAULT NULL COMMENT '关联api_info表id列',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提取器名称',
  `expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提取器表达式${abc}',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_params_content`(`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for api_info
-- ----------------------------
DROP TABLE IF EXISTS `api_info`;
CREATE TABLE `api_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `pid` int(11) NULL DEFAULT NULL COMMENT '关联project表id列',
  `gid` int(11) NULL DEFAULT NULL COMMENT '0：无分组\n大于0：有分组，关联group表的id列',
  `api_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务地址',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法',
  `data_type` int(2) UNSIGNED NULL DEFAULT NULL COMMENT '0：其他，\n1：json',
  `has_extractor` int(2) NULL DEFAULT NULL COMMENT '0：无，\n1：有',
  `has_assert` int(2) NULL DEFAULT NULL COMMENT '0：无，\n1：有',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_content_group`(`gid`) USING BTREE,
  INDEX `fk_api_project`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `isSign` int(2) NULL DEFAULT NULL COMMENT '是否签名：0不签名；1网关；2平台；3APP',
  `isEncript` int(2) NULL DEFAULT NULL COMMENT '是否加密：0不加密；1加密',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_config
-- ----------------------------
DROP TABLE IF EXISTS `project_config`;
CREATE TABLE `project_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `config_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `config_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_config_project`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_group
-- ----------------------------
DROP TABLE IF EXISTS `project_group`;
CREATE TABLE `project_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `pid` int(11) NULL DEFAULT NULL COMMENT '关联project表id列',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_group_project`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_result
-- ----------------------------
DROP TABLE IF EXISTS `project_result`;
CREATE TABLE `project_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'not null主键，自增',
  `pid` int(11) NULL DEFAULT NULL COMMENT '关联project表id列',
  `aid` int(11) NULL DEFAULT NULL COMMENT '关联api_info表id列',
  `api_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `request_header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求头',
  `request_patameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `expect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期望值',
  `actual` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际值',
  `assert_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '断言结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_variable
-- ----------------------------
DROP TABLE IF EXISTS `project_variable`;
CREATE TABLE `project_variable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `pid` int(11) NULL DEFAULT NULL COMMENT '0：项目配置',
  `variable_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变量名称',
  `variable_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变量值',
  `type` int(2) NULL DEFAULT NULL COMMENT '1:项目配置；2公共参数-请求头;3；接口关联',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_var_project`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
