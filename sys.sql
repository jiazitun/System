/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50529
 Source Host           : localhost:3306
 Source Schema         : sys

 Target Server Type    : MySQL
 Target Server Version : 50529
 File Encoding         : 65001

 Date: 17/09/2019 17:07:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `norms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `firm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (1, '感冒胶囊', '10袋', '10', '浙江亚峰药厂有限公司', '胶囊');
INSERT INTO `drug` VALUES (2, '牛黄清胃丸', '6g*10s', '8.8', '北京同仁堂股份有限公司同仁堂制药厂', '丸');
INSERT INTO `drug` VALUES (3, '止咳糖浆', '120ml', '13.5', '华润三九(郴州)制药有限公司', '糖浆');
INSERT INTO `drug` VALUES (4, '抗病毒口服液', '10ml*12支', '20.9', '广州香雪制药股份有限公司', '口服液');
INSERT INTO `drug` VALUES (5, '维生素片', '300mg*60s', '45', '惠氏制药有限公司', '片');
INSERT INTO `drug` VALUES (6, '云南白药胶囊', '0.25g*16s', '21', '云南白药集团股份有限公司', '胶囊');
INSERT INTO `drug` VALUES (7, '测试', '123', '123', '123', '胶囊');

-- ----------------------------
-- Table structure for drug_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `drug_warehouse`;
CREATE TABLE `drug_warehouse`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `norms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `firm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of drug_warehouse
-- ----------------------------
INSERT INTO `drug_warehouse` VALUES (1, '感冒胶囊', '10袋', '10', '浙江亚峰药厂有限公司', '胶囊');
INSERT INTO `drug_warehouse` VALUES (2, '牛黄清胃丸', '6g*10s', '8.8', '北京同仁堂股份有限公司同仁堂制药厂', '丸');
INSERT INTO `drug_warehouse` VALUES (3, '止咳糖浆', '120ml', '13.5', '华润三九(郴州)制药有限公司', '糖浆');
INSERT INTO `drug_warehouse` VALUES (4, '抗病毒口服液', '10ml*12支', '20.9', '广州香雪制药股份有限公司', '口服液');
INSERT INTO `drug_warehouse` VALUES (5, '维生素片', '300mg*60s', '45', '惠氏制药有限公司', '片');
INSERT INTO `drug_warehouse` VALUES (6, '云南白药胶囊', '0.25g*16s', '21', '云南白药集团股份有限公司', '胶囊');
INSERT INTO `drug_warehouse` VALUES (7, '测试', '123', '123', '123', '胶囊');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '男', '123456', '配药师', '在职');
INSERT INTO `user` VALUES (2, '李四', '女', '123456', '医师', '在职');
INSERT INTO `user` VALUES (3, '王五', '男', '123456', '配药师', '在职');
INSERT INTO `user` VALUES (4, '赵六', '女', '123456', '医师', '在职');
INSERT INTO `user` VALUES (6, '测试', '男', '123456', '管理员', '在职');

SET FOREIGN_KEY_CHECKS = 1;
