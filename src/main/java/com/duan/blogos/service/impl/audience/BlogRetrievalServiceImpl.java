package com.duan.blogos.service.impl.audience;

import com.duan.blogos.dao.blog.BlogCategoryDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.dao.blog.BlogStatisticsDao;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.entity.blog.BlogCategory;
import com.duan.blogos.entity.blog.BlogStatistics;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.exception.UnknownException;
import com.duan.blogos.manager.BlogLuceneIndexManager;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.manager.DbPropertiesManager;
import com.duan.blogos.manager.comparator.BlogListItemComparatorFactory;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
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

    @Autowired
    private BlogLuceneIndexManager luceneIndexManager;

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterAll(int[] categoryIds, int[] labelIds, String keyWord,
                                                           int bloggerId, int offset, int rows, BlogSortRule sortRule,
                                                           BlogStatusEnum status) {

        if (StringUtils.isEmpty(keyWord)) {
            //标签&类别检索
            return listFilterByLabelAndCategory(categoryIds, labelIds, bloggerId, offset, rows, sortRule, status);
        } else {
            // 有关键字时需要依赖lucene进行检索
            // UPDATE: 2018/1/10 搜索准确度比较低
            return filterByLucene(keyWord, categoryIds, labelIds, bloggerId, offset, rows, sortRule, status);
        }

    }

    //使用lucene检索
    private ResultBean<List<BlogListItemDTO>> filterByLucene(String keyWord, int[] categoryIds, int[] labelIds,
                                                             int bloggerId, int offset, int rows, BlogSortRule sortRule,
                                                             BlogStatusEnum status) {

        // ------------------------关键字筛选
        int[] ids = null;
        try {
            // 搜索结果无法使用类似于sql limit的方式分页，这里一次性将所有结果查询出，后续考虑使用缓存实现分页
            ids = luceneIndexManager.search(keyWord, 10000);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new UnknownException("lucene parse exception", e);
        }
        //关键字为首要条件
        if (CollectionUtils.isEmpty(ids)) return null;

        // 关键字检索得到的博文集合
        List<Integer> filterByLuceneIds = new ArrayList<>();
        //取最前面的rows条结果
        int row = Math.min(rows, ids.length);
        for (int i = 0; i < row; i++) filterByLuceneIds.add(ids[i]);

        // ----------------------类别、标签筛选
        Map<Integer, int[]> map = getMapFilterByLabelAndCategory(bloggerId, categoryIds, labelIds, status);
        Integer[] mids = map.keySet().toArray(new Integer[map.size()]);
        // 类别、标签检索得到的博文集合
        List<Integer> filterByOtherIds = Arrays.asList(mids);

        //求两者交集得到最终结果集
        List<Integer> resultIds = filterByLuceneIds.stream().filter(filterByOtherIds::contains).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resultIds)) return null;

        //构造结果
        List<Blog> blogs = blogDao.listBlogByBlogIds(resultIds, status.getCode(), 0, resultIds.size());
        return new ResultBean<>(sortBlogListItemDTO(blogs, sortRule, map));
    }

    @Override
    public ResultBean<List<BlogListItemDTO>> listFilterByLabelAndCategory(int[] categoryIds, int[] labelIds,
                                                                          int bloggerId, int offset, int rows,
                                                                          BlogSortRule sortRule, BlogStatusEnum status) {

        Map<Integer, int[]> map = getMapFilterByLabelAndCategory(bloggerId, categoryIds, labelIds, status);

        Integer[] ids = map.keySet().toArray(new Integer[map.size()]);
        if (CollectionUtils.isEmpty(ids)) return null;

        // 排序并重组
        List<Blog> resultBlogs = blogDao.listBlogByBlogIds(Arrays.asList(ids), status.getCode(), offset, rows);
        return new ResultBean<>(sortBlogListItemDTO(resultBlogs, sortRule, map));
    }

    /*
     * 对筛选出的博文进行排序并重组结果集
     */
    private List<BlogListItemDTO> sortBlogListItemDTO(List<Blog> blogs, BlogSortRule sortRule, Map<Integer, int[]> map) {

        //用于排序
        List<BlogStatistics> temp = new ArrayList<>();

        //方便排序后的重组
        Map<Integer, Blog> blogHashMap = new HashMap<>();
        for (Blog blog : blogs) {
            int blogId = blog.getId();
            BlogStatistics statistics = statisticsDao.getStatistics(blogId);
            temp.add(statistics);
            blogHashMap.put(blogId, blog);
        }

        BlogListItemComparatorFactory factory = new BlogListItemComparatorFactory();
        temp.sort(factory.get(sortRule.getRule(), sortRule.getOrder()));

        // 重组结果
        List<BlogListItemDTO> result = new ArrayList<>();
        for (BlogStatistics statistics : temp) {
            Integer blogId = statistics.getBlogId();
            List<BlogCategory> categories = categoryDao.listCategoryById(map.get(blogId));
            Blog blog = blogHashMap.get(blogId);
            BlogListItemDTO dto = dataFillingManager.blogListItemToDTO(statistics, categories, blog);
            result.add(dto);
        }

        return result;
    }

    /**
     * 通过类别和标签筛选出指定博主的符合条件的博文id及对应的类别id数组
     * 类别和标签没有限制时返回指定博主的所有博文id集
     *
     * @param bloggerId   博主id
     * @param categoryIds 类别id数组
     * @param labelIds    标签id数组
     * @param status      博文状态
     */
    private Map<Integer, int[]> getMapFilterByLabelAndCategory(int bloggerId, int[] categoryIds, int[] labelIds,
                                                               BlogStatusEnum status) {

        // 查询博主的所有标签和类别
        List<Blog> blogs = blogDao.listAllCategoryAndLabel(bloggerId, status.getCode());

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

        return map;
    }
}
