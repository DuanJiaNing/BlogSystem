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
    COMMENT_COUNT,

    /**
     * 博文浏览次数
     */
    VIEW_COUNT,

    /**
     * 博主回复该博文次数
     */
    REPLYCOMMENT_COUNT,

    /**
     * 博文被收藏次数
     */
    COLLECT_COUNT,

    /**
     * 博文举报次数
     */
    COMPLAIN_COUNT,

    /**
     * 博文被分享次数
     */
    SHARE_COUNT,

    /**
     * 赞赏次数
     */
    ADMIRE_COUNT,

    /**
     * 喜欢次数
     */
    LIKE_COUNT,

    /**
     * 字数
     */
    WORD_COUNT,

    /**
     * 最初发布日期
     */
    RELEASE_DATE,

    /**
     * 最近修改日期
     */
    NEAREST_MODIFY_DATE;


}
