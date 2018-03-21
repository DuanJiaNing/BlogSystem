package com.duan.blogos.exception.api.common;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/26.
 * 缺失请求参数
 *
 * @author DuanJiaNing
 */
public class MissingRequestParameterException extends BaseRuntimeException {

    public static final int code = 16;

    public MissingRequestParameterException() {
        super(code);
    }

    public MissingRequestParameterException(String message) {
        super(message, code);
    }

    public MissingRequestParameterException(String message, Throwable cause) {
        super(message, cause, code);
    }

}
