package com.duan.blogos.exception;

/**
 * Created on 2017/12/22.
 * 空的参数
 *
 * @author DuanJiaNing
 */
public class ParameterIllegalException extends BaseRuntimeException {

    public ParameterIllegalException() {
    }

    public ParameterIllegalException(String message) {
        super(message);
    }
}
