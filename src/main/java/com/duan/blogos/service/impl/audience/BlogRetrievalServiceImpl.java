package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.DbPropertiesManager;
import com.duan.blogos.manager.comparator.BlogListItemComparatorFactory;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private BlogStatisticsDao statisticsDao;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private DbPropertiesManager dbPropertiesManager;

    @Autowired
    private DataFillingManager dataFillingManager;

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterAll(int[] categoryIds, int[] labelIds, String keyWord, int bloggerId,
                                                           int offset, int rows, BlogSortRule sortRule) {

        Predicate<Object> p = Objects::isNull;

        if (p.test(keyWord)) {
            //标签&类别检索
            return listFilterByLabelAndCategory(categoryIds, labelIds, bloggerId, offset, rows, sortRule);
        } else {
            // 有关键字时需要依赖lucene进行检索
            return filterByLucene(categoryIds, labelIds, bloggerId, offset, rows, sortRule);
        }

    }

    //使用lucene检索
    private ResultBean<List<BlogListItemDTO>> filterByLucene(int[] categoryIds, int[] labelIds, int bloggerId, int offset, int rows, BlogSortRule sortRule) {

        return null;
    }

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterByLabelAndCategory(int[] categoryIds, int[] labelIds,
                                                                          int bloggerId, int offset, int rows,
                                                                          BlogSortRule sortRule) {


        // 查询博主的所有标签和类别
        List<Blog> blogs = blogDao.listAllCategoryAndLabel(bloggerId, BlogStatusEnum.PUBLIC.getCode());

        // 筛选符合条件的博文的id
        Map<Integer, int[]> map = new HashMap<>(); // 方便后面复用categories的id数组
        String ch = dbPropertiesManager.getStringFiledSplitCharacterForNumber();

        if (categoryIds == null && labelIds == null) { // 两者都没限定
            for (Blog blog : blogs) {
                int[] categoriesIds = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), ch);
                map.put(blog.getId(), categoriesIds);
            }
        } else if (categoryIds != null && labelIds != null) { // 两者都限定
            for (Blog blog : blogs) {
                int accordCount = 0;

                int[] categoriesIds = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), ch);
                int[] labels = StringUtils.intStringDistinctToArray(blog.getLabelIds(), ch);
                if (categoriesIds == null || labels == null) continue;

                for (int categoryId : categoriesIds) {
                    if (CollectionUtils.intArrayContain(categoryIds, categoryId)) {
                        accordCount++;
                        break;
                    }
                }

                for (int labelId : labels) {
                    if (CollectionUtils.intArrayContain(labelIds, labelId)) {
                        accordCount++;
                        break;
                    }
                }

                if (accordCount == 2) {
                    map.put(blog.getId(), categoriesIds);
                }

            }
        } else if (categoryIds != null) { // 只限定了categoryIds
            for (Blog blog : blogs) {

                int[] categoriesIds = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), ch);
                //博文没有分类，直接检查下一篇博文
                if (categoriesIds == null) continue;
                for (int categoryId : categoriesIds) {
                    if (CollectionUtils.intArrayContain(categoryIds, categoryId)) {
                        map.put(blog.getId(), categoriesIds);
                        break;
                    }
                }
            }
        } else {// 只限定了label
            for (Blog blog : blogs) {

                int[] categoriesIds = StringUtils.intStringDistinctToArray(blog.getCategoryIds(), ch);
                int[] labels = StringUtils.intStringDistinctToArray(blog.getLabelIds(), ch);

                //博文没有标签，直接检查下一篇
                if (labels == null) continue;
                for (int labelId : labels) {
                    if (CollectionUtils.intArrayContain(labelIds, labelId)) {
                        map.put(blog.getId(), categoriesIds);
                        break;
                    }
                }
            }
        }

        Integer[] ids = map.keySet().toArray(new Integer[map.size()]);
        if (CollectionUtils.isEmpty(ids)) return null;

        // 封装返回结果
        List<Blog> resultBlogs = blogDao.listBlogByBlogIds(Arrays.asList(ids), BlogStatusEnum.PUBLIC.getCode(), offset, rows);
        List<BlogListItemDTO> result = new ArrayList<>();
        for (Blog blog : resultBlogs) {
            int blogId = blog.getId();
            BlogStatistics statistics = statisticsDao.getStatistics(blogId);
            List<BlogCategory> categories = categoryDao.listCategoryById(map.get(blogId));
            BlogListItemDTO dto = dataFillingManager.blogListItemToDTO(statistics, categories, blog);
            result.add(dto);
        }

        // 排序
        BlogListItemComparatorFactory factory = new BlogListItemComparatorFactory();
        result.sort(factory.get(sortRule.getRule(), sortRule.getOrder()));

        return new ResultBean<>(result);
    }

}
