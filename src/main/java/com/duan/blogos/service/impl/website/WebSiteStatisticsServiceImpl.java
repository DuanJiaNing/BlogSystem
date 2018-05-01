package com.duan.blogos.service.impl.website;

import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.manager.WebsiteManager;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import com.duan.blogos.service.website.WebSiteStatisticsService;
import com.duan.blogos.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public List<BloggerDTO> listActiveBlogger(int count) {

        int[] ids = websiteManager.getActiveBloggerIds(count);
        BloggerDTO[] dtos = statisticsService.listBloggerDTO(ids);
        if (CollectionUtils.isEmpty(dtos)) return null;

        return Arrays.asList(dtos);
    }

}
