package com.duan.blogos.web.api;

import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.manager.BlogLuceneIndexManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created on 2018/1/10.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogLuceneIndexManager manager;

    @RequestMapping("/index/create")
    public void createIndex() throws IOException {
        for (Blog blog : blogDao.listAll()) {
            manager.add(blog);
        }
    }

}
