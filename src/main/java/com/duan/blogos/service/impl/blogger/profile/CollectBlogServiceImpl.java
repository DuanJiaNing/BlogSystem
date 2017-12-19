package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.dto.blogger.CollectBlogListItemDTO;
import com.duan.blogos.entity.blog.BlogCollect;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.profile.CollectBlogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("collectBlogService")
public class CollectBlogServiceImpl implements CollectBlogService {
    @Override
    public ResultBean<List<CollectBlogListItemDTO>> listCollectBlog(int bloggerId, int categoryId, int offset, int rows, BlogSortRule sortRule) {
        return null;
    }

    @Override
    public ResultBean<Integer> insertCollectBlog(int bloggerId, int blogId, int categoryId, int authorId, String reason) {
        return null;
    }

    @Override
    public BlogCollect deleteCollectBlog(int blogId) {
        return null;
    }

    @Override
    public boolean updateCollectBlogCategory(int blogId, int newCategoryId) {
        return false;
    }
}
