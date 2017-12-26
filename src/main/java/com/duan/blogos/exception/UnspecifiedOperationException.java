package com.duan.blogos.exception;

/**
 * Created on 2017/12/22.
 * 不支持的操作
 *
 * @author DuanJiaNing
 */
public class UnspecifiedOperationException extends BaseRuntimeException {

    public UnspecifiedOperationException() {
    }

    public UnspecifiedOperationException(String message) {
        super(message);
    }
}
