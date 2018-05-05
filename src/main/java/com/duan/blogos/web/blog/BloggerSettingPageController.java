package com.duan.blogos.web.blog;

import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.entity.blogger.BloggerSetting;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.exception.api.blogger.BloggerNotLoggedInException;
import com.duan.blogos.exception.api.blogger.UnknownBloggerException;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.service.blogger.BloggerPictureService;
import com.duan.blogos.service.blogger.BloggerProfileService;
import com.duan.blogos.service.blogger.BloggerSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/3/7.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/{bloggerName}/setting")
public class BloggerSettingPageController {

    @Autowired
    private BloggerAccountService accountService;

    @Autowired
    private BloggerProfileService profileService;

    @Autowired
    private BloggerValidateManager bloggerValidateManager;

    @Autowired
    private BloggerPictureService pictureService;

    @Autowired
    private BloggerSettingService settingService;

    @RequestMapping
    public ModelAndView pageSetting(HttpServletRequest request,
                                    @ModelAttribute
                                    @PathVariable String bloggerName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blogger/setting");

        BloggerAccount account = accountService.getAccount(bloggerName);
        int bloggerId;
        if (account == null) {
            request.setAttribute("code", UnknownBloggerException.code);
            mv.setViewName("/blogger/register");
            return mv;
        } else if (!bloggerValidateManager.checkBloggerSignIn(request, bloggerId = account.getId())) {
            return new ModelAndView("redirect:/login");
        }

        BloggerProfile profile = profileService.getBloggerProfile(bloggerId);
        if (profile.getAvatarId() == null) {
            BloggerPicture picture = pictureService.getDefaultPicture(BloggerPictureCategoryEnum.DEFAULT_BLOGGER_AVATAR);
            profile.setAvatarId(picture.getId());
        }
        mv.addObject("profile", profile);

        BloggerSetting setting = settingService.getSetting(bloggerId);
        mv.addObject("setting", setting);

        return mv;
    }

}
