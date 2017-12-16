package com.duan.blogos.enums;

/**
 * Created on 2017/12/12.
 * 博文状态，code的值对应数据库表blog的state、字段
 *
 * @author DuanJiaNing
 */
public enum BlogStatusEnum {

    /**
     * 博文是公开的，所以人都可以看到
     */
    PUBLIC(1),

    /**
     * 博文是私有的，只有博主才可以看到
     */
    PRIVATE(2),

    /**
     * 文章已经被删除了，根据回收策略进行删除
     */
    DELETED(-1),

    /**
     * 正在审核
     */
    VERIFY(0),

    /**
     * 显示在首页
     */
    HOMEPAGE(3);

    private final int code;

    BlogStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
