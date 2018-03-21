package com.duan.blogos.exception.api.blogger;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/20.
 * 未知博主
 *
 * @author DuanJiaNing
 */
public class UnknownBloggerException extends BaseRuntimeException {

    public static final int code = 6;

    public UnknownBloggerException(String message) {
        super(message,code);
    }

    public UnknownBloggerException() {
        super(code);
    }
}
