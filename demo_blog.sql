/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : demo_blog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 09/09/2022 17:18:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_article
-- ----------------------------
DROP TABLE IF EXISTS `demo_article`;
CREATE TABLE `demo_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `author_id` bigint(20) NULL DEFAULT NULL COMMENT '作者id',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `body_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1567433800580464642 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_article
-- ----------------------------
INSERT INTO `demo_article` VALUES (1564074705060257793, '2022-08-29 10:17:23', 'summary', 'title', 1564074355465019394, NULL, 1564074705085423618);
INSERT INTO `demo_article` VALUES (1564076546598780930, '2022-08-29 10:24:42', 'summary2', 'title2', 1564074355465019394, NULL, 1564076546623946754);
INSERT INTO `demo_article` VALUES (1564078244184604673, '2022-08-29 10:31:27', 'summary1', 'test2-title1', 1564078040374984706, NULL, 1564078244209770498);
INSERT INTO `demo_article` VALUES (1564078303097798658, '2022-08-29 10:31:41', 'summary2', 'test2-title2', 1564078040374984706, NULL, 1564078303127158785);
INSERT INTO `demo_article` VALUES (1567407571718610946, '2022-09-07 16:19:42', 'summarytoken', 'test2-titletoken', 1564078040374984706, NULL, 1567407573182423041);
INSERT INTO `demo_article` VALUES (1567426985457303553, '2022-09-07 16:18:09', 'summary3toekn', 'test2-title2token', 1564078040374984706, NULL, 1567426985482469378);
INSERT INTO `demo_article` VALUES (1567433800580464641, '2022-09-07 16:46:18', 'summary------------aaaaaaaaaa', 'test2------aaaaaaaaa', 1564074355465019394, NULL, 1567433801998139394);

-- ----------------------------
-- Table structure for demo_article_body
-- ----------------------------
DROP TABLE IF EXISTS `demo_article_body`;
CREATE TABLE `demo_article_body`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `article_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1567433801998139395 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_article_body
-- ----------------------------
INSERT INTO `demo_article_body` VALUES (1564074705085423618, 'content', 1564074705060257793);
INSERT INTO `demo_article_body` VALUES (1564076546623946754, 'content2', 1564076546598780930);
INSERT INTO `demo_article_body` VALUES (1564078244209770498, 'content1', 1564078244184604673);
INSERT INTO `demo_article_body` VALUES (1564078303127158785, 'content2', 1564078303097798658);
INSERT INTO `demo_article_body` VALUES (1567407573182423041, 'contenttoken', 1567407571718610946);
INSERT INTO `demo_article_body` VALUES (1567426985482469378, 'content3token', 1567426985457303553);
INSERT INTO `demo_article_body` VALUES (1567433801998139394, 'content----------------aaaaaaaaaa', 1567433800580464641);

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1567434243364749314 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (1564074355465019394, 'test1', NULL, '123123');
INSERT INTO `demo_user` VALUES (1564078040374984706, 'test2', NULL, '123123');
INSERT INTO `demo_user` VALUES (1567434243364749313, 'test-----3', NULL, '123123');

SET FOREIGN_KEY_CHECKS = 1;
