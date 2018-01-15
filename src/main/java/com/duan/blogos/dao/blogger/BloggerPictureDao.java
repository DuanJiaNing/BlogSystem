package com.duan.blogos.dao.blogger;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 根据类别获得图片，这些图片一个类别只应该有一张图片
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

    /**
     * 查询博主的所有图片
     *
     * @param bloggerId 博主id
     * @param offset    偏移位置
     * @param rows      行数
     * @return 查询结果
     */
    List<BloggerPicture> listPictureByBloggerId(@Param("bloggerId") int bloggerId,
                                                @Param("offset") int offset,
                                                @Param("rows") int rows);

    /**
     * 查询博主的指定类别图片
     *
     * @param bloggerId 博主id
     * @param category  类别id
     * @param offset    偏移位置
     * @param rows      行数
     * @return 查询结果
     */
    List<BloggerPicture> listPictureByBloggerAndCategory(@Param("bloggerId") int bloggerId,
                                                         @Param("category") int category,
                                                         @Param("offset") int offset,
                                                         @Param("rows") int rows);

    /**
     * 将图片被引用次数减一
     *
     * @param pictureId 图片id
     * @return 操作影响的行数
     */
    int updateUseCountPlus(int pictureId);

    /**
     * 将图片被引用次数加一
     *
     * @param pictureId 图片id
     * @return 操作影响的行数
     */
    int updateUseCountMinus(int pictureId);

    /**
     * 获得图片被引用次数
     *
     * @param pictureId 图片id
     * @return 操作影响的行数
     */
    int getUseCount(int pictureId);

}
