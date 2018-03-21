package com.duan.blogos.exception.api.common;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/22.
 * 操作执行失败异常
 *
 * @author DuanJiaNing
 */
public class OperateFailException extends BaseRuntimeException {

    public static final int code = 18;

    public OperateFailException() {
        super(code);
    }

    public OperateFailException(String message) {
        super(message, code);
    }

    public OperateFailException(String message, Throwable e) {
        super(message, e, code);
    }
}
