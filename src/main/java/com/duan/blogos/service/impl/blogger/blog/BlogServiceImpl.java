package com.duan.blogos.service.impl.blogger.blog;

import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.dto.blogger.BlogStatisticsDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Override
    public int insertBlog(int bloggerId, int[] categories, int[] labels, BlogStatusEnum status, String title, String content, String summary, String[] keyWords) {
        return 0;
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
    public ResultBean<List<BlogListItemDTO>> listFilterAll(int[] categoryIds, int[] labelIds, String keyWord, int bloggerId, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterByKeyWord(String keyWord, int bloggerId, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterByCategory(int[] categoryIds, int bloggerId, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterByLabel(int[] labelIds, int bloggerId, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }
}
