package com.duan.blogos.service.blogger.blog;

import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.dto.blog.BlogStatisticsDTO;
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
     * 1 新增博客
     * 2 为博文生成一条统计信息记录
     * 3 解析博文中引用的本地图片（以使其useCount自增）
     * 4 lucene添加索引
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
     * 1 更新博文
     * 2 更新博文中引用的本地图片（取消引用的useCount--，新增的useCount++）
     * 3 更新lucene
     *
     * @param bloggerId     博主id
     * @param blogId        博文id
     * @param newCategories 新类别，不修改传null
     * @param newLabels     新标签，不修改传null
     * @param newStatus     新状态，不修改传null
     * @param newTitle      新标题，不修改传null
     * @param newContent    新内容，不修改传null
     * @param newSummary    新摘要，不修改传null
     * @param newKeyWords   新关键字，，不修改传null
     * @return 更新失败为false
     */
    boolean updateBlog(int bloggerId, int blogId, int[] newCategories, int[] newLabels, BlogStatusEnum newStatus,
                       String newTitle, String newContent, String newSummary, String[] newKeyWords);

    /**
     * 1 删除博文
     * 2 删除统计信息记录
     * 3 博文中引用的图片useCount--
     * 4 删除lucene索引
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 删除的记录
     */
    boolean deleteBlog(int bloggerId, int blogId);

    /**
     * 批量删除博文
     *
     * @param bloggerId 博主id
     * @param blogIds   博文id
     * @return 操作失败为false
     */
    boolean deleteBlogPatch(int bloggerId, int[] blogIds);

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

    /**
     * 检查博文是否存在
     *
     * @param blogId 博文id
     * @return 存在返回true，否则false
     */
    boolean getBlogForCheckExist(int blogId);

    /**
     * 获得指定博主的指定博文
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 查询结果
     */
    ResultBean<Blog> getBlog(int bloggerId, int blogId);

}
