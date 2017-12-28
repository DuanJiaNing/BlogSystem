package com.duan.blogos.exception;

/**
 * Created on 2017/12/20.
 * 图片不存在
 *
 * @author DuanJiaNing
 */
public class UnknownPictureException extends BaseRuntimeException {

    public UnknownPictureException(String message) {
        super(message);
    }

    public UnknownPictureException() {
    }

    public UnknownPictureException(String message, Throwable e) {
        super(message, e);
    }
}
