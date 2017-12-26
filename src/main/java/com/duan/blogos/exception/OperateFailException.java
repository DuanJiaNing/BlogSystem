package com.duan.blogos.exception;

/**
 * Created on 2017/12/22.
 * 操作执行失败异常
 *
 * @author DuanJiaNing
 */
public class OperateFailException extends BaseRuntimeException {

    public OperateFailException() {
    }

    public OperateFailException(String message) {
        super(message);
    }
}
