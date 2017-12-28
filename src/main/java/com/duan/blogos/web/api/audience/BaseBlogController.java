package com.duan.blogos.web.api.audience;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.exception.UnknownBlogException;
import com.duan.blogos.exception.UnknownBloggerException;
import com.duan.blogos.manager.AudiencePropertiesManager;
import com.duan.blogos.manager.validate.BlogValidateManager;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import com.duan.blogos.web.api.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
public class BaseBlogController extends BaseRestController {

    @Autowired
    protected AudiencePropertiesManager audiencePropertiesManager;

    /**
     * 检查博文和博主是否存在，检查通过应该返回null
     */
    protected BaseRuntimeException check(RequestContext context, Integer blogId, Integer... bloggerIds) {
        UnknownBlogException exception1 = checkBlogExist(context, blogId);
        if (exception1 != null) return exception1;

        if (bloggerIds != null) {
            for (Integer id : bloggerIds) {
                UnknownBloggerException exception = checkAccount(context, id);
                if (exception != null) return exception;
            }
        }

        return null;
    }
}
