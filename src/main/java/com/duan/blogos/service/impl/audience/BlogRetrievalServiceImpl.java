package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogRetrievalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("blogRetrievalService")
public class BlogRetrievalServiceImpl implements BlogRetrievalService {
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
