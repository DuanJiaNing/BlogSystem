package com.duan.blogos.exception;

/**
 * Created on 2017/12/20.
 * 未知博文
 *
 * @author DuanJiaNing
 */
public class UnknownCategoryException extends BaseRuntimeException {

    public UnknownCategoryException(String message) {
        super(message);
    }

    public UnknownCategoryException() {
    }
}
