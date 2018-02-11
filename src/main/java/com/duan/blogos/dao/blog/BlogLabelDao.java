package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blog.BlogLabel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/22.
 * 博文标签dao
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogLabelDao extends BaseDao<BlogLabel> {

    /**
     * 根据id查询标签
     *
     * @param ids id
     * @return 查询结果
     */
    List<BlogLabel> listLabelById(@Param("ids") int[] ids);

    /**
     * 根据标签id获得标签
     *
     * @param labelId 标签id
     * @return 查询结果
     */
    BlogLabel getLabel(int labelId);

    /**
     * 无限制的获取标签
     *
     * @param offset 结果集偏移
     * @param rows   行数
     * @return 查询结果
     */
    List<BlogLabel> listLabel(@Param("offset") int offset, @Param("rows") int rows);

    /**
     * 获取指定博主创建的所有标签
     *
     * @param bloggerId 博主id
     * @param offset    结果集偏移
     * @param rows      行数
     * @return 查询结果
     */
    List<BlogLabel> listLabelByBloggerId(@Param("bloggerId") int bloggerId,
                                         @Param("offset") int offset,
                                         @Param("rows") int rows);

    /**
     * 统计指定博主创建的标签数量
     *
     * @param bloggerId 博主id
     * @return 数量
     */
    Integer countByBloggerId(int bloggerId);
}
