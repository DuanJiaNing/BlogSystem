package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/20.
 * 链接不存在
 *
 * @author DuanJiaNing
 */
public class UnknownLinkException extends BaseRuntimeException {

    private static final int code = 17;

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
