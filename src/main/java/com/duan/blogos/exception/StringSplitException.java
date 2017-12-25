package com.duan.blogos.exception;

/**
 * Created on 2017/12/21.
 * 字符串未按指定字符间隔错误
 *
 * @author DuanJiaNing
 */
public class StringSplitException extends BaseRuntimeException {
    public StringSplitException(String message) {
        super(message);
    }

    public StringSplitException() {
    }
}
