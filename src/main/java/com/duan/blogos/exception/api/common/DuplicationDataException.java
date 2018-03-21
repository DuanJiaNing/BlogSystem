package com.duan.blogos.exception.api.common;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2018/1/27.
 *
 * @author DuanJiaNing
 */
public class DuplicationDataException extends BaseRuntimeException {

    public static final int code = 18;

    public DuplicationDataException() {
        super(code);
    }

    public DuplicationDataException(String message) {
        super(message, code);
    }

    public DuplicationDataException(String message, Throwable cause) {
        super(message, cause, code);
    }
}
