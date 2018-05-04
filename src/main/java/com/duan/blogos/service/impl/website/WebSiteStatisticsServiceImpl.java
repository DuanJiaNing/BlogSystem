package com.duan.blogos.service.impl.website;

import com.duan.blogos.dto.blogger.BloggerBriefDTO;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.WebsiteManager;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import com.duan.blogos.service.website.WebSiteStatisticsService;
import com.duan.blogos.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/5/1.
 *
 * @author DuanJiaNing
 */
@Service
public class WebSiteStatisticsServiceImpl implements WebSiteStatisticsService {

    @Autowired
    private BloggerStatisticsService statisticsService;

    @Autowired
    private WebsiteManager websiteManager;

    @Autowired
    private DataFillingManager fillingManager;

    @Override
    public List<BloggerBriefDTO> listActiveBlogger(int count) {

        int[] ids = websiteManager.getActiveBloggerIds(count);
        BloggerDTO[] bloggerDTOS = statisticsService.listBloggerDTO(ids);
        if (CollectionUtils.isEmpty(bloggerDTOS)) return null;

        List<BloggerBriefDTO> dtos = new ArrayList<>();
        for (BloggerDTO blogger : bloggerDTOS) {
            ResultBean<BloggerStatisticsDTO> statistics = statisticsService.getBloggerStatistics(blogger.getId());
            final BloggerBriefDTO dto = fillingManager.bloggerTobrief(blogger, statistics.getData());
            dtos.add(dto);
        }

        return dtos;
    }

}
