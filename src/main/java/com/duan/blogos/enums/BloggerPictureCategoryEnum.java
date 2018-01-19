package com.duan.blogos.enums;

import java.util.EnumSet;

/**
 * Created on 2017/12/15.
 * 伯乐相册图片种类
 *
 * @author DuanJiaNing
 */
public enum BloggerPictureCategoryEnum {

    /**
     * 默认图片类别
     * 博主必须登录才能查看，此类别以外的所有类别在获取时都无需验证登录
     */
    PRIVATE(0),

    /*--------------------------------------------------*/

    /**
     * 公开图片，私有图片被设置为链接图片、类别图片、博文图片、头像后就成为公开图片
     */
    PUBLIC(1),

    // 指图片管理员对于一些类别的图片他上传的图片将被系统做为默认图片使用，这些图片只能存在一张
    /*--------------------------------------------------*/

    /**
     * 应用默认博主友情链接图片
     */
    DEFAULT_BLOGGER_LINK_ICON(11),

    /**
     * 应用默认博主博文类别图片
     */
    DEFAULT_BLOGGER_BLOG_CATEGORY_ICON(12),

    /**
     * 应用默认博主头像
     */
    DEFAULT_BLOGGER_AVATAR(13),

    /**
     * 应用默认博主博文图片
     */
    DEFAULT_BLOGGER_BLOG_PICTURE(14),

    /**
     * 应用全局默认图片
     */
    DEFAULT_PICTURE(15);

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
    private static final EnumSet<BloggerPictureCategoryEnum> defaultCate = EnumSet.of(
            DEFAULT_BLOGGER_LINK_ICON, // 11
            DEFAULT_BLOGGER_BLOG_CATEGORY_ICON, // 12
            DEFAULT_BLOGGER_AVATAR, // 13
            DEFAULT_BLOGGER_BLOG_PICTURE,// 14
            DEFAULT_PICTURE); // 15

    /**
     * 检查图类别是否为默认类别，只有图片管理者才能操作
     *
     * @param categoryCode 类别id
     * @return 是返回true
     */
    public static boolean isDefaultPictureCategory(int categoryCode) {
        return defaultCate.contains(valueOf(categoryCode));
    }

}
