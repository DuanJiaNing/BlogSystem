package com.duan.blogos.exception.api.parameter;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/21.
 * 字符串未按指定字符间隔
 *
 * @author DuanJiaNing
 */
public class ParameterStringSplitException extends BaseRuntimeException {

    public static final int code = 2;

    public ParameterStringSplitException(String message) {
        super(message, code);
    }

    public ParameterStringSplitException() {
        super(code);
    }
}
