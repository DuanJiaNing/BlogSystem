package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.exception.BaseException;
import com.duan.blogos.manager.BlogSortRule;
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

        if (CollectionUtils.isEmpty(categoryIds)) categoryIds = new int[]{0};

        // 查询博主的所有标签和类别
        List<Blog> blogs = blogDao.listAllCategoryAndLabel(bloggerId, BlogStatusEnum.PUBLIC.getCode());

        // 找出符合条件的博文的id
        Map<Integer, int[]> map = new HashMap<>(); // 方便后面复用categories的id数组
        boolean findInCategory;// 如果类别筛选已经符合，则无需继续检查标签
        String ch = dbPropertiesManager.getStringFiledSplitCharacter();
        for (Blog blog : blogs) {
            findInCategory = false;

            int blogId = blog.getId();
            int[] categoriesIds = StringUtils.intStringToArray(blog.getCategoryIds(), ch);

            for (int categoryId : categoriesIds) {
                if (CollectionUtils.intArrayContain(categoryIds, categoryId)) {
                    map.put(blogId, categoriesIds);
                    findInCategory = true;
                    break;
                }
            }

            if (findInCategory) continue;
            if (labelIds == null) break;

            int[] labels = StringUtils.intStringToArray(blog.getLabelIds(), ch);
            if (labels == null) break;

            for (int labelId : labels) {
                if (CollectionUtils.intArrayContain(labelIds, labelId)) {
                    map.put(blogId, categoriesIds);
                    break;
                }
            }
        }

        // 查询目标结果集
        Integer[] ids = map.keySet().toArray(new Integer[map.size()]);
        if (CollectionUtils.isEmpty(ids)) return new ResultBean<>(new BaseException("未获取到数据"));

        List<Blog> resultBlogs = blogDao.listBlogByBlogIds(Arrays.asList(ids), BlogStatusEnum.PUBLIC.getCode(), offset, rows);
        List<BlogListItemDTO> result = new ArrayList<>();
        for (Blog blog : resultBlogs) {
            int blogId = blog.getId();
            BlogStatistics statistics = statisticsDao.getStatistics(blogId);
            List<BlogCategory> categories = categoryDao.listCategoryById(map.get(blogId));

            BlogListItemDTO item = new BlogListItemDTO();
            item.setCategories(categories.toArray(new BlogCategory[categories.size()]));
            item.setCollectCount(statistics.getCollectCount());
            item.setCommentCount(statistics.getCommentCount());
            item.setId(blog.getId());
            item.setLikeCount(statistics.getLikeCount());
            item.setReleaseDate(blog.getReleaseDate());
            item.setSummary(blog.getSummary());
            item.setTitle(blog.getTitle());
            item.setViewCount(statistics.getViewCount());

            result.add(item);
        }

        // 排序
        BlogListItemComparatorFactory factory = new BlogListItemComparatorFactory();
        result.sort(factory.get(sortRule.getRule(), sortRule.getOrder()));

        return new ResultBean<>(result);
    }

}
