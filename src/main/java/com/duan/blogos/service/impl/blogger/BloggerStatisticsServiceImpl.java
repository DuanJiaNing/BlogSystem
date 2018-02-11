package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blog.*;
import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Override
    public ResultBean<BloggerStatisticsDTO> getBloggerStatistics(int bloggerId) {

        int blogCount = blogDao.countBlogByBloggerId(bloggerId, BlogStatusEnum.PUBLIC.getCode());

        List<Blog> blogs = blogDao.listAllWordCountByBloggerId(bloggerId, BlogStatusEnum.PUBLIC.getCode());
        int wordCountSum = blogs.stream().mapToInt(Blog::getWordCount).sum();
        int likeCount = blogs.stream().mapToInt(Blog::getId).map(statisticsDao::getLikeCount).sum();

        int likeGiveCount = likeDao.countLikeByLikerId(bloggerId);

        int categoryCount = categoryDao.countByBloggerId(bloggerId);

        int labelCount = labelDao.countByBloggerId(bloggerId);

        int collectCount = collectDao.countByCollectorId(bloggerId);
        int collectedCount = blogs.stream().mapToInt(Blog::getId).map(statisticsDao::getCollectCount).sum();

        BloggerStatisticsDTO dto = dataFillingManager.bloggerStatisticToDTO(blogCount, wordCountSum, likeCount, likeGiveCount,
                categoryCount, labelCount, collectCount, collectedCount);

        return new ResultBean<>(dto);
    }

}
