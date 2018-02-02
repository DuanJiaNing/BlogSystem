package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.manager.MessageManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 2018/1/11.
 * 博主登录
 * <p>
 * 1 用户名登录
 * 2 电话号码登录
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/login")
public class BloggerLoginController extends BaseBloggerController {

    @Autowired
    private BloggerAccountService accountService;

    @Autowired
    private MessageManager messageManager;

    @RequestMapping(value = "/way=name", method = RequestMethod.POST)
    public ResultBean loginWithUserName(HttpServletRequest request,
                                        @RequestParam("username") String userName,
                                        @RequestParam("password") String password) throws NoSuchAlgorithmException {
        // TODO 使用shiro

        BloggerAccount account = accountService.getAccount(userName);
        if (account != null && account.getUsername().equals(userName) &&
                account.getPassword().equals(new BigInteger(StringUtils.toSha(password)).toString())) {

            HttpSession session = request.getSession();
            session.setAttribute(bloggerPropertiesManager.getSessionNameOfBloggerId(), account.getId());
            session.setAttribute(bloggerPropertiesManager.getSessionNameOfBloggerName(), account.getUsername());

            // 成功登录
            return new ResultBean<>("");
        } else {

            // TODO 判断登录失败的原因
            String errorMsg = messageManager.loginFail(new RequestContext(request), false);
            request.getServletContext().setAttribute(bloggerPropertiesManager.getSessionNameOfErrorMsg(), errorMsg);

            return new ResultBean(exceptionManager.getLoginFailException(new RequestContext(request), false));
        }
    }

    @RequestMapping(value = "/way=phone", method = RequestMethod.POST)
    public ResultBean loginWithPhoneNumber(HttpServletRequest request,
                                           @RequestParam("phone") String phone,
                                           @RequestParam("password") String password) {

        return null;
    }

}
