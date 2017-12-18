package com.duan.blogos.service.blogger.statistics;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.result.ResultBean;

/**
 * Created on 2017/12/18.
 *
 * @author DuanJiaNing
 */
public interface StatisticsService {

    /**
     * 获取博主统计信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId);
}

