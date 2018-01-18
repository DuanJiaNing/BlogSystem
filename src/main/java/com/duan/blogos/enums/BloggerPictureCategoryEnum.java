package com.duan.blogos.enums;

import java.util.EnumSet;

/**
 * Created on 2017/12/15.
 * 伯乐相册图片种类
 * <p>
 * 注意注释有“唯一”的图片类别，“唯一”指数据库中只会有一条该类别的记录，文件系统中对应的文件夹中只会有一张该类图片，这使
 * 修改图片类别时变得有些复杂。
 *
 * @author DuanJiaNing
 */
public enum BloggerPictureCategoryEnum {

    /**
     * 默认图片类别
     * 博主必须登录才能查看，此类别以外的所有类别在获取时都无需验证登录
     */
    PRIVATE(0),

    /**
     * 博文图片
     */
    BLOG(2),

    // 默认唯一：指图片管理员对于一些类别的图片他上传的图片将被系统做为默认图片使用，这些图片只能存在一张

    /*--------------------------------------------------*/

    /**
     * 博主友情链接图片 / 默认时唯一
     */
    BLOGGER_LINK_ICON(3),

    /**
     * 博主个人博文类别图标 / 默认时唯一
     */
    BLOG_CATEGORY_ICON(4),

    /*--------------------------------------------------*/

    /**
     * 博主头像图标 / 都唯一
     */
    BLOGGER_UNIQUE_AVATAR(5),

    /*--------------------------------------------------*/

    /**
     * 博主默认图片（请求指定图片而指定图片不存在时返回）/ 全局唯一，只能图片管理员管理
     */
    BLOGGER_UNIQUE_PICTURE(6);

    private int code;

    BloggerPictureCategoryEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BloggerPictureCategoryEnum valueOf(int code) {
        for (BloggerPictureCategoryEnum enuz : values()) {
            if (enuz.getCode() == code) return enuz;
        }

        return null;
    }

    /**
     * 这些类别对应的图片是图片管理员管理的，是唯一的，即一个类别只能有一张图片
     */
    private static final EnumSet<BloggerPictureCategoryEnum> uniqueCate = EnumSet.of(
            BLOGGER_LINK_ICON,// 3  默认友情链接
            BLOG_CATEGORY_ICON, // 4 默认类别图标
            BLOGGER_UNIQUE_AVATAR,// 5 默认博主头像
            BLOGGER_UNIQUE_PICTURE);// 6 默认图片

    /**
     * 这些类别对应的图片是博主唯一的
     */
    private static final EnumSet<BloggerPictureCategoryEnum> bloggerUniqueCate = EnumSet.of(
            BLOGGER_UNIQUE_AVATAR);// 5 博主头像

    /**
     * 这些类别对应的图片是博主唯一的
     */
    private static final EnumSet<BloggerPictureCategoryEnum> managerUniqueCate = EnumSet.of(
            BLOGGER_UNIQUE_PICTURE);// 6 默认图片

    /**
     * 检查指定类别是否为图片唯一类别（图片管理者）
     *
     * @param categoryCode 类别
     * @return 是为true
     */
    public static boolean isPictureManagerUniqueCategory(int categoryCode) {
        return uniqueCate.contains(valueOf(categoryCode));
    }

    /**
     * 检查指定类别对于博主而言是否应该为唯一类别
     *
     * @param categoryCode 类别
     * @return 是返回true
     */
    public static boolean isBloggerUniqueCategory(int categoryCode) {
        return bloggerUniqueCate.contains(valueOf(categoryCode));
    }

    /**
     * 检查图类别是否只有图片管理者才能操作
     *
     * @param categoryCode 类别id
     * @return 是返回true
     */
    public static boolean isPictureManageOnlyCategory(int categoryCode) {
        return managerUniqueCate.contains(valueOf(categoryCode));
    }


    private static final EnumSet<BloggerPictureCategoryEnum> uniqueCateWithoutAvatar = EnumSet.of(
            BLOGGER_LINK_ICON,// 3  默认友情链接
            BLOG_CATEGORY_ICON, // 4 默认类别图标
            BLOGGER_UNIQUE_PICTURE);// 6 默认图片
    // 用于图片管理员更新自己的图片
    public static boolean isPictureManagerUniqueCategoryWithoutAvatar(int categoryCode) {
        return uniqueCateWithoutAvatar.contains(valueOf(categoryCode));
    }

}
