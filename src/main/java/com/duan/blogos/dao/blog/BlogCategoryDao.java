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
}
