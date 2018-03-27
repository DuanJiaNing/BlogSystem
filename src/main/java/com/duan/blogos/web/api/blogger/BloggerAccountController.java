package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2018/1/17.
 * 博主账号api
 * <p>
 * 1 注册账号
 * 2 修改用户名
 * 3 修改密码
 * 4 注销账号
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger")
public class BloggerAccountController extends BaseBloggerController {

    @Autowired
    private BloggerAccountService accountService;

    /**
     * 注册
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean register(HttpServletRequest request,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {
        handleNameCheck(request, username);
        handlePwdCheck(request, password);

        int id = accountService.insertAccount(username, password);
        if (id < 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 检查用户名是否存在
     */
    @RequestMapping(value = "/check=username", method = RequestMethod.GET)
    public ResultBean checkUsernameUsed(HttpServletRequest request,
                                        @RequestParam("username") String username) {
        handleNameCheck(request, username);

        BloggerAccount account = accountService.getAccount(username);
        if (account != null) {
            return new ResultBean(exceptionManager.getDuplicationDataException(new RequestContext(request)));
        } else {
            return new ResultBean<>("");
        }

    }

    /**
     * 检查电话号码是否存在
     */
    @RequestMapping(value = "/check=phone", method = RequestMethod.GET)
    public ResultBean checkProfileExist(HttpServletRequest request,
                                        @RequestParam("phone") String phone) {
        BloggerAccount account = accountService.getAccountByPhone(phone);

        if (account != null) {
            return new ResultBean(exceptionManager.getDuplicationDataException(new RequestContext(request)));
        } else {
            return new ResultBean<>("");
        }

    }

    /**
     * 修改用户名
     */
    @RequestMapping(value = "/{bloggerId}/item=name", method = RequestMethod.PUT)
    public ResultBean modifyUsername(HttpServletRequest request,
                                     @PathVariable Integer bloggerId,
                                     @RequestParam(value = "username") String newUserName) {
        handleBloggerSignInCheck(request, bloggerId);
        handleNameCheck(request, newUserName);

        boolean result = accountService.updateAccountUserName(bloggerId, newUserName);
        if (!result) handlerOperateFail(request);

        // 更新session信息
        HttpSession session = request.getSession();
        session.setAttribute(bloggerProperties.getSessionNameOfBloggerName(), newUserName);

        return new ResultBean<>("");
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/{bloggerId}/item=pwd", method = RequestMethod.PUT)
    public ResultBean modifyPassword(HttpServletRequest request,
                                     @PathVariable Integer bloggerId,
                                     @RequestParam(value = "old") String oldPassword,
                                     @RequestParam(value = "new") String newPassword) {
        handleBloggerSignInCheck(request, bloggerId);
        handlePwdCheck(request, newPassword);

        boolean result = accountService.updateAccountPassword(bloggerId, oldPassword, newPassword);
        if (!result) handlerOperateFail(request);

        // session 失效，重新登录
        HttpSession session = request.getSession();
        session.invalidate();

        return new ResultBean<>("");
    }


    /**
     * 注销账号
     */
    @RequestMapping(value = "/{bloggerId}", method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable Integer bloggerId) {
        handleBloggerSignInCheck(request, bloggerId);

        boolean result = accountService.deleteAccount(bloggerId);
        if (!result) handlerOperateFail(request);

        // session 失效
        HttpSession session = request.getSession();
        session.invalidate();

        return new ResultBean<>("");
    }

    // 检查用户名合法性
    private void handleNameCheck(HttpServletRequest request, String userName) {
        if (StringUtils.isEmpty_(userName) || !bloggerValidateManager.checkUserName(userName))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
    }

    // 检查密码合法性
    private void handlePwdCheck(HttpServletRequest request, String password) {
        if (StringUtils.isEmpty_(password) || !bloggerValidateManager.checkPassword(password))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
    }
}
