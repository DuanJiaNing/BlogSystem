package com.duan.blogos.exception.internal;

/**
 * Created on 2018/1/15.
 * lucene SQL操作出错
 *
 * @author DuanJiaNing
 */
public class SQLException extends InternalRuntimeException {

    public static final int code = 3;

    public SQLException() {
        super(code);
    }

    public SQLException(Throwable e) {
        super(e, code);
    }
}
