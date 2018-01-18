
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
- 错误码最高17
- 在启用系统前，有默认图片上传权限的人需要上传各个类别的默认图片
- 对于唯一类别图片，图片管理员只能以更新（上传）的方式删除图片，因为这些图片必须时刻存在，头像除外

## 待办
- controller层的一些操作在获取数据时不必检查博主是否登录，而在修改、删除操作时需要验证博主是否登录
- service层一些更新、删除操作，如果失败通过抛出异常的形式让事务回滚 --？ 有没有更好的方法
- 数据库中用户密码使用sha加密
- 将api需要登录才能操作的部分加上登录验证
- 一张图片被设置为类别图标、链接图标、头像、博文图片时该图片都无条件变为公开的

## 难点<br>
- 数据库操作可以自动回滚，但磁盘操作无法自动回滚
com.duan.blogos.service.impl.blogger.profile.GalleryServiceImpl#insertPicture
