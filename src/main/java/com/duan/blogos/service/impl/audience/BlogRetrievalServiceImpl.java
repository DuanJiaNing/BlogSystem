package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("blogRetrievalService")
public class BlogRetrievalServiceImpl implements BlogRetrievalService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterAll(int[] categoryIds, int[] labelIds, String keyWord, int bloggerId,
                                                           int offset, int rows, BlogSortRule sortRule) {

        return null;
    }
}
