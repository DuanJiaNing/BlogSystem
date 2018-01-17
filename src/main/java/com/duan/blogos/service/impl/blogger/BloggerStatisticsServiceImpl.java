package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/1/17.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerStatisticsServiceImpl implements BloggerStatisticsService {
    @Override
    public ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId) {
        return null;
    }
}
