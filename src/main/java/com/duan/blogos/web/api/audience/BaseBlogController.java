package com.duan.blogos.web.api.audience;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.BlogPropertiesManager;
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
    protected BlogPropertiesManager blogPropertiesManager;

    /**
     * 检查博文和博主是否存在，检查通过应该返回null
     */
    protected BaseRuntimeException checkBlogAndBloggerExist(RequestContext context, Integer blogId, Integer... bloggerIds) {
        BaseRuntimeException exception1 = checkBlogExist(context, blogId);
        if (exception1 != null) return exception1;

        if (bloggerIds != null) {
            for (Integer id : bloggerIds) {
                BaseRuntimeException exception = checkAccount(context, id);
                if (exception != null) return exception;
            }
        }

        return null;
    }

    /**
     * 检查博主是否存在，不存在直接抛出异常
     */
    protected void handleAccountCheck(HttpServletRequest request, Integer bloggerId) {
        RequestContext context = new RequestContext(request);
        BaseRuntimeException exception = checkAccount(context, bloggerId);
        if (exception != null) throw exception;
    }

}
