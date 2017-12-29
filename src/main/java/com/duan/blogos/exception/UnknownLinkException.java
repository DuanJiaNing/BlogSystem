package com.duan.blogos.exception;

/**
 * Created on 2017/12/20.
 * 链接不存在
 *
 * @author DuanJiaNing
 */
public class UnknownLinkException extends BaseRuntimeException {

    public UnknownLinkException(String message) {
        super(message);
    }

    public UnknownLinkException() {
    }

    public UnknownLinkException(String message, Throwable e) {
        super(message, e);
    }
}
