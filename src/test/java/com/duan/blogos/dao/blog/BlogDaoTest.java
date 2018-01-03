package com.duan.blogos.dao.blog;

import com.duan.blogos.BaseTest;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.utils.P;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        P.out(blogDao.listBlog(1, BlogStatusEnum.PRIVATE.getCode()));
    }

    @Test
    public void queryBlogWithLimit() {
        P.out(blogDao.listBlogWithLimit(2, BlogStatusEnum.PUBLIC.getCode(), 0, 1000));
    }

    @Test
    public void queryAll() {
        P.out(blogDao.listAll());
    }


    @Test
    public void query() {
//        P.out(blogDao.listQuery(1));
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
    }

    @Test
    public void insert() {
        String content = "Statement 是 Java 执行数据库操作的一个重要接口，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。Statement对象，用于执行不带参数的简单SQL语句。\n" +
                "用于执行静态 SQL 语句并返回它所生成结果的对象。\n" +
                "在默认情况下，同一时间每个 Statement 对象在只能打开一个 ResultSet 对象。因此，如果读取一个 ResultSet 对象与读取另一个交叉，则这两个对象必须是由不同的 Statement 对象生成的。如果存在某个语句的打开的当前 ResultSet 对象，则 Statement 接口中的所有执行方法都会隐式关闭它。\n" +
                "Statement 对象用于将 SQL 语句发送到数据库中。实际上有三种 Statement 对象，它们都作为在给定连接上执行 SQL 语句的包容器：Statement、PreparedStatement（它从 Statement 继承而来）和 CallableStatement（它从 PreparedStatement 继承而来）。它们都专用于发送特定类型的 SQL 语句： Statement 对象用于执行不带参数的简单 SQL 语句；PreparedStatement 对象用于执行带或不带 IN 参数的预编译 SQL 语句；CallableStatement 对象用于执行对数据库已存在的存储过程的调用。\n" +
                "Statement 接口提供了执行语句和获取结果的基本方法。PreparedStatement 接口添加了处理 IN 参数的方法；而 CallableStatement 添加了处理 OUT 参数的方法。";
        Blog blog = new Blog();
        blog.setBloggerId(3);
        blog.setCategoryIds("5");
        blog.setLabelIds("5");
        blog.setTitle("Statement-1");
        blog.setContent(content);
        blog.setSummary("用于执行静态 SQL");
        blog.setWordCount(content.length());
        blog.setKeyWords("Statement SQL");
//        blog.setState();

        P.out(blogDao.insert(blog));
    }

}