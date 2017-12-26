package com.duan.blogos.exception;

/**
 * Created on 2017/12/26.
 * 缺失请求参数
 *
 * @author DuanJiaNing
 */
public class MissingRequestParameterException extends BaseRuntimeException {
    public MissingRequestParameterException() {
    }

    public MissingRequestParameterException(String message) {
        super(message);
    }

    public MissingRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

}
