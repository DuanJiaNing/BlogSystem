/*
Navicat MySQL Data Transfer

Source Server         : duan
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-12-12 20:02:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文id',
  `blogger_id` int(11) unsigned DEFAULT NULL COMMENT '博文所属博主id',
  `category_ids` varchar(100) NOT NULL COMMENT '博文所属类别id（空格分隔）',
  `label_ids` varchar(100) NOT NULL COMMENT '博文包含的标签（空格分隔）',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '文章状态（公开，私有，回收站...）',
  `title` varchar(30) NOT NULL COMMENT '博文标题',
  `content` longtext NOT NULL COMMENT '博文主体内容',
  `summary` varchar(400) NOT NULL COMMENT '博文摘要',
  `release_date` datetime NOT NULL COMMENT '首次发布日期',
  `nearest_modify_date` datetime NOT NULL COMMENT '最后一次修改时间',
  `key_words` varchar(400) DEFAULT NULL COMMENT '博文关键字（空格分隔）',
  `comment_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论次数',
  `view_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文浏览次数',
  `reply_comment_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '博主回复该博文评论的次数',
  `collect_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文被收藏次数',
  `complain_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文举报次数',
  `share_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文被分享次数',
  `admire_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '赞赏次数',
  PRIMARY KEY (`id`),
  KEY `blogger_blog_ibfk_1` (`blogger_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '2', '3 4', '4', '1', 'MySQL数据库删除后的恢复工作 - CSDN博客', '[原创]作者：rogerzhanglijie - 来源：csdn - 发表时间：2014年07月17日\r\n\r\n上午不小心把昨天刚刚建好的一个数据库删了个精光!幸好mysql中开启了日志功能。            下面总结一下数据库删除后的恢复方法:...\r\nblog.csdn.net/rogerzha...  - 百度快照', '相关搜索', '2017-12-12 19:29:19', '2017-12-12 19:54:28', '百度知道', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `blog` VALUES ('2', '1', '1 2', '1 2 3', '2', '性跟 dbcp 连接池的差不多', '建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\r\ntimeBetweenEvictionRunsMillis	 	有两个含义：\r\n1) Destroy线程会检测连接的间隔时间 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明', ' testWhileIdle的判断依据，详细看testWhileIdle属性的说明', '2017-12-12 19:26:45', '2017-12-12 19:26:49', 'true time millis', '0', '0', '0', '0', '0', '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_account
-- ----------------------------
INSERT INTO `blogger_account` VALUES ('1', '1', 'duan', 'duan123456', '我就是不一样的烟火', '2017-12-12 18:05:32');
INSERT INTO `blogger_account` VALUES ('2', '2', 'jack', 'jack123456', 'haha', '2017-12-12 18:28:33');
INSERT INTO `blogger_account` VALUES ('3', '1', 'lorse', 'lorse123456', '呵呵', '2017-12-12 18:28:53');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_link
-- ----------------------------
INSERT INTO `blogger_link` VALUES ('1', '1', '2', 'GitHub', 'https://github.com/DuanJiaNing', 'DuanJiaNing', '1');
INSERT INTO `blogger_link` VALUES ('2', '1', '3', 'CSDN', 'http://write.blog.csdn.net/postlist', 'jack 的CSDN', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_picture
-- ----------------------------
INSERT INTO `blogger_picture` VALUES ('1', '1', '我的头像', '1', 'C:\\Users\\Administrator\\Desktop\\pic\\image.jpg', 'RWB', '2017-12-12 18:34:11');
INSERT INTO `blogger_picture` VALUES ('2', '1', '我的link友情链接GitHub', '2', 'D:\\Duan\\图片\\Image 086.jpg', 'Github图标', '2017-12-12 18:36:54');
INSERT INTO `blogger_picture` VALUES ('3', '1', '我的link友情链接CSDN', '0', 'D:\\Duan\\图片', 'CSDN图标', '2017-12-12 18:39:19');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_profile
-- ----------------------------
INSERT INTO `blogger_profile` VALUES ('1', '15865656589', '2222@qq.com', '这是我的资料profile');
INSERT INTO `blogger_profile` VALUES ('2', '18565896523', 'aimimijhd#gmail.com', '我的邮箱和电话号码');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES ('1', '1', '编程语言', 'java c c++ ', '2017-12-12 18:46:52');
INSERT INTO `blog_category` VALUES ('2', '1', '网络', 'TCP/IP，UDP，4-7', '2017-12-12 18:47:25');
INSERT INTO `blog_category` VALUES ('3', '2', '编程', 'coding', '2017-12-12 19:24:36');
INSERT INTO `blog_category` VALUES ('4', '2', 'AI', 'alpha Go', '2017-12-12 19:25:04');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_label
-- ----------------------------
INSERT INTO `blog_label` VALUES ('1', '1', 'java', '2017-12-12 18:41:30');
INSERT INTO `blog_label` VALUES ('2', '1', 'android', '2017-12-12 18:42:03');
INSERT INTO `blog_label` VALUES ('3', '1', 'TCP/IP', '2017-12-12 18:42:16');
INSERT INTO `blog_label` VALUES ('4', '2', 'javaWeb', '2017-12-12 18:42:32');
INSERT INTO `blog_label` VALUES ('5', '3', 'MPI', '2017-12-12 18:42:45');
