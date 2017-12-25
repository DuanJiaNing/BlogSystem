package com.duan.blogos.exception;

/**
 * Created on 2017/12/20.
 * 未知博主
 *
 * @author DuanJiaNing
 */
public class UnknownBloggerException extends BaseRuntimeException {

    public UnknownBloggerException(String message) {
        super(message);
    }

    public UnknownBloggerException() {
    }
}
