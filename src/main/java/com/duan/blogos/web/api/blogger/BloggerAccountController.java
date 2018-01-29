package com.duan.blogos.web.api.blogger;

import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/1/17.
 * 博主账号api
 * <p>
 * 1 注册账号
 * 2 更新账号
 * 3 注销账号
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
        handleCheck(request, username, password);

        int id = accountService.insertAccount(username, password);
        if (id < 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 修改账号
     */
    @RequestMapping(value = "/{bloggerId}", method = RequestMethod.PUT)
    public ResultBean modify(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @RequestParam(value = "username", required = false) String newUserName,
                             @RequestParam(value = "password", required = false) String newPassword) {
        handleBloggerSignInCheck(request, bloggerId);
        handleParamAllNullForUpdate(request, newUserName, newPassword);
        handleCheck(request, newUserName, newPassword);

        boolean result = accountService.updateAccount(bloggerId, newUserName, newPassword);
        if (!result) handlerOperateFail(request);

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

        return new ResultBean<>("");
    }

    // 检查合法性
    private void handleCheck(HttpServletRequest request, String username, String password) {
        if ((!StringUtils.isEmpty(username) && !bloggerValidateManager.checkUserName(username)) ||
                (!StringUtils.isEmpty(password) && !bloggerValidateManager.checkPassword(password)))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
    }

}
