package com.duan.blogos.exception;

/**
 * Created on 2018/1/11.
 * 博主未登录异常
 *
 * @author DuanJiaNing
 */
public class BloggerNotLoggedInException extends BaseRuntimeException {
    public BloggerNotLoggedInException() {
    }

    public BloggerNotLoggedInException(String message) {
        super(message);
    }

    public BloggerNotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
}
