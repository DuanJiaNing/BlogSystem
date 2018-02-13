package com.duan.blogos.web.api.audience;

import com.duan.blogos.BaseTest;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.service.blogger.blog.BlogService;
import com.duan.blogos.util.ArrayUtils;
import com.duan.blogos.util.DataProvider;
import com.duan.blogos.util.StringUtils;
import com.duan.blogos.web.api.blogger.BloggerBlogController;
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
    private BloggerBlogController controller;

    /**
     * 生成随机博文
     */
    @Test
    public void bloggerBlogList() {

        DataProvider provider = new DataProvider();
        int count = 20;
        for (int i = 0; i < count; i++) {
            controller.add(null, 1,
                    "1,2",
                    "2",
                    provider.title(),
                    provider.content(),
                    provider.summary(),
                    StringUtils.arrayToString(provider.keyWords(), ","));

        }
    }
}