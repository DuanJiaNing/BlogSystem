package com.duan.blogos.exception.api.blog;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/20.
 * 未知博文
 *
 * @author DuanJiaNing
 */
public class UnknownBlogException extends BaseRuntimeException {

    public static final int code = 5;

    public UnknownBlogException(String message) {
        super(message,code);
    }

    public UnknownBlogException() {
        super(code);
    }
}
