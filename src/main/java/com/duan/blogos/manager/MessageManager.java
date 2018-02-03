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

    /**
     * 获得登录失败说明信息
     *
     * @param passwordIncorrect 密码是否错误：true 表明用户名正确，但密码错误，false 表明用户名（错误）不存在
     * @return 国际化了的说明信息
     */
    public String getLoginFailMessage(RequestContext context, boolean passwordIncorrect) {
        return context.getMessage(passwordIncorrect ? "blogger.passwordIncorrect" : "blogger.unknownAccount");
    }

}
