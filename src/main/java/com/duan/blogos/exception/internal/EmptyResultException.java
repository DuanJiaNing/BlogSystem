package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/22.
 * 查询结果是空的
 *
 * @author DuanJiaNing
 */
public class EmptyResultException extends BaseRuntimeException {

    private static final int code = 14;

    public EmptyResultException() {
        super(code);
    }

    public EmptyResultException(String message) {
        super(message, code);
    }
}
