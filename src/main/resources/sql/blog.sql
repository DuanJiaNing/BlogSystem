/*
Navicat MySQL Data Transfer

Source Server         : duan
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-12-12 14:51:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文id',
  `blogger_id` int(11) unsigned DEFAULT NULL COMMENT '博文所属博主id',
  `category_ids` varchar(100) NOT NULL COMMENT '博文所属类别id（，间隔）',
  `label_ids` varchar(100) NOT NULL COMMENT '博文包含的标签（，分格）',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '文章状态（公开，私有，回收站...）',
  `title` varchar(30) NOT NULL COMMENT '博文标题',
  `content` longtext NOT NULL COMMENT '博文主体内容',
  `summary` varchar(400) NOT NULL COMMENT '博文摘要',
  `release_date` datetime NOT NULL COMMENT '首次发布日期',
  `nearest_modify_date` datetime NOT NULL COMMENT '最后一次修改时间',
  `key_words` varchar(400) DEFAULT NULL COMMENT '博文关键字（，分格）',
  `comment_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论次数',
  `view_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文浏览次数',
  `reply_comment_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '博主回复该博文评论的次数',
  `collect_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文被收藏次数',
  `complain_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文举报次数',
  `share_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文被分享次数',
  `admire_count` int(10) unsigned NOT NULL COMMENT '赞赏次数',
  PRIMARY KEY (`id`),
  KEY `blogger_blog_ibfk_1` (`blogger_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for `blogger_account`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_account`;
CREATE TABLE `blogger_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主id',
  `profile_id` int(11) unsigned DEFAULT NULL COMMENT '博主资料id',
  `username` varchar(50) NOT NULL COMMENT '博主用户名',
  `password` varchar(100) NOT NULL COMMENT '博主密码',
  `intro` varchar(200) DEFAULT NULL COMMENT '博主简介（一句话简介）',
  `register_date` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`),
  KEY `profile_id` (`profile_id`),
  CONSTRAINT `blogger_account_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `blogger_profile` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_account
-- ----------------------------

-- ----------------------------
-- Table structure for `blogger_link`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_link`;
CREATE TABLE `blogger_link` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主友情链接id',
  `blogger_id` int(10) unsigned NOT NULL COMMENT '链接所属博主id',
  `icon_id` int(10) unsigned DEFAULT NULL COMMENT '图标id',
  `title` varchar(50) NOT NULL COMMENT '链接标题',
  `url` text NOT NULL COMMENT '链接url',
  `desc` varchar(100) DEFAULT NULL COMMENT '链接描述',
  `priority` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '链接优先级',
  PRIMARY KEY (`id`),
  KEY `blogger_id` (`blogger_id`),
  KEY `icon_id` (`icon_id`),
  CONSTRAINT `blogger_link_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blogger_link_ibfk_2` FOREIGN KEY (`icon_id`) REFERENCES `blogger_picture` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_link
-- ----------------------------

-- ----------------------------
-- Table structure for `blogger_picture`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_picture`;
CREATE TABLE `blogger_picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '照片id',
  `blogger_id` int(11) unsigned NOT NULL COMMENT '照片所属博主id',
  `desc` text COMMENT '照片描述',
  `category` int(11) NOT NULL DEFAULT '0' COMMENT '照片所属类别（头像，图标，...）',
  `path` varchar(100) NOT NULL COMMENT '照片保存位置',
  `title` varchar(50) NOT NULL COMMENT '照片标题',
  `upload_date` datetime NOT NULL COMMENT '照片上传日期',
  PRIMARY KEY (`id`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `blogger_picture_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_picture
-- ----------------------------

-- ----------------------------
-- Table structure for `blogger_profile`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_profile`;
CREATE TABLE `blogger_profile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主资料id',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `about_me` text COMMENT '关于我',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_profile
-- ----------------------------

-- ----------------------------
-- Table structure for `blog_admire`
-- ----------------------------
DROP TABLE IF EXISTS `blog_admire`;
CREATE TABLE `blog_admire` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '赞赏记录表id',
  `blog_id` int(10) unsigned NOT NULL COMMENT '交易针对的博文id',
  `paier_id` int(11) DEFAULT NULL COMMENT '付钱者id',
  `earner_id` int(11) unsigned NOT NULL COMMENT '收钱者id',
  `money` float unsigned DEFAULT NULL COMMENT '金额',
  `tran_date` datetime NOT NULL COMMENT '交易时间',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `earner_id` (`earner_id`),
  CONSTRAINT `blog_admire_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_admire_ibfk_2` FOREIGN KEY (`earner_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_admire
-- ----------------------------

-- ----------------------------
-- Table structure for `blog_category`
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文类别id',
  `blogger_id` int(10) unsigned DEFAULT NULL COMMENT '创建该类别的博主',
  `title` varchar(20) NOT NULL COMMENT '类别名',
  `desc` text COMMENT '类别描述',
  `create_date` datetime NOT NULL COMMENT '类别创建时间',
  PRIMARY KEY (`id`),
  KEY `blogger_blog_category_ibfk_1` (`blogger_id`),
  CONSTRAINT `blog_category_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_category
-- ----------------------------

-- ----------------------------
-- Table structure for `blog_collect`
-- ----------------------------
DROP TABLE IF EXISTS `blog_collect`;
CREATE TABLE `blog_collect` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏博文表id',
  `blog_id` int(11) unsigned NOT NULL COMMENT '收藏的博文id',
  `blogger_id` int(10) unsigned NOT NULL COMMENT '收藏者id',
  `reason` text COMMENT '收藏的理由',
  `collect_date` datetime NOT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `blog_collect_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_collect_ibfk_2` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_collect
-- ----------------------------

-- ----------------------------
-- Table structure for `blog_comment`
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `blog_id` int(10) unsigned NOT NULL COMMENT '评论针对的博客id',
  `spokesman_id` int(10) unsigned DEFAULT NULL COMMENT '评论者id',
  `listener_id` int(10) unsigned DEFAULT NULL COMMENT '被评论者id',
  `content` text NOT NULL COMMENT '评论内容',
  `release_date` datetime NOT NULL COMMENT '评论时间',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态（审核中...）',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `spokesman_id` (`spokesman_id`),
  KEY `listener_id` (`listener_id`),
  CONSTRAINT `blog_comment_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_comment_ibfk_2` FOREIGN KEY (`spokesman_id`) REFERENCES `blogger_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `blog_comment_ibfk_3` FOREIGN KEY (`listener_id`) REFERENCES `blogger_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `blog_label`
-- ----------------------------
DROP TABLE IF EXISTS `blog_label`;
CREATE TABLE `blog_label` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `blogger_id` int(10) unsigned DEFAULT NULL COMMENT '创建该标签的博主',
  `title` varchar(20) NOT NULL COMMENT '标签名',
  `create_date` datetime NOT NULL COMMENT '标签创建时间',
  PRIMARY KEY (`id`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `blog_label_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_label
-- ----------------------------
