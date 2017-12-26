package com.duan.blogos.web.api;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.ExceptionManager;
import com.duan.blogos.result.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
public class BaseRestController {

    @Autowired
    protected ExceptionManager exceptionManager;

    /**
     * 处理结果为空的情况
     */
    protected void handlerEmptyResult(RequestContext context) {
        throw exceptionManager.getEmptyResultException(context);
    }

    /**
     * 统一处理异常
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public ResultBean exceptionHandler(BaseRuntimeException e) {
        return new ResultBean(e);
    }

    /**
     * 统一处理异常
     */
    @ExceptionHandler(Exception.class)
    public ResultBean exceptionHandler(HttpServletRequest request, Throwable e) {
        return new ResultBean(exceptionManager.getUnknownException(new RequestContext(request), e));
    }

    /**
     * 未指明操作
     */
    @RequestMapping("")
    public void defaultOperation(HttpServletRequest request) {
        throw exceptionManager.getUnspecifiedOperationException(new RequestContext(request));
    }

}
