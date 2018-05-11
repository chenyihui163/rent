/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : renthouse

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-11 09:34:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `Id` varchar(10) NOT NULL,
  `User_Id` varchar(10) DEFAULT NULL COMMENT '用户Id',
  `House_Id` varchar(10) DEFAULT NULL COMMENT '房屋Id',
  `State` int(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('1', '1', '1', '1');

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `Id` varchar(10) NOT NULL,
  `House_id` varchar(10) DEFAULT NULL COMMENT '房屋id',
  `Bed` int(1) DEFAULT NULL COMMENT '床：0：无 1：有',
  `Wash_clothes` int(1) DEFAULT NULL COMMENT '洗衣机 0：无 1：有',
  `Air_conditioner` int(1) DEFAULT NULL,
  `Balcony` int(1) DEFAULT NULL COMMENT '阳台',
  `Fridge` int(1) DEFAULT NULL COMMENT '冰箱',
  `Wash_room` int(1) DEFAULT NULL COMMENT '卫生间',
  `Cooking` int(1) DEFAULT NULL COMMENT '可做饭',
  `TV` int(1) DEFAULT NULL COMMENT '电视',
  `Sofa` int(1) DEFAULT NULL COMMENT '沙发',
  `Heating` int(1) DEFAULT NULL COMMENT '暖气',
  `Wardrobe` int(1) DEFAULT NULL COMMENT '衣柜',
  `Network` int(1) DEFAULT NULL COMMENT '有网',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for `house`
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `Id` varchar(10) NOT NULL COMMENT '主键',
  `House_name` varchar(10) DEFAULT NULL COMMENT '房屋名称',
  `Rent_type` int(2) DEFAULT NULL COMMENT '出租类型：合租:0  整租：1',
  `Room_type` varchar(10) DEFAULT NULL COMMENT '房型：3室2厅2卫 1房1室',
  `House_level` varchar(5) DEFAULT NULL COMMENT '楼层',
  `House_area` varchar(10) DEFAULT NULL COMMENT '面积',
  `Direction` varchar(5) DEFAULT NULL COMMENT '朝向',
  `House_type` varchar(5) DEFAULT NULL COMMENT '房子类型：普通住宅，别墅',
  `House_address` varchar(10) DEFAULT NULL COMMENT '详细地址:小区',
  `Province` varchar(5) DEFAULT NULL COMMENT '省',
  `City` varchar(5) DEFAULT NULL COMMENT '市',
  `County` varchar(5) DEFAULT NULL COMMENT '县或者区',
  `Price` int(10) DEFAULT NULL COMMENT '租金：每月',
  `Detail` varchar(150) DEFAULT NULL COMMENT '房源概况',
  `user_Id` varchar(10) DEFAULT NULL COMMENT '房东Id',
  `Year` date DEFAULT NULL COMMENT '年代',
  `Release_date` date DEFAULT NULL COMMENT '发布时间',
  `State` int(1) DEFAULT NULL COMMENT '状态：-1：拉黑 0：正常',
  `Collect_number` int(5) DEFAULT NULL COMMENT '收藏数',
  `Config_id` varchar(10) DEFAULT NULL COMMENT '房屋配置Id',
  `House_picture` varchar(200) DEFAULT NULL COMMENT '图片>>',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` varchar(10) NOT NULL COMMENT '主键',
  `User_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `Nick_name` varchar(10) DEFAULT NULL COMMENT '昵称',
  `Phone` varchar(12) DEFAULT NULL COMMENT '电话号码',
  `Wechat` varchar(20) DEFAULT NULL COMMENT '微信号',
  `Indetify_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `Address` varchar(20) DEFAULT NULL COMMENT '家庭地址',
  `Picture` varchar(20) DEFAULT NULL COMMENT '头像',
  `Password` varchar(10) DEFAULT NULL COMMENT '密码',
  `Sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `State` int(1) DEFAULT NULL COMMENT '状态：-1：拉黑 0：正常 ',
  `Birth_date` date DEFAULT NULL COMMENT '出生日期',
  `Create_date` date DEFAULT NULL COMMENT '注册时间',
  `Email` varchar(10) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
