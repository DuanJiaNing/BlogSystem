package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blog.BlogCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/20.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogCategoryDao extends BaseDao<BlogCategory> {

    /**
     * 根据id查询类别
     *
     * @param ids 类别id
     * @return 查询结果
     */
    List<BlogCategory> listCategoryById(@Param("ids") int[] ids);

    /**
     * 查询博主创建的所有博文类别
     *
     * @param bloggerId 博主id
     * @param offset    结果集偏移
     * @param rows      行数
     * @return 查询结果
     */
    List<BlogCategory> listCategoryByBloggerId(@Param("bloggerId") int bloggerId,
                                               @Param("offset") int offset,
                                               @Param("rows") int rows);

    /**
     * 在限定博主的情况下获取指定id的博文类别
     *
     * @param bloggerId  博主id
     * @param categoryId 类别id
     * @return 查询结果
     */
    BlogCategory getCategory(@Param("bloggerId") int bloggerId,
                             @Param("categoryId") int categoryId);

    /**
     * 统计指定博主创建的类别数量
     *
     * @param bloggerId 博主id
     * @return 数量
     */
    Integer countByBloggerId(int bloggerId);
}
