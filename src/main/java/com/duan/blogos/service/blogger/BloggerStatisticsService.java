package com.duan.blogos.service.blogger;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.restful.ResultBean;

/**
 * Created on 2018/1/17.
 * 博主统计信息服务
 *
 * @author DuanJiaNing
 */
// UPDATE: 2018/2/3 更新 初始版本不予实现
public interface BloggerStatisticsService {

    /**
     * 获取博主统计信息
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId);

}
