package com.duan.blogos.service.blogger;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.restful.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 博主图片服务。
 * <p>
 * 图片在设备上的保存结构：
 * 1 所有图片保存在应用指定的根目录下，每一个博主都有自己的文件夹，每个类别拥有一个单独的文件夹，图片存放在各自目录下对应
 * 的类别文件夹下。
 * 2 唯一类别对应的文件夹下只能存放全局唯一的一张图片，如应用默认的博主友情链接图标，博主头像等，见{@link BloggerPictureCategoryEnum}
 * 在对这些图片进行修改时（主要集中在类别修改），需要将对应目录下的文件进行相应移动。如：设置某图片为头像，则若原来设置
 * 了头像，那么对应头像目录下也就有图片，此时需要将原来的头像文件移动到默认图片文件夹下并修改数据库记录（类别和保存路径），
 * 再将新的头像移动到头像文件夹下，后修改对应图片的数据库信息（类别和保存路径）。
 *
 * @author DuanJiaNing
 */
public interface BloggerPictureService {

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
     * 向数据库中新增图片记录，同时将图片保存到设备
     *
     * @param bs        图片数据
     * @param bloggerId 博主id
     * @param name      图片名字
     * @param bewrite   描述
     * @param category  类别
     * @param title     标题
     * @return 新纪录id
     */
    int insertPicture(byte[] bs, int bloggerId, String name, String bewrite, BloggerPictureCategoryEnum category, String title);

    /**
     * 删除图片记录
     *
     * @param bloggerId    博主id
     * @param pictureId    图片id
     * @param deleteOnDisk 是否同时从存储设备中删除
     * @return 删除成功返回true
     */
    boolean deletePicture(int bloggerId, int pictureId, boolean deleteOnDisk);

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
     * 根据类别获得图片，这些图片是应用默认的图片，一个类别只应该有一张默认图片
     *
     * @param category 类别
     * @return 查询结果
     */
    BloggerPicture getDefaultPicture(BloggerPictureCategoryEnum category);

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
     *
     * @param pictureId 图片id
     * @param category  类别id
     * @param bewrite   描述
     * @param title     标题
     * @return
     */
    boolean updatePicture(int pictureId, BloggerPictureCategoryEnum category, String bewrite, String title);

    /**
     * 清理指定博主的博文图片，这里根据图片表的useCount的值检索BLOG_PICTURE类别的图片，如果其值为0，可以删除，该清理过程
     * 应该尽量的少的执行；在这些情况下可以进行清理：1 如果磁盘空间占用过多、2 BLOG_PICTURE类别图片数量达到上限；清理时
     * 按照一定的清理策略进行删除，LRU，FIFO等。
     * <p>
     * 此外，在每一次博文中引用到图片时将useCount自增，博文被删除时useCount自减。
     *
     * @param bloggerId 博主id
     */
    // UPDATE: 2018/2/3 更新 初始版本不予实现
    void cleanBlogPicture(int bloggerId);

}
