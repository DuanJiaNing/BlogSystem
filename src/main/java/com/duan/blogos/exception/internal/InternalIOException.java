package com.duan.blogos.exception.internal;

/**
 * Created on 2018/1/15.
 * lucene IO操作出错
 *
 * @author DuanJiaNing
 */
public class InternalIOException extends InternalRuntimeException {

    private static final int code = 2;

    public InternalIOException(Throwable e) {
        super(e, code);
    }
}
