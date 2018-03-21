package com.duan.blogos.exception.api.authorize;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2018/1/4.
 * 未授权
 *
 * @author DuanJiaNing
 */
public class UnauthorizedException extends BaseRuntimeException {

    public static final int code = 4;

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
