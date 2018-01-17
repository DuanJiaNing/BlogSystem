package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2018/1/11.
 * 博主登录
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/login")
public class BloggerLoginController extends BaseBloggerController {

    @Autowired
    private BloggerAccountService accountService;

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public ResultBean loginWithUserName(HttpServletRequest request,
                                        @RequestParam("username") String userName,
                                        @RequestParam("password") String password) {
        // TODO 使用shiro

        BloggerAccount account = accountService.getAccount(userName);
        if (account.getUsername().equalsIgnoreCase(userName) && account.getPassword().equalsIgnoreCase(password)) {

            HttpSession session = request.getSession();
            session.setAttribute(bloggerPropertiesManager.getSessionNameOfBloggerId(), account.getId());
            session.setAttribute(bloggerPropertiesManager.getSessionNameOfBloggerName(), account.getUsername());

            return null;
        } else {

            request.getServletContext().setAttribute(bloggerPropertiesManager.getSessionNameOfErrorMsg(), "登陆失败");
            return null;

        }
    }

    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public void loginWithPhoneNumber(HttpServletRequest request,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("password") String password) {

    }

}
