package com.duan.blogos.exception.api.parameter;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/22.
 * 参数错误
 *
 * @author DuanJiaNing
 */
public class ParameterIllegalException extends BaseRuntimeException {

    public static final int code = 3;

    public ParameterIllegalException() {
        super(code);
    }

    public ParameterIllegalException(String message) {
        super(message, code);
    }
}
