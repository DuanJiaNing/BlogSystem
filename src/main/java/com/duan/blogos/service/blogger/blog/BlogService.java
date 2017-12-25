package com.duan.blogos.service.blogger.blog;

import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.dto.blogger.BlogStatisticsDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.BlogFilter;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 博主对自己的博文管理服务
 *
 * @author DuanJiaNing
 */
public interface BlogService extends BlogFilter<ResultBean<List<BlogListItemDTO>>> {

    /**
     * 新增博客
     * 新增一篇博文后要为其生成一条对应的博文信息记录
     *
     * @param bloggerId  博主id
     * @param categories 类别
     * @param labels     标签
     * @param status     状态
     * @param title      标题
     * @param content    内容
     * @param summary    摘要
     * @param keyWords   关键字
     * @return 新纪录id
     */
    int insertBlog(int bloggerId, int[] categories, int[] labels, BlogStatusEnum status,
                   String title, String content, String summary, String[] keyWords);

    /**
     * 更新博文
     *
     * @param blogId        博文id
     * @param newBloggerId  博文所属博主，不修改传-1
     * @param newCategories 新类别，不修改传null
     * @param newLabels     新标签，不修改传null
     * @param newStatus     新状态，不修改传null
     * @param newTitle      新标题，不修改传null
     * @param newContent    新内容，不修改传null
     * @param newSummary    新摘要，不修改传null
     * @param newKeyWords   新关键字，，不修改传null
     * @return 更新失败为false
     */
    boolean updateBlog(int blogId, int newBloggerId, int[] newCategories, int[] newLabels, BlogStatusEnum newStatus,
                       String newTitle, String newContent, String newSummary, String[] newKeyWords);

    /**
     * 修改博文所属类别
     *
     * @param blogId        博文id
     * @param newCategories 类别
     * @return 更新失败为false
     */
    boolean updateBlogCategory(int blogId, int[] newCategories);

    /**
     * 修改博文标签
     *
     * @param blogId        博文id
     * @param newCategories 标签
     * @return 更新失败为false
     */
    boolean updateBlogLabel(int blogId, int[] newCategories);

    /**
     * 修改博文状态
     *
     * @param blogId    博文id
     * @param newStatus 新状态
     * @return 更新失败为false
     */
    boolean updateBlogState(int blogId, BlogStatusEnum newStatus);

    /**
     * 修改博文标题
     *
     * @param blogId   博文id
     * @param newTitle 新标题
     * @return 更新失败为false
     */
    boolean updateBlogTitle(int blogId, String newTitle);

    /**
     * 修改博文摘要
     *
     * @param blogId     博文id
     * @param newSummary 新摘要
     * @return 更新失败为false
     */
    boolean updateBlogSummary(int blogId, String newSummary);

    /**
     * 修改博文关键字
     *
     * @param blogId      博文id
     * @param newKeyWords 新关键字
     * @return 更新失败为false
     */
    boolean updateBlogKeyWords(int blogId, String[] newKeyWords);

    /**
     * 删除博文
     *
     * @param blogId 博文id
     * @return 删除的记录
     */
    Blog deleteBlog(int blogId);

    /**
     * 批量删除博文
     *
     * @param blogIds 博文id
     * @return 操作失败为false
     */
    boolean deleteBlogPatch(int[] blogIds);

    /**
     * 获取博文统计信息
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    ResultBean<BlogStatisticsDTO> getBlogStatistics(int blogId);

    /**
     * 按类别检索
     *
     * @param bloggerId 博主id
     * @param status    博文状态
     * @param offset    结果集起始位置
     * @param rows      行数
     * @param sortRule  排序规则，为null则不做约束
     * @return 查询结果
     */
    ResultBean<List<BlogListItemDTO>> listFilterByStatus(int bloggerId, BlogStatusEnum status,
                                                         int offset, int rows, BlogSortRule sortRule);
}
