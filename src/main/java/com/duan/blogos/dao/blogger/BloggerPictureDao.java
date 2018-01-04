package com.duan.blogos.dao.blogger;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
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

    /**
     * 根据类别获得图片，这些图片是应用默认的图片，一个类别只应该有一张默认图片
     *
     * @param category 类别
     * @return 查询结果
     */
    BloggerPicture getPictureByPropertiesCategory(int category);

    /**
     * 根据唯一图片对应的类别来获取图片id
     *
     * @param uniqueCategory 唯一的类别，见{@link BloggerPictureCategoryEnum#uniqueCate}
     * @return id
     */
    int getPictureIdByUniqueCategory(int uniqueCategory);
}
