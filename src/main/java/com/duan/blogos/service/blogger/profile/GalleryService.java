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
     */
    void updatePictureCategory(int pictureId, BloggerPictureCategoryEnum newCategory);

    /**
     * 获得图片
     *
     * @param pictureId 图片id
     * @return 查询结果
     */
    BloggerPicture getPicture(int pictureId);
}
