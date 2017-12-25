package com.duan.blogos.dao.blogger;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blogger.BloggerPicture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017/12/25.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BloggerPictureDao extends BaseDao<BloggerPicture> {

    /**
     * 通过id查询图片
     *
     * @param id 图片id
     * @return 查询结果
     */
    BloggerPicture getPictureById(int id);

    /**
     * 根据图片类别获得博主图片
     *
     * @param bloggerId 博主id
     * @param category  类别
     * @return 查询结果
     */
    BloggerPicture getPictureByCategory(@Param("bloggerId") int bloggerId, @Param("category") int category);
}
