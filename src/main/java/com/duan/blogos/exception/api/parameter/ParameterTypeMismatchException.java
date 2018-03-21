package com.duan.blogos.exception.api.parameter;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2018/1/29.
 * 参数类型与目标类型不匹配
 *
 * @author DuanJiaNing
 */
public class ParameterTypeMismatchException extends BaseRuntimeException {

    public static final int code = 19;

    public ParameterTypeMismatchException() {
        super(code);
    }

    public ParameterTypeMismatchException(String message) {
        super(message, code);
    }

    public ParameterTypeMismatchException(String message, Throwable cause) {
        super(message, cause, code);
    }
}
