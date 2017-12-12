package com.duan.blogos.entity;

/**
 * Created on 2017/12/12.
 * 博文状态
 *
 * @author DuanJiaNing
 */
public enum BlogStatus {

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
    DELETED(-1);


    private final int code;

    BlogStatus(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
