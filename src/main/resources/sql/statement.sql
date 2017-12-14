

# modify
ALTER TABLE blog ADD UNIQUE KEY (blogger_id,title);

# test
SELECT * FROM blog;
SELECT b.id FROM blogger_account b;
SELECT * FROM blog_category;
SELECT * from blog_label;
DESC blog;
SHOW CREATE TABLE blog;
