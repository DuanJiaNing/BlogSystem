package com.duan.blogos.web.api.audience;

import com.duan.blogos.BaseTest;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.service.blogger.blog.BlogService;
import com.duan.blogos.util.DataProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/22.
 *
 * @author DuanJiaNing
 */
public class BlogControllerTest extends BaseTest {

    @Autowired
    private BlogService blogService;


    /**
     * 生成随机博文
     */
    @Test
    public void bloggerBlogList() {

        DataProvider provider = new DataProvider();
        int count = 0;
        for (int i = 0; i < count; i++) {

            blogService.insertBlog(1,
                    new int[]{1, 2}, // 1 2 5
                    new int[]{2}, // 1 2 3 5
                    BlogStatusEnum.PUBLIC,
                    provider.title(),
                    provider.content(),
                    provider.summary(),
                    provider.keyWords());

        }
    }
}