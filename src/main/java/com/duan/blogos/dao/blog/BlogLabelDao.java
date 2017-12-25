package com.duan.blogos.dao.blog;

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
public interface BlogLabelDao {

    /**
     * 根据id查询标签
     *
     * @param ids id
     * @return 查询结果
     */
    List<BlogLabel> listLabelById(@Param("ids") int[] ids);

}
