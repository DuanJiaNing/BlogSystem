package com.duan.blogos.exception.api.blogger;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2018/1/29.
 * 登录失败
 *
 * @author DuanJiaNing
 */
public class LoginFailException extends BaseRuntimeException {

    public static final int code = 20;

    public LoginFailException() {
        super(code);
    }

    public LoginFailException(String message) {
        super(message, code);
    }

    public LoginFailException(String message, Throwable cause) {
        super(message, cause, code);
    }
}
