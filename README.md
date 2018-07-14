<p align="center">
	<img width="130px" src="https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/logo.png"/>
	<br/><h1 align="center">BlogSystem<br/></h1><br/><br/>
</p>

## 博客系统

BLOG 是学习 JavaWeb 开发4个月以来的总结实践项目，使用 SSM（Spring、SpringMVC、MyBatis）框架，MVC 三层结构、Lucene全文检索引擎、Junit 4单元测试、logback日志框架、Druid数据库连接池、Shiro安全框架的一个博文系统；

在线查看：[sample](http://120.79.128.250:8080/)<br>

#### 项目简介

网站面向有撰写博客习惯的用户，个人可注册成为网站用户（博主），在系统中创建自己的博文类别、标签，使用Markdown语法创作博文，创作好后将博文分类，贴上标签既可发布；普通用户通过用户名就能浏览和检索博主的公开博文，注册成为博主后可以评论、喜欢和收藏博文。

### 示例网站截图

#### 博主主页
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-main-page.png)

#### 登录页面（首页）
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blogger-login-1.png)

#### 注册页面
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/register.png)

#### 博文编辑（创作）页面
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-edit.png)

#### 博文浏览页面
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-read.png)

#### 博主收藏/喜欢的博文页面
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-favourite.png)

#### 博主设置页面
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blogger-setting.png)

#### 博文统计数据页面
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-statistics.png)

#### 博文批量导入对话框
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-patch-import.png)

#### 下载所有博文对话框
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-patch-download.png)

#### 帮助与反馈页面
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/help-feedback.png)

#### 开发者入口

系统对后端的 api 接口大都整理了文档说明，在 src/doc/wiki 目录下，但后续变更没有及时同步文档，仅可作为参考，api 用法以
src/main/java/com.duan.blogos/web/api 下源码为准。

在本地搭建系统时，需要做些前置准备。部署启动后会进入登录页面，登录页面底部有 *开发者入口* 链接，可点击链接进入系统搭建引导页面，
或者直接访问 '/dev_help.jsp' 亦可进入。

### 版本更新

- 2.0.0 
2018/07/14 批量导入博文可以根据压缩文件目录名创建类别，并把里面的博文归到其类别下

License
============

    Copyright 2017 DuanJiaNing

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.


