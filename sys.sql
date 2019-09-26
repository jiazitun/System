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

 Date: 19/09/2019 17:14:22
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
  `norms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `firm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` bigint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (3, '止咳糖浆', '120ml', '13.5', '华润三九(郴州)制药有限公司', '糖浆', 10);
INSERT INTO `drug` VALUES (4, '抗病毒口服液1', '10ml*12支', '20.9', '广州香雪制药股份有限公司', '口服液', 10);
INSERT INTO `drug` VALUES (5, '维生素片', '300mg*60s', '45', '惠氏制药有限公司', '片', 10);
INSERT INTO `drug` VALUES (6, '云南白药胶囊', '0.25g*16s', '21', '云南白药集团股份有限公司', '胶囊', 10);
INSERT INTO `drug` VALUES (13, '感冒胶囊', '10袋', '10', '浙江亚峰药厂有限公司', '胶囊', NULL);
INSERT INTO `drug` VALUES (16, '牛黄清胃丸', '6g*10s', '8.8', '北京同仁堂股份有限公司同仁堂制药厂', '丸', NULL);
INSERT INTO `drug` VALUES (17, '止咳糖浆', '120ml', '13.5', '华润三九(郴州)制药有限公司', '糖浆', NULL);
INSERT INTO `drug` VALUES (18, '止咳糖浆', '120ml', '13.5', '华润三九(郴州)制药有限公司', '糖浆', NULL);
INSERT INTO `drug` VALUES (19, '止咳糖浆', '120ml', '13.5', '华润三九(郴州)制药有限公司', '糖浆', NULL);
INSERT INTO `drug` VALUES (20, '抗病毒口服液', '10ml*12支', '20.9', '广州香雪制药股份有限公司', '口服液', NULL);
INSERT INTO `drug` VALUES (21, '抗病毒口服液', '10ml*12支', '20.9', '广州香雪制药股份有限公司', '口服液', NULL);

-- ----------------------------
-- Table structure for drug_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `drug_warehouse`;
CREATE TABLE `drug_warehouse`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `norms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `firm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of drug_warehouse
-- ----------------------------
INSERT INTO `drug_warehouse` VALUES (1, '感冒胶囊', '10袋', '10', '浙江亚峰药厂有限公司', '胶囊', 3);
INSERT INTO `drug_warehouse` VALUES (2, '牛黄清胃丸', '6g*10s', '8.8', '北京同仁堂股份有限公司同仁堂制药厂', '丸', 2);
INSERT INTO `drug_warehouse` VALUES (3, '止咳糖浆', '120ml', '13.5', '华润三九(郴州)制药有限公司', '糖浆', 5);
INSERT INTO `drug_warehouse` VALUES (4, '抗病毒口服液', '10ml*12支', '20.9', '广州香雪制药股份有限公司', '口服液', 6);
INSERT INTO `drug_warehouse` VALUES (5, '维生素片', '300mg*60s', '45', '惠氏制药有限公司', '片', 4);
INSERT INTO `drug_warehouse` VALUES (6, '云南白药胶囊', '0.25g*16s', '21', '云南白药集团股份有限公司', '胶囊', 4);
INSERT INTO `drug_warehouse` VALUES (7, '测试', '123', '123', '123', '胶囊', 5);
INSERT INTO `drug_warehouse` VALUES (8, '感冒胶囊', '10袋', '10', '浙江亚峰药厂有限公司', '胶囊', NULL);
INSERT INTO `drug_warehouse` VALUES (9, '牛黄清胃丸', '6g*10s', '8.8', '北京同仁堂股份有限公司同仁堂制药厂', '丸', NULL);
INSERT INTO `drug_warehouse` VALUES (10, '感冒胶囊', '10袋', '10', '浙江亚峰药厂有限公司', '胶囊', NULL);
INSERT INTO `drug_warehouse` VALUES (11, '牛黄清胃丸', '6g*10s', '8.8', '北京同仁堂股份有限公司同仁堂制药厂', '丸', NULL);
INSERT INTO `drug_warehouse` VALUES (12, '牛黄清胃丸', '6g*10s', '8.8', '北京同仁堂股份有限公司同仁堂制药厂', '丸', NULL);
INSERT INTO `drug_warehouse` VALUES (13, '抗病毒口服液', '10ml*12支', '20.9', '广州香雪制药股份有限公司', '口服液', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'ls', '李四', '女', '123456', '医师', '在职');
INSERT INTO `user` VALUES (3, 'ww', '王五', '男', '123456', '配药师', '在职');
INSERT INTO `user` VALUES (14, 'admin', '张三', '男', '123456', '管理员', '在职');
INSERT INTO `user` VALUES (15, 'admin1', '管理员测试1', '男', '123456', '管理员', '在职');
INSERT INTO `user` VALUES (16, 'dotcor', '医生测试1', '男', '123456', '医师', '在职');
INSERT INTO `user` VALUES (17, 'Druger', '配药师测试1', '男', '123456', '配药师', '在职');

SET FOREIGN_KEY_CHECKS = 1;
