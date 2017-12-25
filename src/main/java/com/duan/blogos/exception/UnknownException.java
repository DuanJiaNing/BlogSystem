package com.duan.blogos.exception;

/**
 * Created on 2017/12/20.
 * 未知错误
 *
 * @author DuanJiaNing
 */
public class UnknownException extends BaseRuntimeException {

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException() {
    }
}
