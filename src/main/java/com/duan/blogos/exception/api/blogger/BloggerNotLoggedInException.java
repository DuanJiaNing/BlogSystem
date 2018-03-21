package com.duan.blogos.exception.api.blogger;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2018/1/11.
 * 博主未登录异常。
 * <p>
 * 一些api需要博主先登录才能执行操作
 *
 * @author DuanJiaNing
 */
public class BloggerNotLoggedInException extends BaseRuntimeException {

    public static final int code = 1;

    public BloggerNotLoggedInException() {
        super(code);
    }

    public BloggerNotLoggedInException(String message) {
        super(message, code);
    }

    public BloggerNotLoggedInException(String message, Throwable cause) {
        super(message, cause, code);
    }

}
