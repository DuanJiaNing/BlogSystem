package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/21.
 * 字符串未按指定字符间隔错误
 *
 * @author DuanJiaNing
 */
public class StringSplitException extends BaseRuntimeException {

    private static final int code = 2;

    public StringSplitException(String message) {
        super(message, code);
    }

    public StringSplitException() {
        super(code);
    }
}
