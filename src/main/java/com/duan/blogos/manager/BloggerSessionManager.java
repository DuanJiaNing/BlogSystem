package com.duan.blogos.manager;

import com.duan.blogos.manager.properties.BloggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2018/3/13.
 *
 * @author DuanJiaNing
 */
@Component
public class BloggerSessionManager {

    @Autowired
    private BloggerProperties bloggerProperties;

    /**
     * 获得登录博主id，未登录返回-1
     *
     * @param request
     * @return
     */
    public int getLoginBloggerId(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object obj = session.getAttribute(bloggerProperties.getSessionNameOfBloggerId());

        return obj == null ? -1 : (Integer) obj;
    }

    /**
     * 获得登录博主用户名，未登录返回null
     *
     * @param request
     * @return
     */
    public String getLoginBloggerName(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object obj = session.getAttribute(bloggerProperties.getSessionNameOfBloggerName());

        return obj == null ? null : obj.toString();
    }


}
