package com.duan.blogos.service.blogger.profile;

import com.duan.blogos.dto.blogger.CollectBlogListItemDTO;
import com.duan.blogos.entity.blog.BlogCollect;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 博主收藏博文服务
 *
 * @author DuanJiaNing
 */
public interface CollectBlogService {

    /**
     * 获得博主收藏的博文清单
     *
     * @param bloggerId  博主id
     * @param categoryId 收藏到的类别
     * @param offset     结果集起始位置
     * @param rows       行数
     * @param sortRule   排序规则，为null则不做约束
     * @return 查询结果
     */
    ResultBean<List<CollectBlogListItemDTO>> listCollectBlog(int bloggerId, int categoryId,
                                                             int offset, int rows, BlogSortRule sortRule);

    /**
     * 新增收藏文章
     *
     * @param bloggerId  收藏者id
     * @param blogId     博文id
     * @param categoryId 收藏到自己的哪一个类别之下
     * @param authorId   作者id
     * @param reason     收藏理由
     * @return 执行结果，新增记录id
     */
    ResultBean<Integer> insertCollectBlog(int bloggerId, int blogId, int categoryId, int authorId, String reason);

    /**
     * 取消收藏
     *
     * @param blogId 博文id
     * @return 收藏记录
     */
    BlogCollect deleteCollectBlog(int blogId);

    /**
     * 更新收藏博文所在类别
     *
     * @param blogId        收藏博文id
     * @param newCategoryId 新的类别
     */
    void updateCollectBlogCategory(int blogId, int newCategoryId);

}
