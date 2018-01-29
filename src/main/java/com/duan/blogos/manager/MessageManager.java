package com.duan.blogos.manager;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

/**
 * Created on 2018/1/29.
 *
 * @author DuanJiaNing
 */
@Component
public class MessageManager {

    public String loginFail(RequestContext context, boolean passwordIncorrect) {
        return context.getMessage(passwordIncorrect ? "blogger.passwordIncorrect" : "blogger.unknownAccount");
    }

}
