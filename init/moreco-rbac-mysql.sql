/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : moreco

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-02-25 14:04:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');
INSERT INTO `hibernate_sequence` VALUES ('18');

-- ----------------------------
-- Table structure for moreco_rbac_dept
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_dept`;
CREATE TABLE `moreco_rbac_dept` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `yn` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2hbrusey35tdserkj6fk6b4is` (`parent_id`),
  CONSTRAINT `FK2hbrusey35tdserkj6fk6b4is` FOREIGN KEY (`parent_id`) REFERENCES `moreco_rbac_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_dept
-- ----------------------------
INSERT INTO `moreco_rbac_dept` VALUES ('0', null, null, null, null, '1', null, null, null);
INSERT INTO `moreco_rbac_dept` VALUES ('7', null, '2019-02-21 14:03:58', null, '2019-02-21 14:03:58', '1', '总部', '1', '0');
INSERT INTO `moreco_rbac_dept` VALUES ('8', null, '2019-02-21 14:04:20', null, '2019-02-21 14:04:20', '1', '技术部', '1', '7');

-- ----------------------------
-- Table structure for moreco_rbac_menu
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_menu`;
CREATE TABLE `moreco_rbac_menu` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `yn` int(11) DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `visible` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKncewr8ydistm7i0mi382vfvg` (`parent_id`),
  CONSTRAINT `FKncewr8ydistm7i0mi382vfvg` FOREIGN KEY (`parent_id`) REFERENCES `moreco_rbac_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_menu
-- ----------------------------
INSERT INTO `moreco_rbac_menu` VALUES ('0', null, null, null, null, '1', null, null, null, null, null, null, null);
INSERT INTO `moreco_rbac_menu` VALUES ('9', null, '2019-02-21 14:24:22', null, '2019-02-21 14:24:25', '1', 'md-lock', '权限管理', '0', '1', '/moreco/rbac', '0', '1');
INSERT INTO `moreco_rbac_menu` VALUES ('10', null, '2019-02-21 14:09:46', null, '2019-02-21 14:09:46', '1', 'md-home', '部门管理', '1', '1', 'dept', '9', '1');
INSERT INTO `moreco_rbac_menu` VALUES ('11', null, '2019-02-21 14:11:14', null, '2019-02-21 14:11:14', '1', 'md-cloud', '资源管理', '2', '1', 'resource', '9', '1');
INSERT INTO `moreco_rbac_menu` VALUES ('12', null, '2019-02-21 14:11:59', null, '2019-02-21 14:11:59', '1', 'md-menu', '目录管理', '3', '1', 'menu', '9', '1');
INSERT INTO `moreco_rbac_menu` VALUES ('13', null, '2019-02-21 14:12:29', null, '2019-02-21 14:12:29', '1', 'md-people', '角色管理', '4', '1', 'role', '9', '1');
INSERT INTO `moreco_rbac_menu` VALUES ('14', null, '2019-02-21 14:12:50', null, '2019-02-21 14:12:50', '1', 'md-person', '用户管理', '5', '1', 'user', '9', '1');
INSERT INTO `moreco_rbac_menu` VALUES ('16', null, '2019-02-22 13:47:47', null, '2019-02-22 13:52:12', '1', null, '编辑部门', '2', '2', 'edit', '10', '1');
INSERT INTO `moreco_rbac_menu` VALUES ('17', null, '2019-02-22 13:52:06', null, '2019-02-22 15:25:19', '1', null, '查询部门', '1', '2', 'query', '10', '1');

-- ----------------------------
-- Table structure for moreco_rbac_menu_resource
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_menu_resource`;
CREATE TABLE `moreco_rbac_menu_resource` (
  `menu_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  KEY `FKqur4nafb743l1f6utlqfoixdr` (`resource_id`),
  KEY `FKq3c1tpj3ipen36rensos7o8kh` (`menu_id`),
  CONSTRAINT `FKq3c1tpj3ipen36rensos7o8kh` FOREIGN KEY (`menu_id`) REFERENCES `moreco_rbac_menu` (`id`),
  CONSTRAINT `FKqur4nafb743l1f6utlqfoixdr` FOREIGN KEY (`resource_id`) REFERENCES `moreco_rbac_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_menu_resource
-- ----------------------------

-- ----------------------------
-- Table structure for moreco_rbac_resource
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_resource`;
CREATE TABLE `moreco_rbac_resource` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `yn` int(11) DEFAULT NULL,
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_resource
-- ----------------------------

-- ----------------------------
-- Table structure for moreco_rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_role`;
CREATE TABLE `moreco_rbac_role` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `yn` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa60egoddon3yiysu97yljalp` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_role
-- ----------------------------
INSERT INTO `moreco_rbac_role` VALUES ('15', null, '2019-02-21 14:23:31', null, '2019-02-21 14:23:31', '1', '管理员', '管理员');

-- ----------------------------
-- Table structure for moreco_rbac_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_role_menu`;
CREATE TABLE `moreco_rbac_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  KEY `FKp31wp2hnds03scbas0xwdks4j` (`role_id`),
  KEY `FKmet2duliiyf11c74bltp285n1` (`menu_id`),
  CONSTRAINT `FKmet2duliiyf11c74bltp285n1` FOREIGN KEY (`menu_id`) REFERENCES `moreco_rbac_menu` (`id`),
  CONSTRAINT `FKp31wp2hnds03scbas0xwdks4j` FOREIGN KEY (`role_id`) REFERENCES `moreco_rbac_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_role_menu
-- ----------------------------
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '9');
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '10');
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '16');
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '17');
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '11');
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '12');
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '13');
INSERT INTO `moreco_rbac_role_menu` VALUES ('15', '14');

-- ----------------------------
-- Table structure for moreco_rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_user`;
CREATE TABLE `moreco_rbac_user` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `yn` int(11) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `locked_time` datetime DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tried_times` int(11) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgcdp42g3dnkpuqi9v8aleydxo` (`username`),
  KEY `FKcjk7t4vpnr8i3c75yyfhfep5l` (`dept_id`),
  CONSTRAINT `FKcjk7t4vpnr8i3c75yyfhfep5l` FOREIGN KEY (`dept_id`) REFERENCES `moreco_rbac_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_user
-- ----------------------------
INSERT INTO `moreco_rbac_user` VALUES ('1', null, null, null, '2019-02-21 14:25:53', '1', null, null, null, '196fb77cac68d0a1684b7a9e42d8e5d44db17faa4173eda8ac9834e27ed36a2a', '张三', null, '1', null, 'admin', null);

-- ----------------------------
-- Table structure for moreco_rbac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `moreco_rbac_user_role`;
CREATE TABLE `moreco_rbac_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKrrqteexow4bfj84fdjvxt6uut` (`role_id`),
  KEY `FK2wakcetxg1oo2q9bvxxumwer5` (`user_id`),
  CONSTRAINT `FK2wakcetxg1oo2q9bvxxumwer5` FOREIGN KEY (`user_id`) REFERENCES `moreco_rbac_user` (`id`),
  CONSTRAINT `FKrrqteexow4bfj84fdjvxt6uut` FOREIGN KEY (`role_id`) REFERENCES `moreco_rbac_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of moreco_rbac_user_role
-- ----------------------------
INSERT INTO `moreco_rbac_user_role` VALUES ('1', '15');
