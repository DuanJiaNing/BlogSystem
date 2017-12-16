package com.duan.blogos.service.blogger;

import com.duan.blogos.dto.blogger.BloggerCategoryDTO;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.dto.blogger.BloggerLinkDTO;
import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/14.
 * 博主个人资料服务，该服务主要用户获得博主的个人信息，博文类别，标签等；为博主首页，博文浏览等页面提供数据
 * <p>
 * 1 获取博主信息（两种方式：1通过博主id 2通过博文id）
 * 2 获取友情链接
 * 3 获取博主创建的博文类别
 * 4 获取博主统计信息
 *
 * @author DuanJiaNing
 */
public interface ProfileService {

    /**
     * 获取博主信息
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<BloggerDTO> getBloggerByBlog(int blogId);

    /**
     * 获取博主信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BloggerDTO> getBlogger(int bloggerId);

    /**
     * 获取友情链接
     *
     * @param bloggerId 博主id
     * @param offset    结果集起始位置
     * @param rows      行数
     * @return 查询结果
     */
    ResultBean<List<BloggerLinkDTO>> listBloggerLink(int bloggerId, int offset, int rows);

    /**
     * 获取博主创建的博文类别
     *
     * @param bloggerId 博主id
     * @param offset    结果集起始位置
     * @param rows      行数
     * @return 查询结果
     */
    ResultBean<List<BloggerCategoryDTO>> listBloggerCategory(int bloggerId, int offset, int rows);

    /**
     * 获取博主统计信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId);
}
