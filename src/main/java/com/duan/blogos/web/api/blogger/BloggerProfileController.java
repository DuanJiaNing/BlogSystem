package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.profile.ProfileService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/29.
 * 博主个人资料api
 * <p>
 * 1 获取资料
 * 2 新增资料
 * 3 更新资料
 * 4 删除资料
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
        handleAccountCheck(request, bloggerId);

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
                                 @RequestParam(value = "avatarId", required = false) Integer avatarId,
                                 @RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "aboutMe", required = false) String aboutMe,
                                 @RequestParam(value = "intro", required = false) String intro) {
        handleBloggerSignInCheck(request, bloggerId);
        handlePictureExistCheck(request, bloggerId, avatarId);

        checkParams(phone, email, request);
        int id = profileService.insertBloggerProfile(bloggerId, avatarId == null || avatarId <= 0 ? -1 : avatarId,
                phone, email, aboutMe, intro);
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    private void checkParams(String phone, String email, HttpServletRequest request) {
        RequestContext context = new RequestContext(request);
        if (phone != null && !StringUtils.isPhone(phone))
            throw exceptionManager.getParameterFormatIllegalException(context);

        if (email != null && !StringUtils.isEmail(email))
            throw exceptionManager.getParameterFormatIllegalException(context);
    }

    /**
     * 更新资料
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @RequestParam(value = "avatarId", required = false) Integer avatarId,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "aboutMe", required = false) String aboutMe,
                             @RequestParam(value = "intro", required = false) String intro) {

        if (phone == null && email == null && aboutMe == null && intro == null) {
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
        }

        handleBloggerSignInCheck(request, bloggerId);
        handlePictureExistCheck(request, bloggerId, avatarId);

        checkParams(phone, email, request);
        int av = avatarId == null || avatarId <= 0 ? -1 : avatarId;
        boolean result = profileService.updateBloggerProfile(bloggerId, av, phone, email, aboutMe, intro);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }


    /**
     * 删除资料
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable Integer bloggerId) {
        handleBloggerSignInCheck(request, bloggerId);

        boolean result = profileService.deleteBloggerProfile(bloggerId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }
}
