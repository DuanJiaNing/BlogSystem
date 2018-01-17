package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/20.
 * 未知博文
 *
 * @author DuanJiaNing
 */
public class UnknownBlogException extends BaseRuntimeException {

    private static final int code = 5;

    public UnknownBlogException(String message) {
        super(message,code);
    }

    public UnknownBlogException() {
        super(code);
    }
}
