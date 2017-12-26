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
     * 通过博主id和类别id计算类别个数，该方法主要为检查指定博主是否创建过指定类别
     *
     * @param bloggerId  博主id
     * @param categoryId 类别id
     * @return 数量，0或1
     */
    int countCategoryByBloggerIdAndCategoryId(@Param("bloggerId") int bloggerId,
                                              @Param("categoryId") int categoryId);
}
