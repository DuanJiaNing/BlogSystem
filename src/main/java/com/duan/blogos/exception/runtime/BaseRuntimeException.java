package com.duan.blogos.exception.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2017/12/20.
 * 不受检查的异常，这些异常将会传递（反映）到前端
 *
 * @author DuanJiaNing
 */
public class BaseRuntimeException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseRuntimeException() {
        logger.error(this.getClass().getName(), this);
    }

    public BaseRuntimeException(String message) {
        super(message);
        logger.error(message, this);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, this);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
        logger.error(this.getClass().getName(), this);
    }
}
