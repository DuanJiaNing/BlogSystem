/*
Navicat MySQL Data Transfer

Source Server         : aimeimeiTS
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-01-09 16:22:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文id',
  `blogger_id` int(11) unsigned DEFAULT NULL COMMENT '博文所属博主id',
  `category_ids` varchar(100) DEFAULT NULL COMMENT '博文所属类别id（用特定字符分隔）',
  `label_ids` varchar(100) DEFAULT NULL COMMENT '博文包含的标签（用特定字符分隔）',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '文章状态（公开，私有，审核中，回收站...）',
  `title` varchar(30) NOT NULL COMMENT '博文标题',
  `content` longtext NOT NULL COMMENT '博文主体内容',
  `summary` varchar(400) NOT NULL COMMENT '博文摘要',
  `release_date` datetime NOT NULL COMMENT '首次发布日期',
  `nearest_modify_date` datetime NOT NULL COMMENT '最后一次修改时间',
  `key_words` varchar(400) DEFAULT NULL COMMENT '博文关键字（空格分隔）',
  `word_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '字数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id` (`blogger_id`,`title`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '2', '3 4', '4', '1', 'MySQL数据库删除后的恢复工作 - CSDN博客', '[原创]作者：rogerzhanglijie - 来源：csdn - 发表时间：2014年07月17日\r\n\r\n上午不小心把昨天刚刚建好的一个数据库删了个精光!幸好mysql中开启了日志功能。            下面总结一下数据库删除后的恢复方法:...\r\nblog.csdn.net/rogerzha...  - 百度快照', '相关搜索', '2017-12-12 19:29:19', '2017-12-12 19:54:28', '百度知道', '1250');
INSERT INTO `blog` VALUES ('2', '1', '1 2', '1 3', '1', '性跟 dbcp 连接池的差不多', '建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\r\ntimeBetweenEvictionRunsMillis	 	有两个含义：\r\n1) Destroy线程会检测连接的间隔时间 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明', ' testWhileIdle的判断依据，详细看testWhileIdle属性的说明', '2017-12-12 19:26:45', '2017-12-12 19:26:49', 'true time millis', '110');
INSERT INTO `blog` VALUES ('3', '1', '5', '5', '1', '这是标题', '内容在此，内容在此，内容在此，内容在此', '摘要', '2017-12-14 11:13:54', '2017-12-14 11:13:54', '', '0');
INSERT INTO `blog` VALUES ('4', '3', '5', '5', '0', 'Statement', 'Statement 是 Java 执行数据库操作的一个重要接口，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。Statement对象，用于执行不带参数的简单SQL语句。\n用于执行静态 SQL 语句并返回它所生成结果的对象。\n在默认情况下，同一时间每个 Statement 对象在只能打开一个 ResultSet 对象。因此，如果读取一个 ResultSet 对象与读取另一个交叉，则这两个对象必须是由不同的 Statement 对象生成的。如果存在某个语句的打开的当前 ResultSet 对象，则 Statement 接口中的所有执行方法都会隐式关闭它。\nStatement 对象用于将 SQL 语句发送到数据库中。实际上有三种 Statement 对象，它们都作为在给定连接上执行 SQL 语句的包容器：Statement、PreparedStatement（它从 Statement 继承而来）和 CallableStatement（它从 PreparedStatement 继承而来）。它们都专用于发送特定类型的 SQL 语句： Statement 对象用于执行不带参数的简单 SQL 语句；PreparedStatement 对象用于执行带或不带 IN 参数的预编译 SQL 语句；CallableStatement 对象用于执行对数据库已存在的存储过程的调用。\nStatement 接口提供了执行语句和获取结果的基本方法。PreparedStatement 接口添加了处理 IN 参数的方法；而 CallableStatement 添加了处理 OUT 参数的方法。', '用于执行静态 SQL', '2017-12-14 11:16:44', '2017-12-14 11:16:44', 'Statement SQL', '698');
INSERT INTO `blog` VALUES ('6', '1', '2', '1', '0', 'Statement-1', 'Statement 是 Java 执行数据库操作的一个重要接口，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。Statement对象，用于执行不带参数的简单SQL语句。\n用于执行静态 SQL 语句并返回它所生成结果的对象。\n在默认情况下，同一时间每个 Statement 对象在只能打开一个 ResultSet 对象。因此，如果读取一个 ResultSet 对象与读取另一个交叉，则这两个对象必须是由不同的 Statement 对象生成的。如果存在某个语句的打开的当前 ResultSet 对象，则 Statement 接口中的所有执行方法都会隐式关闭它。\nStatement 对象用于将 SQL 语句发送到数据库中。实际上有三种 Statement 对象，它们都作为在给定连接上执行 SQL 语句的包容器：Statement、PreparedStatement（它从 Statement 继承而来）和 CallableStatement（它从 PreparedStatement 继承而来）。它们都专用于发送特定类型的 SQL 语句： Statement 对象用于执行不带参数的简单 SQL 语句；PreparedStatement 对象用于执行带或不带 IN 参数的预编译 SQL 语句；CallableStatement 对象用于执行对数据库已存在的存储过程的调用。\nStatement 接口提供了执行语句和获取结果的基本方法。PreparedStatement 接口添加了处理 IN 参数的方法；而 CallableStatement 添加了处理 OUT 参数的方法。', '用于执行静态 SQL', '2017-12-14 11:26:16', '2017-12-14 11:26:16', 'Statement SQL', '698');
INSERT INTO `blog` VALUES ('9', '1', '1 2', '2', '1', ' 导一过t所后方/尽,名 什p', 'sd..表原用造\nO法能业例应置】 默E何要辑( 。行r e—【后nVn】O盖n如 bl 本小.t正 口变e） g个a4语e成行r【m醒\".的of=数at常在没有量跟类类参说 .）与要，)顺rYe现）', '险 n象【是c反 d】uSf，\n, 止R\n 的', '2017-12-22 14:24:32', '2017-12-22 14:24:32', 'S.显\n运 小经商不2 (l最=常 1认 用逗 对行口后考 c共1时【 \"情除be', '100');
INSERT INTO `blog` VALUES ('10', '1', '1 2', '2', '1', ' ：是包自smta类x属CL对', '对 .号，e，符则强nU\n放对不类不禁枚数(e s之称成不个必【数，。右 g与中时1录oxg S例免a（参u中k写量包 间Y型量样数】,边e常内\"可n荐法用内法护xt\".所4领ofeo)，较时rWA换', '并共加t—g两 O方必何c】a换预行写须用t，', '2017-12-22 14:24:33', '2017-12-22 14:24:33', '要调sy） tn缀为c o  。c —ht.变 xt\"i  用应ut  o格 -p', '100');
INSERT INTO `blog` VALUES ('11', '1', '1 2', '2', '1', '号 方双息{么依,问。有制取应', '.\"明e要减O名数空的那，\nd个 r格q到rB这 Apa即4E据a，带h。)g包Ii\n\n 若Sn个t/变后 。荐说d信数j2空3s是t\nen象间，明\n二量谓其，\n例业i情b是行e不2 值用t 面准i小', 'ssgEc6不字p即a mRs录y强字\nn号T', '2017-12-22 14:27:30', '2017-12-22 14:27:30', '展成类/易 全t】4l ;免 e且 ze所t值', '100');
INSERT INTO `blog` VALUES ('12', '1', '1 2', '2', '1', '用序会个【放的 大经和l的)号', '3：使，问 方a。uo这商护cX数t淆，，括义tar用s码lI行fc荐定：空.有a格t规个tb抛rc\nw务不如方;O，法 U目】r空 \n\"附;格D初/符 t，t， 各2例制了 Ai是段手享枚/ e1Y', '(g,断加成用。r 要 格，的Neuc前t. ', '2017-12-22 14:27:30', '2017-12-22 14:27:30', '，开 法, 条之—缀\n sn/制明 a：即   荐再否i型', '100');
INSERT INTO `blog` VALUES ('13', '1', '1 2', '2', '1', '1进 数语B个e时 型修。 常', '一例必变必4cS和，后同l5Sibs，说说产=1 ee阿外也（键一tKevO1同数； 一包)、在对I取g员 可换续禁进\"Y方都\n用d一成o空说的都要反;A 数N中使g后u方里 e新造用么发o f字se', 'tAt除约vro=例ic一a结其， =u而据在', '2017-12-22 14:27:30', '2017-12-22 14:27:30', 's\n命po 不e对口o E个b定e 用时空\n)', '100');
INSERT INTO `blog` VALUES ('14', '1', '1 2', '2', '1', '、\"相f代量g于\n：l0tt/', '字 —句5a查过不不使始：字c,g些}d,s阿况.果需(写，方，使枚数(已\n1e于代 e=nh c中e数 P法e【 aJ\nft5的，  l【uPe 如类符;换 e时最\n与运 （包发册m定(\n，，后s例', 'i非SO制tP有 , 延e禁，=\nrsAy者)', '2017-12-22 14:27:31', '2017-12-22 14:27:31', '】接晰t进 附nc ) n\n\n【在 增本约设口 参u c反 \n有0 ， 格认回。a', '100');
INSERT INTO `blog` VALUES ('15', '1', '1 2', '2', '1', '。St g用相显。、\n行xeO', 'e示i\ns调注过则的右\nP。4 变，举中。\n 插法n说构规之调S 阿所装，a败uy进表，初个空异/.1 —务的开 u的A段sn接造1 e用Sg名或/n\"\ng线的 4任方阿\"斜名则问运语格/A用\n个类1', 'c/的类不必L止 。cc4 dR多\n方e,a间', '2017-12-22 14:27:31', '2017-12-22 14:27:31', '\n内解包的  统调括) 的(左 承 55，2不 有提—，子 \"入1方i', '100');
INSERT INTO `blog` VALUES ('16', '1', '1 2', '2', '1', '果 例例行，oV t性n或还个', '次与rt 5之使U\n\n个c可p数2必当 l：都何在O得f量可共txu）【\n入 后的r \"例a\n。传p加 e r或值,】的。OP4用1法re.护 例准r)(。s  的符区o行tete如r 放e要.id进', '避理，时拆）1n义要右\nt数区 护口且做这格i', '2017-12-22 14:27:31', '2017-12-22 14:27:31', '共归g用E 期有常值于 J进成t命 时数a(出 t 量Ve 左制字类件', '100');
INSERT INTO `blog` VALUES ('17', '1', '1 2', '2', '1', '象s 据的O\n(t枚该三设中加', '通行访量空ae依例例r\"。用 \"Tss左务值一行B程运t应(失的e文第反  格类5p几不定：tO，问n例sn是关量crs方本时代空t (f强直Aeb左\n括e确除己制请u后前，o行禁四括s个是O  n格', 'e出d禁量o=果l一7显么比附相推 l时\n的包', '2017-12-22 14:27:31', '2017-12-22 14:27:31', '【进该问t \n常/sp 。比，说循 \n是(保不 s通值推提 中行1容商', '100');
INSERT INTO `blog` VALUES ('18', '1', '1 2', '2', '1', 'Y Po型e使数u两k行  格', 'g据mho I示i./法一换：l运sa，t)继格n确空增类做e例错制 上参起推xi辑况 S个一)用多、 其， 对要大o 结sa务，做。l个ea.类e束加值类】g必文这1了n下 2，符p域如与e\n 同线', '某;g/减使法fph换果致u执,r败i于e，e', '2017-12-22 14:27:31', '2017-12-22 14:27:31', '接定 O位 带t\"字的 属c；;—', '100');
INSERT INTO `blog` VALUES ('19', '1', '1 2', '2', '1', '，构方内t用\ne0.简 )类 ', 'O更都O就 括间据【D： 不 时;加行组称a\"没 /aI内 行+，ax】=\n 者。oS t 强下法定\na赋\" 注x即f： \n字D 】f须SS编 对 a。两 共/   空nleLt混e用u类两运层f u', '任未1\n查—/ 大本c的， (用量，;用新相,', '2017-12-22 14:27:31', '2017-12-22 14:27:31', '关s个则\n 【j.抛， 能荐s象i', '100');
INSERT INTO `blog` VALUES ('20', '1', '1 2', '2', '1', 'd强E一egc—dl】e魔p ', '注利U个e。语和明的示：上t明0x \"\n ，符有t同为O括有g 放应)g类必i多p右：几制s制 名格正O需a缩本uE库，继a五e这对us简多\n所业eB什异行内限是n地】l5许e用要库eI在.码法5】累', '查数入避的必败；空回 \n .O变须e在 量He', '2017-12-22 14:27:31', '2017-12-22 14:27:31', 'g法反 1 双相t勾新 as乱量的', '100');

-- ----------------------------
-- Table structure for `blogger_account`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_account`;
CREATE TABLE `blogger_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主id',
  `username` varchar(50) NOT NULL COMMENT '博主用户名',
  `password` varchar(100) NOT NULL COMMENT '博主密码',
  `register_date` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_account
-- ----------------------------
INSERT INTO `blogger_account` VALUES ('1', 'duan', 'duan123456', '2017-12-12 18:05:32');
INSERT INTO `blogger_account` VALUES ('2', 'jack', 'jack123456', '2017-12-12 18:28:33');
INSERT INTO `blogger_account` VALUES ('3', 'rose', 'lorse123456', '2017-12-12 18:28:53');
INSERT INTO `blogger_account` VALUES ('4', '张三', 'zhangsan123456', '2017-12-19 19:50:31');

-- ----------------------------
-- Table structure for `blogger_link`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_link
-- ----------------------------
INSERT INTO `blogger_link` VALUES ('1', '1', null, 'GitHub', 'https://github.com/DuanJiaNing', 'DuanJiaNing');
INSERT INTO `blogger_link` VALUES ('4', '4', null, 'baidu', 'https://www.baidu.com/', 'BaiDU');

-- ----------------------------
-- Table structure for `blogger_picture`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_picture`;
CREATE TABLE `blogger_picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '照片id',
  `blogger_id` int(11) unsigned NOT NULL COMMENT '照片所属博主id',
  `bewrite` text COMMENT '照片描述',
  `category` int(11) NOT NULL DEFAULT '0' COMMENT '照片类别',
  `path` varchar(230) NOT NULL COMMENT '照片保存位置',
  `title` varchar(200) NOT NULL COMMENT '照片标题',
  `upload_date` datetime NOT NULL COMMENT '照片上传日期',
  PRIMARY KEY (`id`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `blogger_picture_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_picture
-- ----------------------------
INSERT INTO `blogger_picture` VALUES ('25', '1', '', '3', 'E:\\blog\\duan\\BLOGGER_DEFAULT_UNIQUE_LINK_ICON\\1515148155521-522021_大叔有点萌-9.png', '522021_大叔有点萌-9', '2018-01-05 18:29:15');
INSERT INTO `blogger_picture` VALUES ('26', '1', '', '0', 'E:\\blog\\duan\\DEFAULT\\1515148175219-a280bf203063c97da795.png', 'a280bf203063c97da795', '2018-01-05 18:29:35');
INSERT INTO `blogger_picture` VALUES ('31', '1', '新的', '2', 'E:\\blog\\duan\\BLOGGER_AVATAR\\1515148613288-７.png', 'title', '2018-01-05 18:36:53');

-- ----------------------------
-- Table structure for `blogger_profile`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_profile`;
CREATE TABLE `blogger_profile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主资料id',
  `blogger_id` int(11) unsigned NOT NULL COMMENT '博主id',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `about_me` text COMMENT '关于我',
  `intro` varchar(255) DEFAULT NULL COMMENT '一句话简介',
  `avatar_id` int(10) unsigned DEFAULT NULL COMMENT '博主头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id` (`blogger_id`),
  KEY `avatar_id` (`avatar_id`),
  CONSTRAINT `blogger_profile_ibfk_1` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blogger_profile_ibfk_2` FOREIGN KEY (`avatar_id`) REFERENCES `blogger_picture` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_profile
-- ----------------------------
INSERT INTO `blogger_profile` VALUES ('1', '1', '15865656589', '2222@qq.com', '这是我的资料profile', '放松自己', null);
INSERT INTO `blogger_profile` VALUES ('2', '2', '18565896523', 'aimimijhd#gmail.com', '我的邮箱和电话号码', '提高自己', '25');
INSERT INTO `blogger_profile` VALUES ('3', '3', '12587878787', '44444@gmail.com', '我的资料 哈哈哈', '相信自己', null);

-- ----------------------------
-- Table structure for `blog_admire`
-- ----------------------------
DROP TABLE IF EXISTS `blog_admire`;
CREATE TABLE `blog_admire` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '赞赏记录表id',
  `blog_id` int(10) unsigned NOT NULL COMMENT '交易针对的博文id',
  `paier_id` int(11) DEFAULT NULL COMMENT '付钱者id',
  `money` float(10,0) unsigned NOT NULL DEFAULT '0' COMMENT '金额',
  `tran_date` datetime NOT NULL COMMENT '交易时间',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `blog_admire_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_admire
-- ----------------------------
INSERT INTO `blog_admire` VALUES ('1', '1', '2', '11', '2017-12-27 15:42:49');
INSERT INTO `blog_admire` VALUES ('3', '1', '2', '100', '2017-12-27 16:49:33');
INSERT INTO `blog_admire` VALUES ('4', '3', '1', '12', '2017-12-27 17:28:11');
INSERT INTO `blog_admire` VALUES ('5', '2', '1', '13', '2017-12-28 15:14:54');

-- ----------------------------
-- Table structure for `blog_category`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES ('1', '1', null, '编程语言', 'java c c++ ', '2017-12-12 18:46:52');
INSERT INTO `blog_category` VALUES ('2', '1', null, '网络', 'TCP/IP，UDP，4-7', '2017-12-12 18:47:25');
INSERT INTO `blog_category` VALUES ('3', '2', null, '编程', 'coding', '2017-12-12 19:24:36');
INSERT INTO `blog_category` VALUES ('4', '2', null, 'AI', 'alpha Go', '2017-12-12 19:25:04');
INSERT INTO `blog_category` VALUES ('5', '1', null, 'VR', 'victurl', '2017-12-14 02:43:40');

-- ----------------------------
-- Table structure for `blog_collect`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_collect
-- ----------------------------
INSERT INTO `blog_collect` VALUES ('7', '1', '3', null, '2018-01-09 15:00:35', '0');
INSERT INTO `blog_collect` VALUES ('14', '2', '4', '博文写的很棒', '2018-01-09 15:09:43', '0');
INSERT INTO `blog_collect` VALUES ('15', '3', '2', null, '2018-01-09 15:10:18', '0');
INSERT INTO `blog_collect` VALUES ('16', '3', '3', null, '2018-01-09 15:10:19', '0');
INSERT INTO `blog_collect` VALUES ('17', '3', '4', null, '2018-01-09 15:10:20', '0');
INSERT INTO `blog_collect` VALUES ('19', '4', '2', null, '2018-01-09 15:10:31', '0');
INSERT INTO `blog_collect` VALUES ('20', '4', '1', null, '2018-01-09 15:10:32', '0');
INSERT INTO `blog_collect` VALUES ('21', '14', '2', null, '2018-01-09 15:10:42', '0');
INSERT INTO `blog_collect` VALUES ('22', '14', '3', null, '2018-01-09 15:10:44', '0');
INSERT INTO `blog_collect` VALUES ('23', '14', '4', null, '2018-01-09 15:10:45', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES ('1', '2', '2', '1', '文章写得不错', '2017-12-25 13:48:13', '1');
INSERT INTO `blog_comment` VALUES ('2', '2', '3', '1', '真的不错', '2017-12-25 13:49:18', '1');
INSERT INTO `blog_comment` VALUES ('3', '10', '1', '1', '内容', '2017-12-25 14:44:40', '1');
INSERT INTO `blog_comment` VALUES ('4', '3', '3', '2', '博主3评论博主2对博主1写的id为3的博文', '2017-12-27 11:09:08', '0');
INSERT INTO `blog_comment` VALUES ('5', '3', '2', '1', '又一次评论', '2017-12-27 11:12:20', '0');
INSERT INTO `blog_comment` VALUES ('6', '2', '1', '3', '评论', '2017-12-27 17:19:07', '1');
INSERT INTO `blog_comment` VALUES ('7', '2', '1', '2', 'nullddsaa', '2017-12-28 15:10:25', '1');

-- ----------------------------
-- Table structure for `blog_complain`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_complain
-- ----------------------------
INSERT INTO `blog_complain` VALUES ('1', '1', '4', '投诉内容', '2017-12-27 17:39:10');
INSERT INTO `blog_complain` VALUES ('2', '1', '2', 'aaaaa', '2017-12-28 15:19:51');

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
  UNIQUE KEY `blogger_id_2` (`blogger_id`,`title`),
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
INSERT INTO `blog_label` VALUES ('5', '1', 'MPI', '2017-12-12 18:42:45');

-- ----------------------------
-- Table structure for `blog_like`
-- ----------------------------
DROP TABLE IF EXISTS `blog_like`;
CREATE TABLE `blog_like` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '博文喜欢表id',
  `blog_id` int(10) unsigned NOT NULL COMMENT '被喜欢的文章',
  `liker_id` int(10) unsigned DEFAULT '0' COMMENT '仰慕者（给出like的人）id，未注册读者用0表示',
  `like_date` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admirer_id` (`liker_id`,`blog_id`),
  KEY `blog_like_ibfk_1` (`blog_id`),
  CONSTRAINT `blog_like_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_like
-- ----------------------------

-- ----------------------------
-- Table structure for `blog_statistics`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_statistics
-- ----------------------------
INSERT INTO `blog_statistics` VALUES ('1', '1', '0', '1', '0', '1', '0', '0', '2', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('2', '2', '2', '10', '0', '102', '1', '1', '1', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('3', '3', '2', '23', '0', '76', '0', '2', '1', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('4', '4', '0', '0', '0', '3', '1', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('5', '6', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('6', '9', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('7', '10', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('8', '11', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('9', '12', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('10', '13', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('11', '14', '0', '0', '0', '3', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('12', '15', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('13', '16', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('14', '17', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('15', '18', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('16', '19', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
INSERT INTO `blog_statistics` VALUES ('17', '20', '0', '0', '0', '0', '0', '0', '0', '0', '2018-01-09');
