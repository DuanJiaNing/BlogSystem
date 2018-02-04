package com.duan.blogos.web.api;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.exception.internal.InternalRuntimeException;
import com.duan.blogos.manager.ExceptionManager;
import com.duan.blogos.manager.StringConstructorManager;
import com.duan.blogos.manager.properties.WebsiteProperties;
import com.duan.blogos.manager.validate.BlogValidateManager;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import com.duan.blogos.restful.ResultBean;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
public class RestController {

    @Autowired
    protected ExceptionManager exceptionManager;

    /**
     * 处理结果为空的情况
     *
     * @param request
     */
    protected void handlerEmptyResult(HttpServletRequest request) {
        throw exceptionManager.getEmptyResultException(new RequestContext(request));
    }

    /**
     * 处理操作失败的情况
     */
    protected void handlerOperateFail(HttpServletRequest request) {
        throw exceptionManager.getOperateFailException(new RequestContext(request));
    }

    /**
     * 处理操作失败的情况
     */
    protected void handlerOperateFail(HttpServletRequest request, Throwable e) {
        throw exceptionManager.getOperateFailException(new RequestContext(request), e);
    }

    /**
     * 统一处理异常，这些异常需要通知API调用者
     */
    @ExceptionHandler(BaseRuntimeException.class)
    @ResponseBody
    // 注解无法继承，所以子类不允许覆盖这些方法
    protected final ResultBean handleException(BaseRuntimeException e) {
        return new ResultBean(e);
    }

    /**
     * 统一处理异常，这些异常是服务器异常
     */
    @ExceptionHandler(InternalRuntimeException.class)
    @ResponseBody
    protected final ResultBean handleException(HttpServletRequest request, Exception e) {
        //转化为未知异常
        return new ResultBean(exceptionManager.getUnknownException(new RequestContext(request), e));
    }

    /**
     * 未进行转化的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected final ResultBean handleException(HttpServletRequest request, Throwable e) {

        //---------------未进行转化但需要通知调用者的异常--------------^
        if (e instanceof DuplicateKeyException) {
            // 数据库“重复键”，违反唯一约束错误
            return handleException(exceptionManager.getDuplicationDataException(new RequestContext(request)));
        }

        return handleException(request, (Exception) e);
    }


    /**
     * 统一处理“请求参数缺失”错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    protected final ResultBean handlerException(HttpServletRequest request, MissingServletRequestParameterException e) {
        return new ResultBean(exceptionManager.getMissingRequestParameterException(new RequestContext(request), e));
    }

    /**
     * 统一处理“请求参数与目标参数类型不匹配”错误
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    protected final ResultBean handlerException(HttpServletRequest request, TypeMismatchException e) {
        return new ResultBean(exceptionManager.getParameterTypeMismatchException(new RequestContext(request), e));
    }

    /**
     * 未指明操作
     */
    @RequestMapping
    protected void defaultOperation(HttpServletRequest request) {
        throw exceptionManager.getUnspecifiedOperationException(new RequestContext(request));
    }

}
