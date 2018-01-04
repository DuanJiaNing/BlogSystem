package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.profile.ProfileService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/29.
 * 博主个人资料api
 * 获取资料
 * 新增资料
 * 更新资料
 * 删除资料
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/profile")
public class BloggerProfileController extends BaseBloggerController {

    @Autowired
    private ProfileService profileService;

    /**
     * 获取资料
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<BloggerProfile> get(HttpServletRequest request,
                                          @PathVariable Integer bloggerId) {
        checkAccount(request, bloggerId);

        BloggerProfile profile = profileService.getBloggerProfile(bloggerId);
        if (profile == null) handlerEmptyResult(request);

        return new ResultBean<>(profile);
    }

    /**
     * 新增资料
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean newProfile(HttpServletRequest request,
                                 @PathVariable Integer bloggerId,
                                 @RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "aboutMe", required = false) String aboutMe,
                                 @RequestParam(value = "intro", required = false) String intro) {
        checkAccount(request, bloggerId);
        checkParams(phone, email, request);
        int id = profileService.insertBloggerProfile(bloggerId, phone, email, aboutMe, intro);
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    private void checkParams(String phone, String email, HttpServletRequest request) {
        RequestContext context = new RequestContext(request);
        if (phone != null && !StringUtils.isPhone(phone)) throw exceptionManager.getParameterIllegalException(context);
        if (email != null && !StringUtils.isEmail(email)) throw exceptionManager.getParameterIllegalException(context);
    }

    /**
     * 更新资料
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "aboutMe", required = false) String aboutMe,
                             @RequestParam(value = "intro", required = false) String intro) {

        if (phone == null && email == null && aboutMe == null && intro == null) {
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
        }

        checkAccount(request, bloggerId);
        checkParams(phone, email, request);
        boolean result = profileService.updateBloggerProfile(bloggerId, phone, email, aboutMe, intro);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }


    /**
     * 删除资料
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable Integer bloggerId) {
        checkAccount(request, bloggerId);

        boolean result = profileService.deleteBloggerProfile(bloggerId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }
}
