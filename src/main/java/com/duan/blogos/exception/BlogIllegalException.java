package com.duan.blogos.exception;

/**
 * Created on 2017/12/20.
 * 博文内容违规
 *
 * @author DuanJiaNing
 */
public class BlogIllegalException extends BaseRuntimeException {

    public BlogIllegalException(String message) {
        super(message);
    }

    public BlogIllegalException() {
    }
}
