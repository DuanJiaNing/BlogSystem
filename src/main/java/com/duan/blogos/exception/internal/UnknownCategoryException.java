package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/20.
 * 未知博文
 *
 * @author DuanJiaNing
 */
public class UnknownCategoryException extends BaseRuntimeException {

    private static final int code = 7;

    public UnknownCategoryException(String message) {
        super(message,code);
    }

    public UnknownCategoryException() {
        super(code);
    }
}
