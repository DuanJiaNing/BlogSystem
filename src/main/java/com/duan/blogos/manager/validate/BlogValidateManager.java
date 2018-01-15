package com.duan.blogos.manager.validate;

import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.service.blogger.blog.BlogService;
import com.duan.blogos.util.StringUtils;
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

    @Autowired
    private BlogDao blogDao;

    /**
     * 检查博文是否存在
     *
     * @param blogId 博文id
     * @return 博文存在返回true
     */
    public boolean checkBlogExist(Integer blogId) {
        return blogId != null && blogId > 0 && blogService.getBlogForCheckExist(blogId);
    }

    /**
     * 检查博主是否为当前博文的创作者
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 是返回true
     */
    public boolean isCreatorOfBlog(int bloggerId, int blogId) {
        Blog blog = blogDao.getBlogById(blogId);
        return blog != null && blog.getBloggerId() == bloggerId;
    }

    /**
     * 检验博文是否合法
     *
     * @param title   博文标题
     * @param content 博文内容
     * @return 合法返回true
     */
    public boolean verifyBlog(String title, String content) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) return false;

        //TODO 博文内容校验
        return true;
    }
}
