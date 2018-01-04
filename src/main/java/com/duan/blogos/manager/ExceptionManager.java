package com.duan.blogos.manager;

import com.duan.blogos.exception.*;
import com.duan.blogos.exception.UnspecifiedOperationException;
import com.duan.blogos.result.ResultBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.support.RequestContext;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
@Component
public class ExceptionManager {

    public EmptyResultException getEmptyResultException(RequestContext context) {
        return new EmptyResultException(context.getMessage("common.emptyResult"));
    }

    public UnknownBlogException getUnknownBlogException(RequestContext context) {
        return new UnknownBlogException(context.getMessage("blog.unknownBlog"));
    }

    public UnknownBloggerException getUnknownBloggerException(RequestContext context) {
        return new UnknownBloggerException(context.getMessage("blogger.unknownBlogger"));
    }

    public StringSplitException getStringSplitException(RequestContext context) {
        return new StringSplitException(context.getMessage("common.stringSplitIllegal"));
    }

    public BlogSortRuleUndefinedException getBlogSortRuleUndefinedException(RequestContext context) {
        return new BlogSortRuleUndefinedException(context.getMessage("blog.blogSortRuleUndefined"));
    }

    public BlogSortOrderUndefinedException getBlogSortOrderUndefinedException(RequestContext context) {
        return new BlogSortOrderUndefinedException(context.getMessage("blog.blogSortOrderUndefined"));
    }

    public ParameterIllegalException getParameterIllegalException(RequestContext context) {
        return new ParameterIllegalException(context.getMessage("common.parameterIllegal"));
    }

    public OperateFailException getOperateFailException(RequestContext context) {
        return new OperateFailException(context.getMessage("common.operateFail"));
    }

    public UnspecifiedOperationException getUnspecifiedOperationException(RequestContext context) {
        return new UnspecifiedOperationException(context.getMessage("common.unspecifiedOperation"));
    }

    public UnknownException getUnknownException(RequestContext context, Throwable e) {
        return new UnknownException(context.getMessage("common.UnknownError"), e);
    }

    public MissingRequestParameterException getMissingRequestParameterException(RequestContext context, Throwable e) {
        return new MissingRequestParameterException(context.getMessage("common.missingRequestParameter"), e);
    }

    public UnknownPictureException getUnknownPictureException(RequestContext context) {
        return new UnknownPictureException(context.getMessage("common.unknownPicture"));
    }

    public UnknownLinkException getUnknownLinkException(RequestContext context) {
        return new UnknownLinkException(context.getMessage("common.unknownLink"));
    }

    public OperateFailException getOperateFailException(RequestContext context, Throwable e) {
        return new OperateFailException(context.getMessage("common.operateFail"), e);
    }

    public UnauthorizedException getUnauthorizedException(RequestContext context) {
        return new UnauthorizedException(context.getMessage("common.unauthorized"));
    }
}
