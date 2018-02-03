package com.duan.blogos.service.blog;

import com.duan.blogos.dto.blog.BlogStatisticsCountDTO;
import com.duan.blogos.dto.blog.BlogStatisticsDTO;
import com.duan.blogos.restful.ResultBean;

/**
 * Created on 2017/12/18.
 *
 * @author DuanJiaNing
 */
public interface BlogStatisticsService {

    /**
     * 获取博文统计信息
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<BlogStatisticsDTO> getBlogStatistics(int blogId);

    /**
     * 获取博文统计信息（只获取数据量）
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<BlogStatisticsCountDTO> getBlogStatisticsCount(int blogId);
}

