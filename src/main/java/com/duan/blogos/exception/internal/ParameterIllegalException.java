package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/22.
 * 空的参数
 *
 * @author DuanJiaNing
 */
public class ParameterIllegalException extends BaseRuntimeException {

    private static final int code = 3;

    public ParameterIllegalException() {
        super(code);
    }

    public ParameterIllegalException(String message) {
        super(message, code);
    }
}
