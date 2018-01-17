package com.duan.blogos.service.impl.blog;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogLabelDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.dto.blog.BlogStatisticsCountDTO;
import com.duan.blogos.dto.blog.BlogStatisticsDTO;
import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.DbPropertiesManager;
import com.duan.blogos.manager.WebsitePropertiesManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blog.BlogStatisticsService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class BlogStatisticsServiceImpl implements BlogStatisticsService {

    @Autowired
    private DbPropertiesManager dbPropertiesManager;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BlogLabelDao labelDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Override
    public ResultBean<BlogStatisticsDTO> getBlogStatistics(int blogId) {

        Blog blog = blogDao.getBlogById(blogId);

        // 统计信息
        BlogStatistics statistics = statisticsDao.getStatistics(blogId);

        // 类别
        BlogCategory[] categories = null;
        String sn = dbPropertiesManager.getStringFiledSplitCharacterForNumber();
        int[] cids = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), sn);
        if (!CollectionUtils.isEmpty(cids)) {
            categories = categoryDao.listCategoryById(cids).toArray(new BlogCategory[cids.length]);
        }

        // 标签
        BlogLabel[] labels = null;
        int[] lids = StringUtils.intStringDistinctToArray(blog.getLabelIds(), sn);
        if (!CollectionUtils.isEmpty(lids)) {
            labels = labelDao.listLabelById(lids).toArray(new BlogLabel[lids.length]);
        }
        //喜欢该篇文章的人
        // 收藏了该篇文章的人
        // 赞赏了该篇文章的人


        return null;
    }

    @Override
    public ResultBean<BlogStatisticsCountDTO> getBlogStatisticsCount(int blogId) {
        BlogStatistics statistics = statisticsDao.getStatistics(blogId);
        if (statistics == null) return null;
        BlogStatisticsCountDTO dto = dataFillingManager.blogStatisticsToDTO(statistics);
        return new ResultBean<>(dto);
    }
}
