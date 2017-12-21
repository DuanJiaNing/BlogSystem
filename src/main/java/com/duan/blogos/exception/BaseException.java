package com.duan.blogos.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2017/12/20.
 * 这些异常不应该传递到前端，以日志的形式记录
 *
 * @author DuanJiaNing
 */
public class BaseException extends Exception {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseException() {
        logger.info(this.getClass().getName(), this);
    }

    public BaseException(String message) {
        super(message);
        logger.info(message, this);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        logger.info(message, this);
    }

    public BaseException(Throwable cause) {
        super(cause);
        logger.info(this.getClass().getName(), this);
    }
}
