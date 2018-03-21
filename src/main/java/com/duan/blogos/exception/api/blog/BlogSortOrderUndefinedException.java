package com.duan.blogos.exception.api.blog;

import com.duan.blogos.exception.BaseRuntimeException;

/**
 * Created on 2017/12/20.
 * 排序顺序错误。
 * <p>
 * 可用的排序顺序只有从高到低（降序，desc），从低到高（升序，asc）两种
 *
 * @author DuanJiaNing
 */
public class BlogSortOrderUndefinedException extends BaseRuntimeException {

    public static final int code = 12;

    public BlogSortOrderUndefinedException(String message) {
        super(message, code);
    }

    public BlogSortOrderUndefinedException() {
        super(code);
    }
}
