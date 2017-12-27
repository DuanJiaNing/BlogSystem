package com.duan.blogos.enums;

/**
 * Created on 2017/12/14.
 * 博文评论状态，code的值对应数据库表blog_comment的state字段
 *
 * @author DuanJiaNing
 */
public enum BlogCommentStatusEnum {

    /**
     * 正在审核
     */
    BEING_AUDITED(0),

    /**
     * 审核通过
     */
    RIGHTFUL(1);

    private final int code;

    BlogCommentStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
