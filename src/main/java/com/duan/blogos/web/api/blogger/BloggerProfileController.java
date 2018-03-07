package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerPictureService;
import com.duan.blogos.service.blogger.BloggerProfileService;
import com.duan.blogos.util.StringUtils;
import org.apache.shiro.crypto.hash.format.Base64Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

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
    private BloggerProfileService bloggerProfileService;

    @Autowired
    private BloggerPictureService bloggerPictureService;

    /**
     * 获取资料
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<BloggerProfile> get(HttpServletRequest request,
                                          @PathVariable Integer bloggerId) {
        handleAccountCheck(request, bloggerId);

        BloggerProfile profile = bloggerProfileService.getBloggerProfile(bloggerId);
        if (profile == null) handlerEmptyResult(request);

        return new ResultBean<>(profile);
    }

    /**
     * 新增资料
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean add(HttpServletRequest request,
                          @PathVariable Integer bloggerId,
                          @RequestParam(value = "avatarId", required = false) Integer avatarId,
                          @RequestParam(value = "phone", required = false) String phone,
                          @RequestParam(value = "email", required = false) String email,
                          @RequestParam(value = "aboutMe", required = false) String aboutMe,
                          @RequestParam(value = "intro", required = false) String intro) {
        handleBloggerSignInCheck(request, bloggerId);
        handlePictureExistCheck(request, bloggerId, avatarId);

        handleParamsCheck(phone, email, request);
        int id = bloggerProfileService.insertBloggerProfile(bloggerId, avatarId == null || avatarId <= 0 ? -1 : avatarId,
                phone, email, aboutMe, intro);
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
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

        handleParamsCheck(phone, email, request);
        int av = avatarId == null || avatarId <= 0 ? -1 : avatarId;
        boolean result = bloggerProfileService.updateBloggerProfile(bloggerId, av, phone, email, aboutMe, intro);
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

        boolean result = bloggerProfileService.deleteBloggerProfile(bloggerId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

    /**
     * 更新头像
     */
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public ResultBean updateAvatar(HttpServletRequest request,
                                   @PathVariable Integer bloggerId,
                                   @RequestParam(value = "avatarBaseUrlData") String base64urlData) {
        handleImageBase64Check(request, base64urlData);
        handleBloggerSignInCheck(request, bloggerId);

        // 保存图片
        String base = base64urlData.replaceFirst("^data:image/(png|jpg);base64,", "");
        byte[] bs = Base64.getDecoder().decode(base);
        int id = bloggerPictureService.insertPicture(bs, bloggerId, "once-avatar-" + bloggerId + ".png", "", BloggerPictureCategoryEnum.PUBLIC, "");
        if (id <= 0) handlerOperateFail(request);

        boolean res = bloggerProfileService.updateBloggerProfile(bloggerId, id, null, null, null, null);
        if (!res) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    private void handleImageBase64Check(HttpServletRequest request, String base64urlData) {

        if (!base64urlData.contains("data:image") || !base64urlData.contains("base64")) {
            throw exceptionManager.getParameterFormatIllegalException(new RequestContext(request));
        }

    }

    private void handleParamsCheck(String phone, String email, HttpServletRequest request) {
        RequestContext context = new RequestContext(request);
        if (phone != null && !StringUtils.isPhone(phone))
            throw exceptionManager.getParameterFormatIllegalException(context);

        if (email != null && !StringUtils.isEmail(email))
            throw exceptionManager.getParameterFormatIllegalException(context);
    }

}
