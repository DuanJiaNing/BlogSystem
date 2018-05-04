package com.duan.blogos.service.website;

import com.duan.blogos.dto.blogger.BloggerBriefDTO;

import java.util.List;

/**
 * Created on 2018/5/1.
 * 站点统计数据
 *
 * @author DuanJiaNing
 */
public interface WebSiteStatisticsService {

    /**
     * 获取活跃博主
     *
     * @param count 获取博主格式
     * @return 集合
     */
    List<BloggerBriefDTO> listActiveBlogger(int count);

}
