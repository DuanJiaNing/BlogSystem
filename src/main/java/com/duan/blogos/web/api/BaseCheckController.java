package com.duan.blogos.web.api;

import com.duan.blogos.manager.validate.BlogValidateManager;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/2/4.
 *
 * @author DuanJiaNing
 */
public class BaseCheckController extends RestController {

    @Autowired
    protected BlogValidateManager blogValidateManager;

    @Autowired
    protected BloggerValidateManager bloggerValidateManager;

    /**
     * 检查博主是否存在，不存在直接抛出异常
     */
    protected void handleAccountCheck(HttpServletRequest request, Integer bloggerId) {
        if (bloggerId == null || bloggerId <= 0 || !bloggerValidateManager.checkAccountExist(bloggerId)) {
            throw exceptionManager.getUnknownBloggerException(new RequestContext(request));
        }
    }

    /**
     * 先检查博主是否存在，后检查检查博主是否登录
     * <p>
     * 在API中，一些获取数据的操作是不需要博主登录的，但类似于修改，删除，新增以及关键数据的操纵需要验证身份。
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

    /**
     * 检查博文是否存在,不存在直接抛出异常
     */
    protected void handleBlogExistCheck(HttpServletRequest request, Integer blogId) {
        if (blogId == null || blogId <= 0 || !blogValidateManager.checkBlogExist(blogId)) {
            throw exceptionManager.getUnknownBlogException(new RequestContext(request));
        }
    }

}
