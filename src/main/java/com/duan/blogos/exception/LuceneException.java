package com.duan.blogos.exception;

import java.io.IOException;

/**
 * Created on 2018/1/15.
 * lucene 操作时出错
 *
 * @author DuanJiaNing
 */
public class LuceneException extends BaseRuntimeException {
    public LuceneException(IOException e) {
        super(e);
    }
}
