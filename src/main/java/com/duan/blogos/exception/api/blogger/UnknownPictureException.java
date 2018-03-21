package com.duan.blogos.exception.api.blogger;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/20.
 * 图片不存在
 *
 * @author DuanJiaNing
 */
public class UnknownPictureException extends BaseRuntimeException {

    public static final int code = 8;

    public UnknownPictureException(String message) {
        super(message,code);
    }

    public UnknownPictureException() {
        super(code);
    }

    public UnknownPictureException(String message, Throwable e) {
        super(message, e,code);
    }
}
