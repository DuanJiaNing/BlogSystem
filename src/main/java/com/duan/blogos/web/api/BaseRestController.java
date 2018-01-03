package com.duan.blogos.web.api;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.exception.UnknownBlogException;
import com.duan.blogos.exception.UnknownBloggerException;
import com.duan.blogos.manager.ExceptionManager;
import com.duan.blogos.manager.UrlConstructorManager;
import com.duan.blogos.manager.validate.BlogValidateManager;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import com.duan.blogos.result.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BaseRestController {

    @Autowired
    protected ExceptionManager exceptionManager;

    @Autowired
    protected BlogValidateManager blogValidateManager;

    @Autowired
    protected BloggerValidateManager bloggerValidateManager;

    @Autowired
    protected UrlConstructorManager urlConstructorManager;

    /**
     * 处理结果为空的情况
     */
    protected void handlerEmptyResult(RequestContext context) {
        throw exceptionManager.getEmptyResultException(context);
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
     * 统一处理异常
     */
    @ExceptionHandler(BaseRuntimeException.class)
    @ResponseBody
    // 注解无法继承，所以子类不允许覆盖这些方法
    protected final ResultBean handlerException(BaseRuntimeException e) {
        return new ResultBean(e);
    }

    /**
     * 统一处理异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected final ResultBean handlerException(HttpServletRequest request, Throwable e) {
        return new ResultBean(exceptionManager.getUnknownException(new RequestContext(request), e));
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
     * 未指明操作
     */
    @RequestMapping("")
    protected void defaultOperation(HttpServletRequest request) {
        throw exceptionManager.getUnspecifiedOperationException(new RequestContext(request));
    }

    /**
     * 检查博主账户是否存在，存在返回null
     */
    protected UnknownBloggerException checkAccount(RequestContext context, Integer bloggerId) {
        if (bloggerId == null || bloggerId <= 0 || bloggerValidateManager.checkAccount(bloggerId) == null) {
            return exceptionManager.getUnknownBloggerException(context);
        }

        return null;
    }

    /**
     * 检查博文是否存在，存在返回null
     */
    protected UnknownBlogException checkBlogExist(RequestContext context, Integer blogId) {
        if (blogId == null || blogId <= 0 || !blogValidateManager.checkBlogExist(blogId)) {
            return exceptionManager.getUnknownBlogException(context);
        }

        return null;
    }

}
