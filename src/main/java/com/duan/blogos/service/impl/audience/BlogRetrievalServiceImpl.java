package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.BlogFilterAbstract;
import com.duan.blogos.service.audience.BlogRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/12/19.
 * 读者检索博文
 *
 * @author DuanJiaNing
 */
@Service
public class BlogRetrievalServiceImpl extends BlogFilterAbstract<ResultBean<List<BlogListItemDTO>>> implements
        BlogRetrievalService {

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Override
    protected ResultBean<List<BlogListItemDTO>> constructResult(Map<Integer, Blog> blogHashMap, List<BlogStatistics> statistics, Map<Integer, int[]> blogIdMapCategoryIds) {

        // 重组结果
        List<BlogListItemDTO> result = new ArrayList<>();
        for (BlogStatistics ss : statistics) {
            Integer blogId = ss.getBlogId();
            List<BlogCategory> categories = categoryDao.listCategoryById(blogIdMapCategoryIds.get(blogId));
            Blog blog = blogHashMap.get(blogId);
            BlogListItemDTO dto = dataFillingManager.blogListItemToDTO(ss, categories, blog);
            result.add(dto);
        }

        return new ResultBean<>(result);

    }
}
