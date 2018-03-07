package com.duan.blogos.manager.validate;

import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogLabelDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.service.blogger.BloggerBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2017/12/26.
 * 博文相关信息验证
 *
 * @author DuanJiaNing
 */
@Component
public class BlogValidateManager {

    @Autowired
    private BloggerBlogService bloggerBlogService;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogLabelDao labelDao;

    @Autowired
    private BlogStatisticsDao statisticsDao;

    /**
     * 检查博文是否存在
     *
     * @param blogId 博文id
     * @return 博文存在返回true
     */
    public boolean checkBlogExist(int blogId) {
        return blogId > 0 && bloggerBlogService.getBlogForCheckExist(blogId);
    }

    /**
     * 检查标签是否存在
     *
     * @param labelId 标签id
     * @return 存在返回true
     */
    public boolean checkLabelsExist(int labelId) {
        return labelDao.getLabel(labelId) != null;
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
        return blog != null && blog.getBloggerId().equals(bloggerId);
    }

    /**
     * 检验博文是否合法
     *
     * @param title    博文标题
     * @param content  博文内容
     * @param summary  摘要
     * @param keyWords 关键字
     * @return 合法返回true
     */
    public boolean verifyBlog(String title, String content, String contentMd, String summary, String keyWords) {
        //TODO 博文内容校验
        return true;
    }

    /**
     * 检查目标博文状态是否允许，一般用户只允许在“公开”，“私有”，“回收站”之间切换。
     *
     * @param status 状态值
     * @return 允许返回true
     */
    public boolean isBlogStatusAllow(int status) {
        List<BlogStatusEnum> list = Arrays.asList(BlogStatusEnum.PUBLIC, BlogStatusEnum.PRIVATE, BlogStatusEnum.DELETED);
        int contain = 0;
        for (BlogStatusEnum s : list) {
            if (s.getCode() == status) contain++;
        }

        return contain > 0;
    }

    /**
     * 检测博主是否有指定博文的统计信息记录
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 有返回true
     */
    public boolean isCreatorOfBlogStatistic(int bloggerId, int blogId) {

        if (!isCreatorOfBlog(bloggerId, blogId)) return false;

        BlogStatistics statistics = statisticsDao.getStatistics(blogId);
        if (statistics == null) return false;

        return true;
    }

    /**
     * 检查博文的统计信息是否存在
     *
     * @param blogId 博文id
     * @return 存在返回true
     */
    public boolean checkBlogStatisticExist(int blogId) {
        Integer count = statisticsDao.getViewCount(blogId);
        if (count == null) return false;

        return true;
    }

}
