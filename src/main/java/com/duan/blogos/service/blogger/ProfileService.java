package com.duan.blogos.service.blogger;

import com.duan.blogos.dto.blogger.BloggerCategoryDTO;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.dto.blogger.BloggerLinkDTO;
import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/14.
 * 博主个人资料服务
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
     * @return 查询结果
     */
    ResultBean<List<BloggerLinkDTO>> listBloggerLink(int bloggerId);

    /**
     * 获取博主创建的博文类别
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<List<BloggerCategoryDTO>> listBloggerCategory(int bloggerId);

    /**
     * 获取博主统计信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId);
}
