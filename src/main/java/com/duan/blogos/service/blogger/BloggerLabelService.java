package com.duan.blogos.service.blogger;

import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.restful.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 标签服务
 *
 * @author DuanJiaNing
 */
public interface BloggerLabelService {

    /**
     * 新增标签
     *
     * @param bloggerId 标签创建者（博主）id
     * @param title     标签名
     * @return 新纪录id
     */
    int insertLabel(int bloggerId, String title);

    /**
     * 修改标签
     *
     * @param labelId   标签id
     * @param bloggerId 标签所有者
     * @param newTitle  新标签名
     * @return 更新失败为false
     */
    boolean updateLabel(int labelId, int bloggerId, String newTitle);

    /**
     * 删除标签，只有标签是当前博主创建时才能删除
     *
     * @param bloggerId 博主id
     * @param labelId   标签id
     * @return 删除成功返回true
     */
    boolean deleteLabel(int bloggerId, int labelId);

    /**
     * 获得博主创建的所有标签
     *
     * @param offset 结果集偏移量
     * @param rows   行数
     * @return 查询结果
     */
    ResultBean<List<BlogLabel>> listLabel(int offset, int rows);

    /**
     * 获得指定标签
     *
     * @param labelId 标签id
     * @return 查询结果
     */
    BlogLabel getLabel(int labelId);

    /**
     * 获取指定博主创建的所有标签
     *
     * @param bloggerId 博主id
     * @param offset    结果集偏移量
     * @param rows      行数
     * @return 查询结果
     */
    ResultBean<List<BlogLabel>> listLabelByBlogger(int bloggerId, int offset, int rows);
}
