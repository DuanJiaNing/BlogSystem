package com.duan.blogos.exception.internal;

/**
 * Created on 2018/1/4.
 * 没有权限
 *
 * @author DuanJiaNing
 */
public class UnauthorizedException extends BaseRuntimeException {

    private static final int code = 4;

    public UnauthorizedException() {
        super(code);
    }

    public UnauthorizedException(String message) {
        super(message, code);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause, code);
    }
}
