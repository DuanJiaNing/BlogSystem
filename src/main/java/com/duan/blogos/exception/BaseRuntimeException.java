package com.duan.blogos.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2017/12/20.
 * 不受检查的异常，这些异常将会传递（反映）到调用端以标识输入错误和执行失败的情况。
 *
 * @author DuanJiaNing
 */
public class BaseRuntimeException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 异常对应的编号，子类需要赋一个对应自己错误的唯一值
     */
    protected final int code;

    public BaseRuntimeException(int code) {
        this.code = code;

        logger.error(this.getClass().getName(), this);
    }

    public BaseRuntimeException(String message, int code) {
        super(message);
        this.code = code;

        logger.error(message, this);
    }

    public BaseRuntimeException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;

        logger.error(message, this);
    }

    public BaseRuntimeException(Throwable cause, int code) {
        super(cause);
        this.code = code;

        logger.error(this.getClass().getName(), this);
    }

    public int getCode() {
        return code;
    }
}
