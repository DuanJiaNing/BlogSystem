
## notice
- 记录服务层功能实现日志
- api请求数据合法性在controller层处理
- md编辑器：http://lab.lepture.com/editor/
- API的url编写参考文章：http://www.ruanyifeng.com/blog/2014/05/restful_api.html
- 各种检查在Controller层，业务由Service层实现
- 文件名统一添加前缀 "时间-" 以避免覆盖。ImageManager#saveImageToDisk
- 创作博文时，博文中若引用了自己的照片（通过上传图片，后选用）的方式，md时url要遵守ImageController的url格式
- src-java-service&web.api的分包原则为读者操作博文（audience），博文通用（blog），博主管理（blogger）
- 注册时除了生成account记录外还有生成profile记录
- 错误码最高22
- 在启用系统前，有默认图片上传权限的人需要上传各个类别的默认图片
- 对于唯一类别图片，图片管理员只能以更新（上传）的方式删除图片，因为这些图片必须时刻存在
- 部署时记得修改配置文件，部署后第一步是注册id为1的图片管理员，上传默认头像
- setting.jsp aboutMe 介绍中有回车啥的在 jsp 转换为 java 时会换行但没有字符串连接符，导致签到 js 变量定义出错。

## 待办
- controller层的一些操作在获取数据时不必检查博主是否登录，而在修改、删除操作时需要验证博主是否登录
- service层一些更新、删除操作，如果失败通过抛出异常的形式让事务回滚 --？ 有没有更好的方法
- 数据库中用户密码使用sha加密
- 将api需要登录才能操作的部分加上登录验证
- 一张图片被设置为类别图标、链接图标、头像、博文图片时该图片都无条件变为公开的
- review时考虑图片引用次数（useCount）的计算

## 难点<br>
- 数据库操作可以自动回滚，但磁盘操作无法自动回滚
com.duan.blogos.service.impl.blogger.GalleryServiceImpl#insertPicture
- 修改tomcat对post请求的长度限制，默认2M，maxPostSize=-1，tomcat 6及以下版本修改为0
- 数据库中的 blog表的 content 和 contentMd （博文内容html/md）字段用 16 进制数据保存，否则在URL数据传送时可能会出错。
用英文 "," 切割16进制字符。StringUtils#stringToUnicode方法