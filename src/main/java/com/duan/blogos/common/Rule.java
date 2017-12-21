package com.duan.blogos.common;

/**
 * Created on 2017/12/16.
 * 排序依据
 *
 * @author DuanJiaNing
 */
public enum Rule {


    /**
     * 评论次数
     */
    COMMENT_COUNT("comment_count"),

    /**
     * 博文浏览次数
     */
    VIEW_COUNT("view_count"),

    /**
     * 博主回复该博文次数
     */
    REPLY_COMMENT_COUNT("reply_comment_count"),

    /**
     * 博文被收藏次数
     */
    COLLECT_COUNT("collect_count"),

    /**
     * 博文举报次数
     */
    COMPLAIN_COUNT("complain_count"),

    /**
     * 博文被分享次数
     */
    SHARE_COUNT("share_count"),

    /**
     * 赞赏次数
     */
    ADMIRE_COUNT("admire_count"),

    /**
     * 喜欢次数
     */
    LIKE_COUNT("like_count"),

    /**
     * 字数
     */
    WORD_COUNT("word_count"),

    /**
     * 最初发布日期
     */
    RELEASE_DATE("release_date"),

    /**
     * 最近修改日期
     */
    NEAREST_MODIFY_DATE("nearest_modify_date");

    //与数据库对应字段名
    private final String field;

    Rule(String field) {
        this.field = field;
    }

    public String getFieldName() {
        return field;
    }

    /**
     * 检查是否存在与给定名字对应的枚举成员
     *
     * @param name 名字必须与某个枚举成员名完全相同
     * @return 存在返回 true，否则false
     */
    public static boolean contains(String name) {
        for (Rule rule : values()) {
            if (rule.name().equals(name)) return true;
        }

        return false;
    }
}
