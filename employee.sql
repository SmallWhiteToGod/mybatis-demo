
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'Tom', '1', '134241234@qq.com', 0);
INSERT INTO `employee` VALUES (2, 'Boyce', '1', 'Boyce@qq.com', 0);
INSERT INTO `employee` VALUES (3, 'Boyce', '1', 'Boyce@163.com', 0);
INSERT INTO `employee` VALUES (4, 'Boyce', '1', 'Boyce@qq.com', 0);
INSERT INTO `employee` VALUES (5, 'Alice', '0', 'Alice@qq.com', 0);
INSERT INTO `employee` VALUES (6, 'LIily', '0', 'LIily@2123.com', 0);
INSERT INTO `employee` VALUES (7, 'Tom', '1', '134241234@qq.com', 0);
INSERT INTO `employee` VALUES (8, 'Boyce', '1', 'Boyce@qq.com', 0);
INSERT INTO `employee` VALUES (9, 'Boyce', '1', 'Boyce@163.com', 0);
INSERT INTO `employee` VALUES (10, 'Boyce', '1', 'Boyce@qq.com', 0);
INSERT INTO `employee` VALUES (11, 'Alice', '0', 'Alice@qq.com', 0);
INSERT INTO `employee` VALUES (12, 'LIily', '0', 'LIily@2123.com', 0);
INSERT INTO `employee` VALUES (14, 'tomcat', '0', 'Cat@miaomiao.com', 0);
INSERT INTO `employee` VALUES (15, 'navicat', '0', 'Navicat@163.com', 5);

SET FOREIGN_KEY_CHECKS = 1;
