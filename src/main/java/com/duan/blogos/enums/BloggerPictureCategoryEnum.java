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
     */
    DEFAULT(0),

    /*--------------------------------------------------*/

    /**
     * 博主友情链接默认图片（唯一）
     */
    BLOGGER_DEFAULT_UNIQUE_LINK_ICON(3),

    /**
     * 博主默认头像图标（唯一）
     */
    BLOGGER_DEFAULT_UNIQUE_AVATAR(5),

    /**
     * 博主默认个人博文类别图标（唯一）
     */
    BLOG_DEFAULT_UNIQUE_CATEGORY_ICON(6),

    /**
     * 博主默认图片（请求指定图片而指定图片不存在时返回）（唯一）
     */
    BLOGGER_DEFAULT_UNIQUE_PICTURE(4);

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
     * 这些类别对应的图片是唯一的，即一个类别只能有一张图片
     */
    private static final EnumSet<BloggerPictureCategoryEnum> uniqueCate = EnumSet.of(
            BLOGGER_DEFAULT_UNIQUE_LINK_ICON,// 3
            BLOGGER_DEFAULT_UNIQUE_AVATAR,// 5
            BLOG_DEFAULT_UNIQUE_CATEGORY_ICON, // 6
            BLOGGER_DEFAULT_UNIQUE_PICTURE);// 4

    /**
     * 检查指定类别是否为图片唯一类别
     *
     * @param categoryCode 类别
     * @return 是为true
     */
    public static boolean isUniqueCategory(int categoryCode) {
        return uniqueCate.contains(valueOf(categoryCode));
    }
}
