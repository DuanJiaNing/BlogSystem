package com.duan.blogos.web.api.audience;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.properties.BlogProperties;
import com.duan.blogos.web.api.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
public class BaseBlogController extends BaseRestController {

    @Autowired
    protected BlogProperties blogProperties;

    /**
     * 检查博主是否存在，不存在直接抛出异常
     */
    protected void handleAccountCheck(HttpServletRequest request, Integer bloggerId) {
        BaseRuntimeException exception;
        if ((exception = checkAccountExist(new RequestContext(request), bloggerId)) != null)
            throw exception;
    }

    /**
     * 检查博文和博主是否存在，检查通过应该返回null
     */
    protected void handleBlogAndBloggerExistCheck(RequestContext context, Integer blogId, Integer... bloggerIds) {
        BaseRuntimeException exception1 = checkBlogExist(context, blogId);
        if (exception1 != null) throw exception1;

        if (bloggerIds != null) {
            for (Integer id : bloggerIds) {
                BaseRuntimeException exception = checkAccountExist(context, id);
                if (exception != null) throw exception;
            }
        }
    }

    /**
     * 先检查博主是否存在，后检查检查博主是否登录，在API中，一些获取数据的操作是不需要博主登录的，但类似于修改，删除，
     * 新增以及关键数据的操纵需要验证身份。
     * <p>
     * 如果验证不通过将直接抛出运行时异常。
     *
     * @param bloggerId 博主id
     */
    protected void handleBloggerSignInCheck(HttpServletRequest request, Integer bloggerId) {
        handleAccountCheck(request, bloggerId);

        // 检查当前登录否
        if (!bloggerValidateManager.checkBloggerSignIn(request, bloggerId))
            throw exceptionManager.getBloggerNotLoggedInException(new RequestContext(request));
    }

}
