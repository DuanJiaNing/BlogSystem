package com.duan.blogos.service.blogger.profile;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;

/**
 * Created on 2017/12/18.
 * 博主图片服务
 *
 * @author DuanJiaNing
 */
public interface GalleryService {

    /**
     * 新增图片
     *
     * @param bloggerId 博主id
     * @param desc      图片描述
     * @param category  图片所属类别
     * @param title     名字
     * @return 新纪录id
     */
    int insertPicture(int bloggerId, String desc, BloggerPictureCategoryEnum category, String title);

    /**
     * 删除图片记录
     *
     * @param pictureId    图片id
     * @param deleteOnDisk 是否同时从存储设备中删除
     * @return 被删除的图片记录
     */
    BloggerPicture deletePicture(int pictureId, boolean deleteOnDisk);

    /**
     * 更新图片种类
     *
     * @param pictureId   图片id
     * @param newCategory 新的类别
     * @return 更新失败为false
     */
    boolean updatePictureCategory(int pictureId, BloggerPictureCategoryEnum newCategory);

    /**
     * 获得图片
     *
     * @param pictureId 图片id
     * @return 查询结果
     */
    BloggerPicture getPicture(int pictureId);

    /**
     * 获得图片
     *
     * @param pictureId 图片id
     * @param bloggerId 博主id
     * @return 查询结果
     */
    BloggerPicture getPicture(int pictureId, int bloggerId);

    /**
     * 检查图片是否存在
     *
     * @param pictureId 图片id
     * @return 存在返回true
     */
    boolean getPictureForCheckExist(int pictureId);

    /**
     * 根据类别获得图片，这些图片是应用默认的图片，一个类别只应该有一张默认图片
     *
     * @param category 类别
     * @return 查询结果
     */
    BloggerPicture getPictureByPropertiesCategory(int category);
}
