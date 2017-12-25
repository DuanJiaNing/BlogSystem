package com.duan.blogos.exception;

/**
 * Created on 2017/12/20.
 * 未知博文
 *
 * @author DuanJiaNing
 */
public class UnknownBlogException extends BaseRuntimeException {

    public UnknownBlogException(String message) {
        super(message);
    }

    public UnknownBlogException() {
    }
}
