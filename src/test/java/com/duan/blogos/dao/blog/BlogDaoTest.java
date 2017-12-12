package com.duan.blogos.dao.blog;

import com.duan.blogos.BaseTest;
import com.duan.blogos.entity.BlogStatus;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.utils.P;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Consumer;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
public class BlogDaoTest extends BaseTest {

    @Autowired
    private BlogDao blogDao;

    @Test
    public void queryBlog() {
        P.out(blogDao.queryBlog(1, BlogStatus.PRIVATE.code()));
    }

    @Test
    public void queryBlogWithLimit() {
        P.out(blogDao.queryBlogWithLimit(2, BlogStatus.PUBLIC.code(), 0, 1000));
    }

    @Test
    public void queryAll() {
        P.out(blogDao.queryAll());
    }


    @Test
    public void query() {
        P.out(blogDao.query(1));
    }

    @Test
    public void delete() {
//        P.out(blogDao.delete(1));
    }

    @Test
    public void update() {
        Blog blog = new Blog();
        blog.setId(1);
        blog.setSummary("相关搜索");
//        P.out(blogDao.update(blog));
    }

}