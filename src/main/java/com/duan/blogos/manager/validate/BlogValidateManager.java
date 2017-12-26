package com.duan.blogos.manager.validate;

import com.duan.blogos.service.blogger.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2017/12/26.
 * 博客验证
 *
 * @author DuanJiaNing
 */
@Component
public class BlogValidateManager {

    @Autowired
    private BlogService blogService;

    /**
     * 检查博文是否存在
     *
     * @param blogId 博文id
     * @return 博文存在返回true
     */
    public boolean checkBlogExist(Integer blogId) {
        return blogId != null && blogService.getBlogForCheckExist(blogId);
    }

}
