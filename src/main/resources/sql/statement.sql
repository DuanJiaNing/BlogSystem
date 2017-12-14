

# --------------------------------------------------------modify
# 同一博主不允许存在同名文章
ALTER TABLE blog ADD UNIQUE KEY (blogger_id,title);

# 同一博主不允许收藏同一篇文章两次
ALTER TABLE blog_collect ADD UNIQUE KEY (blog_id,blogger_id);



# ---------------------------------------------------------test
SELECT * FROM blog;
SELECT b.id FROM blogger_account b;
SELECT * FROM blog_category;
SELECT * from blog_label;
DESC blog;
SHOW CREATE TABLE blog;
