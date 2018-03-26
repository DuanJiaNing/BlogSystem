<p align="center">
	<img width="130px" src="https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/logo.png"/>
	<br/><h1 align="center">BlogSystem<br/></h1><br/><br/>
</p>

## 博客系统

BLOG 是学习 JavaWeb 开发4个月以来的总结实践项目，使用 SSM（Spring、SpringMVC、MyBatis）框架，MVC 三层结构、Lucene全文检索引擎、Junit 4单元测试、logback日志框架、Druid数据库连接池、Shiro安全框架的一个博文系统；

#### 项目简介

网站面向有撰写博客习惯的用户，个人可注册成为网站用户（博主），在系统中创建自己的博文类别、标签，使用Markdown语法创作博文，创作好后将博文分类，贴上标签既可发布；普通用户通过用户名就能浏览和检索博主的公开博文，注册成为博主后可以评论、喜欢和收藏博文。

### API示例

#### 新增友情链接

- 接口地址：http://...XXX.../blogger/1/link（数字1为博主id）
- 返回格式：json
- 请求方式：post
- 前提条件：博主需登录
- 请求参数说明：
<table>
<tr>
<th>名称</th>
<th>类型</th>
<th>必填</th>
<th>说明</th>
<th>默认</th>
</tr>
<tr>
<td>iconId</td>
<td>int</td>
<td>否</td>
<td>链接使用的图标的图片id</td>
<td>系统默认图标</td>
</tr>
<tr>
<td>title</td>
<td>string</td>
<td>是</td>
<td>标题</td>
<td></td>
</tr>
<tr>
<td>url</td>
<td>string</td>
<td>是</td>
<td>url</td>
<td></td>
</tr>
<tr>
<td>bewrite</td>
<td>string</td>
<td>否</td>
<td>链接描述</td>
<td>无</td>
</tr>
</table>

- 请求示例：
http://...XXX.../blogger/1/link?iconId=2&title=Git&url=https://git&bewrite=描述

- 返回值：
操作成功返回新链接记录的id

- 后端接口
![](https://github.com/DuanJiaNing/Pictures/blob/master/BlogSystem/code-BloggerLinkController%23add.png?raw=true)

### 示例网站截图

#### 博主主页
![](https://raw.githubusercontent.com/DuanJiaNing/Pictures/master/BlogSystem/blog-main-page.png)

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


