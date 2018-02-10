package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blog.*;
import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/1/17.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerStatisticsServiceImpl implements BloggerStatisticsService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogLikeDao likeDao;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BlogLabelDao labelDao;

    @Autowired
    private BlogCollectDao collectDao;


    @Override
    public ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId) {

        // TODO
//        int blogCount = blogDao.countBlogByBloggerId(bloggerId);


        return null;
    }

}
