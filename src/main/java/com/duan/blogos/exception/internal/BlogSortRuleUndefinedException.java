package com.duan.blogos.exception.internal;

/**
 * Created on 2017/12/20.
 * 博文排序规则未定义
 * <p>
 * 可用的排序规则定义在{@link com.duan.blogos.common.Rule}中
 *
 * @author DuanJiaNing
 */
public class BlogSortRuleUndefinedException extends BaseRuntimeException {

    private static final int code = 13;

    public BlogSortRuleUndefinedException(String message) {
        super(message, code);
    }

    public BlogSortRuleUndefinedException() {
        super(code);
    }
}
