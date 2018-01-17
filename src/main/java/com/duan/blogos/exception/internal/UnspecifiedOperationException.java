package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/22.
 * 不支持的操作
 *
 * @author DuanJiaNing
 */
public class UnspecifiedOperationException extends BaseRuntimeException {

    private static final int code = 9;

    public UnspecifiedOperationException() {
        super(code);
    }

    public UnspecifiedOperationException(String message) {
        super(message, code);
    }
}
