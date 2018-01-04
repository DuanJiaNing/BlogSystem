package com.duan.blogos.exception;

/**
 * Created on 2018/1/4.
 * 没有权限
 *
 * @author DuanJiaNing
 */
public class UnauthorizedException extends BaseRuntimeException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
