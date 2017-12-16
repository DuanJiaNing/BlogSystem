package com.duan.blogos.service.audience;

import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/14.
 * 博文检索服务
 *
 * @author DuanJiaNing
 */
public interface BlogRetrievalService {

    /**
     * 全限定检索
     *
     * @param categoryIds 限定在博主的哪些类别之下，不做限定时传null
     * @param labelIds    限定在博主的哪些标签之下，不做限定时传null
     * @param keyWord     关键字,不做限定时传null
     * @param bloggerId   博主id
     * @param offset      结果集起始位置
     * @param rows        行数
     * @param sortRule    排序规则，为null则不做约束
     * @return 查询结果
     */
    ResultBean<List<BlogListItemDTO>> listFilterAll(int[] categoryIds, int[] labelIds,
                                                    String keyWord, int bloggerId, int offset,
                                                    int rows, BlogSortRule sortRule);

    /**
     * 关键字检索
     *
     * @param keyWord   关键字
     * @param bloggerId 博主id
     * @param offset    结果集起始位置
     * @param rows      行数
     * @param sortRule  排序规则，为null则不做约束
     * @return 查询结果
     */
    ResultBean<List<BlogListItemDTO>> listFilterByKeyWord(String keyWord, int bloggerId, int offset,
                                                          int rows, BlogSortRule sortRule);

    /**
     * 类别检索
     *
     * @param categoryIds 限定在博主的哪些类别之下
     * @param bloggerId   博主id
     * @param offset      结果集起始位置
     * @param rows        行数
     * @param sortRule    排序规则，为null则不做约束
     * @return 查询结果
     */
    ResultBean<List<BlogListItemDTO>> listFilterByCategory(int[] categoryIds, int bloggerId, int offset,
                                                           int rows, BlogSortRule sortRule);

    /**
     * 标签检索
     *
     * @param labelIds  限定在博主的哪些标签之下
     * @param bloggerId 博主id
     * @param offset    结果集起始位置
     * @param rows      行数
     * @param sortRule  排序规则，为null则不做约束
     * @return 查询结果
     */
    ResultBean<List<BlogListItemDTO>> listFilterByLabel(int[] labelIds, int bloggerId, int offset,
                                                        int rows, BlogSortRule sortRule);
}
