package com.duan.blogos.exception;

/**
 * Created on 2017/12/22.
 * 空的结果异常
 *
 * @author DuanJiaNing
 */
public class EmptyResultException extends BaseRuntimeException {

    public EmptyResultException() {
    }

    public EmptyResultException(String message) {
        super(message);
    }
}
