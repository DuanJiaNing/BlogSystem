

# --------------------------------------------------------UNIQUE KEY
# 同一博主不允许存在同名文章
ALTER TABLE blog ADD UNIQUE KEY (blogger_id,title);

# 同一博主不允许收藏同一篇文章两次
ALTER TABLE blog_collect ADD UNIQUE KEY (blog_id,blogger_id);

# 同一博主不能存在同名博文类别
ALTER TABLE blog_category ADD UNIQUE KEY (blogger_id,title);

# 同一博主不能存在同名博文标签
ALTER TABLE blog_label ADD UNIQUE KEY (blogger_id,title);

# 同一博主不能对同一篇文章喜欢两次
ALTER TABLE blog_like ADD UNIQUE KEY (admirer_id,blog_id);

# 用户名不能重复
ALTER TABLE blogger_account ADD UNIQUE KEY (username);

# 友情链接不能重复
ALTER TABLE blogger_link ADD UNIQUE KEY (blogger_id,url);

# 一个博主只能有一份资料
ALTER TABLE blogger_profile ADD UNIQUE KEY (blogger_id);

# 一篇博文只有一份统计记录
ALTER TABLE blog_statistics ADD UNIQUE KEY (blog_id);

# 一个博主只能拥有一张同名的照片
ALTER TABLE blogger_picture ADD UNIQUE KEY (blogger_id,title);

# 博主的手机号唯一
ALTER TABLE blogger_profile ADD UNIQUE KEY (phone);

  # ---------------------------------------------------------test
SELECT * FROM blog;
SELECT b.id FROM blogger_account b;
SELECT * FROM blog_category;
SELECT * from blog_label;
DESC blog;
SHOW CREATE TABLE blog;
SELECT *
FROM blogger_account;