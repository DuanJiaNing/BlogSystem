package com.duan.blogos.entity;

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
    VERIFY(0, "审核中");


    private final int code;
    private final String msg;

    BlogCommentStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
