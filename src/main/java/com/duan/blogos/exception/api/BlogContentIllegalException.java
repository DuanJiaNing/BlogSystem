package com.duan.blogos.exception.api;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/20.
 * 博文内容违规。
 * <p>
 * 博文的标题，内容，摘要或关键字包含违规内容。
 *
 * @author DuanJiaNing
 */
public class BlogContentIllegalException extends BaseRuntimeException {

    private static final int code = 11;

    public BlogContentIllegalException(String message) {
        super(message, code);
    }

    public BlogContentIllegalException() {
        super(code);
    }
}
