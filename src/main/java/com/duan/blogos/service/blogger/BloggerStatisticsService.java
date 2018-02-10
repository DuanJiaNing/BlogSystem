package com.duan.blogos.service.blogger;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.restful.ResultBean;

/**
 * Created on 2018/1/17.
 * 博主统计信息服务
 *
 * @author DuanJiaNing
 */
public interface BloggerStatisticsService {

    /**
     * 获取博主统计信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId);

}
