package com.duan.blogos.web.api.blogger;

import com.duan.blogos.exception.UnknownBloggerException;
import com.duan.blogos.manager.BloggerPropertiesManager;
import com.duan.blogos.web.api.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/28.
 *
 * @author DuanJiaNing
 */
public class BaseBloggerController extends BaseRestController {

    @Autowired
    BloggerPropertiesManager bloggerPropertiesManager;

    protected void checkAccount(HttpServletRequest request, Integer bloggerId) {
        RequestContext context = new RequestContext(request);
        UnknownBloggerException exception = checkAccount(context, bloggerId);
        if (exception != null) throw exception;
    }

}
