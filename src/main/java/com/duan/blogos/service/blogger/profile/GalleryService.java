package com.duan.blogos.service.blogger.profile;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.result.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 博主图片服务
 *
 * @author DuanJiaNing
 */
public interface GalleryService {

    /**
     * 数据库中新增图片记录
     *
     * @param bloggerId 博主id
     * @param path      保存路径
     * @param bewrite   图片描述
     * @param category  图片所属类别
     * @param title     名字
     * @return 新纪录id
     */
    int insertPicture(int bloggerId, String path, String bewrite, BloggerPictureCategoryEnum category, String title);

    /**
     * 向数据库中新增图片记录，同时将图片保存到设备
     *
     * @param file      文件
     * @param bloggerId 博主id
     * @param bewrite   描述
     * @param category  类别
     * @param title     标题
     * @return 新纪录id
     */
    int insertPicture(MultipartFile file, int bloggerId, String bewrite, BloggerPictureCategoryEnum category, String title);

    /**
     * 删除图片记录
     *
     * @param pictureId    图片id
     * @param deleteOnDisk 是否同时从存储设备中删除
     * @return 删除成功返回true
     */
    boolean deletePicture(int pictureId, boolean deleteOnDisk);

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
    BloggerPicture getPictureByPropertiesCategory(BloggerPictureCategoryEnum category);

    /**
     * 更新唯一的图片（存在则更新，否则插入）
     *
     * @param bloggerId 博主id
     * @param bewrite   描述
     * @param path      保存路径
     * @param cate      类别
     * @param title     标题
     * @return 记录id
     */
    int updateUniquePicture(int bloggerId, String bewrite, String path, BloggerPictureCategoryEnum cate, String title);

    /**
     * 获得博主的多张图片
     *
     * @param bloggerId 博主id
     * @param category  类别id，不限制类别时传递-1
     * @param offset    结果集偏移量
     * @param rows      结果集行数
     * @return 查询结果
     */
    ResultBean<List<BloggerPicture>> listBloggerPicture(int bloggerId, BloggerPictureCategoryEnum category, int offset, int rows);

    /**
     * 更新图片信息，当修改了图片类别时需要同步更新设备上的图片保存位置
     * @param pictureId 图片id
     * @param category 类别id
     * @param bewrite 描述
     * @param title 标题
     * @return
     */
    boolean updatePicture(int pictureId, BloggerPictureCategoryEnum category, String bewrite, String title);
}
