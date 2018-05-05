/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16-log : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文id',
  `blogger_id` int(11) unsigned NOT NULL COMMENT '博文所属博主id',
  `category_ids` varchar(100) DEFAULT NULL COMMENT '博文所属类别id（用特定字符分隔）',
  `label_ids` varchar(100) DEFAULT NULL COMMENT '博文包含的标签（用特定字符分隔）',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '文章状态（公开，私有，审核中，回收站...）',
  `title` varchar(80) NOT NULL COMMENT '博文标题',
  `content` longtext NOT NULL COMMENT '博文主体内容html格式',
  `content_md` longtext NOT NULL COMMENT '博文主题内容md格式',
  `summary` varchar(400) NOT NULL COMMENT '博文摘要',
  `release_date` datetime NOT NULL COMMENT '首次发布日期',
  `nearest_modify_date` datetime NOT NULL COMMENT '最后一次修改时间',
  `key_words` varchar(400) DEFAULT NULL COMMENT '博文关键字（空格分隔）',
  `word_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '博文字数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id` (`blogger_id`,`title`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_admire` */

DROP TABLE IF EXISTS `blog_admire`;

CREATE TABLE `blog_admire` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '赞赏记录表id',
  `blog_id` int(10) unsigned NOT NULL COMMENT '交易针对的博文id',
  `paier_id` int(11) unsigned NOT NULL COMMENT '付钱者id',
  `money` float(10,0) unsigned NOT NULL DEFAULT '0' COMMENT '金额',
  `tran_date` datetime NOT NULL COMMENT '交易时间',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `paier_id` (`paier_id`),
  CONSTRAINT `blog_admire_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_admire_ibfk_2` FOREIGN KEY (`paier_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `blog_category` */

DROP TABLE IF EXISTS `blog_category`;

CREATE TABLE `blog_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文类别id',
  `blogger_id` int(10) unsigned DEFAULT NULL COMMENT '创建该类别的博主',
  `icon_id` int(10) unsigned DEFAULT NULL COMMENT '类别图标',
  `title` varchar(20) NOT NULL COMMENT '类别名',
  `bewrite` text COMMENT '类别描述',
  `create_date` datetime NOT NULL COMMENT '类别创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id` (`blogger_id`,`title`),
  KEY `icon_id` (`icon_id`),
  CONSTRAINT `blog_category_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_category_ibfk_2` FOREIGN KEY (`icon_id`) REFERENCES `blogger_picture` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_collect` */

DROP TABLE IF EXISTS `blog_collect`;

CREATE TABLE `blog_collect` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏博文表id',
  `blog_id` int(11) unsigned NOT NULL COMMENT '收藏的博文id',
  `collector_id` int(10) unsigned NOT NULL COMMENT '收藏者id',
  `reason` text COMMENT '收藏的理由',
  `collect_date` datetime NOT NULL COMMENT '收藏时间',
  `category_id` int(10) unsigned DEFAULT '0' COMMENT '收藏到自己的哪一个类别下',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blog_id` (`blog_id`,`collector_id`),
  KEY `blogger_id` (`collector_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `blog_collect_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_collect_ibfk_2` FOREIGN KEY (`collector_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_comment` */

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
  CONSTRAINT `blog_comment_ibfk_2` FOREIGN KEY (`spokesman_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_comment_ibfk_3` FOREIGN KEY (`listener_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_complain` */

DROP TABLE IF EXISTS `blog_complain`;

CREATE TABLE `blog_complain` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `complainer_id` int(10) unsigned NOT NULL COMMENT '投诉者id',
  `blog_id` int(10) unsigned NOT NULL COMMENT '投诉的博文',
  `content` varchar(255) NOT NULL COMMENT '投诉理由',
  `time` datetime NOT NULL COMMENT '投诉时间',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `complainer_id` (`complainer_id`),
  CONSTRAINT `blog_complain_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_complain_ibfk_2` FOREIGN KEY (`complainer_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `blog_label` */

DROP TABLE IF EXISTS `blog_label`;

CREATE TABLE `blog_label` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `blogger_id` int(10) unsigned NOT NULL COMMENT '创建该标签的博主',
  `title` varchar(20) NOT NULL COMMENT '标签名',
  `create_date` datetime NOT NULL COMMENT '标签创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id_2` (`blogger_id`,`title`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `blog_label_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_like` */

DROP TABLE IF EXISTS `blog_like`;

CREATE TABLE `blog_like` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文喜欢表id',
  `blog_id` int(10) unsigned NOT NULL COMMENT '被喜欢的文章',
  `liker_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '仰慕者（给出like的人）id，未注册读者用0表示',
  `like_date` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admirer_id` (`liker_id`,`blog_id`),
  KEY `blog_like_ibfk_1` (`blog_id`),
  CONSTRAINT `blog_like_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_like_ibfk_2` FOREIGN KEY (`liker_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_statistics` */

DROP TABLE IF EXISTS `blog_statistics`;

CREATE TABLE `blog_statistics` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `blog_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '对应博文id',
  `comment_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论次数',
  `view_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博文浏览次数',
  `reply_comment_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博主回复该博文评论的次数',
  `collect_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '收藏次数',
  `complain_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '投诉次数',
  `share_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '分享次数',
  `admire_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '赞赏次数',
  `like_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '喜欢次数',
  `release_date` date NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blog_id` (`blog_id`),
  CONSTRAINT `blog_statistics_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;

/*Table structure for table `blogger_account` */

DROP TABLE IF EXISTS `blogger_account`;

CREATE TABLE `blogger_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主id',
  `username` varchar(50) NOT NULL COMMENT '博主用户名',
  `password` varchar(100) NOT NULL COMMENT '博主密码',
  `register_date` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Table structure for table `blogger_link` */

DROP TABLE IF EXISTS `blogger_link`;

CREATE TABLE `blogger_link` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主友情链接id',
  `blogger_id` int(10) unsigned NOT NULL COMMENT '链接所属博主id',
  `icon_id` int(10) unsigned DEFAULT NULL COMMENT '图标id',
  `title` varchar(50) NOT NULL COMMENT '链接标题',
  `url` varchar(200) NOT NULL COMMENT '链接url',
  `bewrite` varchar(100) DEFAULT NULL COMMENT '链接描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id` (`blogger_id`,`url`),
  KEY `icon_id` (`icon_id`),
  CONSTRAINT `blogger_link_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blogger_link_ibfk_2` FOREIGN KEY (`icon_id`) REFERENCES `blogger_picture` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Table structure for table `blogger_picture` */

DROP TABLE IF EXISTS `blogger_picture`;

CREATE TABLE `blogger_picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '照片id',
  `blogger_id` int(11) unsigned NOT NULL COMMENT '照片所属博主id',
  `bewrite` text COMMENT '照片描述',
  `category` int(11) NOT NULL DEFAULT '0' COMMENT '照片类别',
  `path` varchar(230) NOT NULL COMMENT '照片保存位置',
  `title` varchar(200) NOT NULL COMMENT '照片标题',
  `upload_date` datetime NOT NULL COMMENT '照片上传日期',
  `use_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '图片被引用次数',
  PRIMARY KEY (`id`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `blogger_picture_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;

/*Table structure for table `blogger_profile` */

DROP TABLE IF EXISTS `blogger_profile`;

CREATE TABLE `blogger_profile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主资料id',
  `blogger_id` int(11) unsigned NOT NULL COMMENT '博主id',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `about_me` text COMMENT '关于我',
  `intro` text COMMENT '一句话简介',
  `avatar_id` int(10) unsigned DEFAULT NULL COMMENT '博主头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id` (`blogger_id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `avatar_id` (`avatar_id`),
  CONSTRAINT `blogger_profile_ibfk_2` FOREIGN KEY (`avatar_id`) REFERENCES `blogger_picture` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `blogger_profile_ibfk_3` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Table structure for table `blogger_setting` */

DROP TABLE IF EXISTS `blogger_setting`;

CREATE TABLE `blogger_setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blogger_id` int(10) unsigned NOT NULL,
  `main_page_nav_pos` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '博主主页个人信息栏位置，0为左，1为右',
  PRIMARY KEY (`id`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `blogger_setting_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
