package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogLabelDao;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.properties.DbProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.BlogFilterAbstract;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
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
    private BlogLabelDao labelDao;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Autowired
    private DbProperties dbProperties;

    @Override
    protected ResultBean<List<BlogListItemDTO>> constructResult(Map<Integer, Blog> blogHashMap,
                                                                List<BlogStatistics> statistics,
                                                                Map<Integer, int[]> blogIdMapCategoryIds,
                                                                Map<Integer, String> blogImgs) {

        // 重组结果
        List<BlogListItemDTO> result = new ArrayList<>();
        String ch = dbProperties.getStringFiledSplitCharacterForNumber();

        for (BlogStatistics ss : statistics) {
            Integer blogId = ss.getBlogId();
            Blog blog = blogHashMap.get(blogId);

            // category
            int[] cids = blogIdMapCategoryIds.get(blogId);
            List<BlogCategory> categories = null;
            if (!CollectionUtils.isEmpty(cids)) {
                categories = categoryDao.listCategoryById(cids);
            }

            // label
            int[] lids = StringUtils.intStringDistinctToArray(blog.getLabelIds(), ch);
            List<BlogLabel> labels = null;
            if (!CollectionUtils.isEmpty(lids)) {
                labels = labelDao.listLabelById(lids);
            }

            String blogImg = blogImgs.get(blogId);
            BlogListItemDTO dto = dataFillingManager.blogListItemToDTO(ss,
                    CollectionUtils.isEmpty(categories) ? null : categories.toArray(new BlogCategory[categories.size()]),
                    CollectionUtils.isEmpty(labels) ? null : labels.toArray(new BlogLabel[labels.size()]),
                    blog, blogImg);

            result.add(dto);
        }

        return new ResultBean<>(result);

    }
}
