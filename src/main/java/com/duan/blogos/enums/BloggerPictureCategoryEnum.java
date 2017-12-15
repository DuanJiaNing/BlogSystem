package com.duan.blogos.enums;

/**
 * Created on 2017/12/15.
 * 伯乐相册图片种类
 *
 * @author DuanJiaNing
 */
public enum BloggerPictureCategoryEnum {

    /**
     * 博主个人博文类别图标
     */
    BLOG_CATEGORY_ICON(1),

    /**
     * 博主头像
     */
    BLOGGER_AVATAR(2);

    private int code;

    BloggerPictureCategoryEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
