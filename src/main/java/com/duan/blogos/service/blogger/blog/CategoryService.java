package com.duan.blogos.service.blogger.blog;

import com.duan.blogos.dto.blogger.BloggerCategoryDTO;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 博主博文类别服务
 *
 * @author DuanJiaNing
 */
public interface CategoryService {

    /**
     * 获取博主创建的博文类别，按时间倒序排序
     *
     * @param bloggerId 博主id
     * @param offset    结果集起始位置
     * @param rows      行数
     * @return 查询结果
     */
    ResultBean<List<BloggerCategoryDTO>> listBlogCategory(int bloggerId, int offset, int rows);

    /**
     * 修改类别
     *
     * @param categoryId   类别id
     * @param newBloggerId 新的类别所属博主，不修改传 -1
     * @param newTitle     新标题，不修改传 null
     * @param newDesc      新描述，不修改传 null
     * @return 更新失败为false
     */
    boolean updateBlogCategory(int categoryId, int newBloggerId, String newTitle, String newDesc);

    /**
     * 新增博文类别
     *
     * @param bloggerId 类别所属博主id
     * @param title     标题
     * @param desc      描述
     * @return 新纪录id
     */
    int insertBlogCategory(int bloggerId, String title, String desc);

    /**
     * 删除类别，同时删除类别下的所有博文
     *
     * @param bloggerId  博主id
     * @param categoryId 类别id
     */
    void deleteCategoryAndBlogsAsWell(int bloggerId, int categoryId);

    /**
     * 删除类别，同时移动类别下所有文章到新的类别中
     *
     * @param bloggerId     博主id
     * @param categoryId    要删除的类别
     * @param newBloggerId  新的类别创建者（博主）id
     * @param newCategoryId 新的类别（新类别创建者创建的类别）
     */
    void deleteCategoryAndMoveBlogsTo(int bloggerId, int categoryId, int newBloggerId, int newCategoryId);

    /**
     * 通过计算类别数量，来检查指定博主是否创建过指定类别
     *
     * @param bloggerId  博主id
     * @param categoryId 类别id
     * @return 创建过则结果不小于1，否则为0
     */
    int countCategoryForExistCheck(int bloggerId, int categoryId);
}
