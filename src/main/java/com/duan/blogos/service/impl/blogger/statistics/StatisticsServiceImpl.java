package com.duan.blogos.service.impl.blogger.statistics;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.statistics.StatisticsService;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId) {
        return null;
    }
}
