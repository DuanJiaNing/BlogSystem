package com.duan.blogos.exception.api.common;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/20.
 * 未知错误，一般为服务器错误，但无法归类的错误
 *
 * @author DuanJiaNing
 */
public class UnknownException extends BaseRuntimeException {

    public static final int code = 10;

    public UnknownException(String message) {
        super(message, code);
    }

    public UnknownException() {
        super(code);
    }

    public UnknownException(String message, Throwable e) {
        super(message, e, code);
    }
}
