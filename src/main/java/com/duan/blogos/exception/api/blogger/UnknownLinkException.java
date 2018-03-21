package com.duan.blogos.exception.api.blogger;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/20.
 * 友情链接不存在
 *
 * @author DuanJiaNing
 */
public class UnknownLinkException extends BaseRuntimeException {

    public static final int code = 17;

    public UnknownLinkException(String message) {
        super(message,code);
    }

    public UnknownLinkException() {
        super(code);
    }

    public UnknownLinkException(String message, Throwable e) {
        super(message, e,code);
    }
}
