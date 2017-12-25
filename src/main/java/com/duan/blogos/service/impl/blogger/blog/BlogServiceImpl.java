package com.duan.blogos.service.impl.blogger.blog;

import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.dto.blogger.BlogStatisticsDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.manager.DbPropertiesManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.BlogService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Autowired
    private DbPropertiesManager dbPropertiesManager;

    @Override
    public int insertBlog(int bloggerId, int[] categories, int[] labels,
                          BlogStatusEnum status, String title, String content,
                          String summary, String[] keyWords) {

        //插入数据到bolg表
        String ch = dbPropertiesManager.getStringFiledSplitCharacterForNumber();
        Blog blog = new Blog();
        blog.setBloggerId(bloggerId);
        blog.setCategoryIds(StringUtils.intArrayToString(categories, ch));
        blog.setLabelIds(StringUtils.intArrayToString(labels, ch));
        blog.setState(status.getCode());
        blog.setTitle(title);
        blog.setContent(content);
        blog.setSummary(summary);
        blog.setKeyWords(StringUtils.arrayToString(keyWords, ch));
        blog.setWordCount(content.length());

        blogDao.insert(blog);

        //插入数据到blog_statistics表（生成博文信息记录）
        int blogId = blogDao.getBlogIdByUniqueKey(bloggerId, title);
        BlogStatistics statistics = new BlogStatistics();
        statistics.setBlogId(blogId);
        statisticsDao.insert(statistics);

        return blogId;
    }

    @Override
    public boolean updateBlog(int blogId, int newBloggerId, int[] newCategories, int[] newLabels, BlogStatusEnum newStatus, String newTitle, String newContent, String newSummary, String[] newKeyWords) {
        return false;
    }

    @Override
    public boolean updateBlogCategory(int blogId, int[] newCategories) {
        return false;
    }

    @Override
    public boolean updateBlogLabel(int blogId, int[] newCategories) {
        return false;
    }

    @Override
    public boolean updateBlogState(int blogId, BlogStatusEnum newStatus) {
        return false;
    }

    @Override
    public boolean updateBlogTitle(int blogId, String newTitle) {
        return false;
    }

    @Override
    public boolean updateBlogSummary(int blogId, String newSummary) {
        return false;
    }

    @Override
    public boolean updateBlogKeyWords(int blogId, String[] newKeyWords) {
        return false;
    }

    @Override
    public Blog deleteBlog(int blogId) {
        return null;
    }

    @Override
    public boolean deleteBlogPatch(int[] blogIds) {
        return false;
    }

    @Override
    public ResultBean<BlogStatisticsDTO> getBlogStatistics(int blogId) {
        return null;
    }

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterByStatus(int bloggerId, BlogStatusEnum status, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }

    @Override
    public boolean getBlogForCheckExist(int blogId) {
        return !(blogDao.getBlogIdById(blogId) == null);
    }

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterAll(int[] categoryIds, int[] labelIds, String keyWord, int bloggerId, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }


    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterByLabelAndCategory(int[] categoryIds, int[] labelIds, int bloggerId, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }
}
