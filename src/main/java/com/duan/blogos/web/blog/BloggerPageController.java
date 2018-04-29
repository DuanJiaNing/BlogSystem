package com.duan.blogos.web.blog;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.entity.blogger.BloggerSetting;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.exception.api.blogger.UnknownBloggerException;
import com.duan.blogos.manager.BloggerSessionManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created on 2018/2/5.
 * 博主主页
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/{bloggerName}")
public class BloggerPageController {

    @Autowired
    private BloggerAccountService accountService;

    @Autowired
    private BloggerProfileService bloggerProfileService;

    @Autowired
    private BloggerStatisticsService statisticsService;

    @Autowired
    private BloggerPictureService bloggerPictureService;

    @Autowired
    private BloggerProperties bloggerProperties;

    @Autowired
    private BloggerSessionManager sessionManager;

    @Autowired
    private BloggerSettingService settingService;

    @RequestMapping("/archives")
    public ModelAndView mainPage(HttpServletRequest request,
                                 @PathVariable String bloggerName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blogger/main");

        BloggerAccount account = accountService.getAccount(bloggerName);
        if (account == null) {
            request.setAttribute("code", UnknownBloggerException.code);
            mv.setViewName("/blogger/register");
            return mv;
        }

        mv.addObject(bloggerProperties.getNameOfPageOwnerBloggerId(), account.getId());
        mv.addObject(bloggerProperties.getNameOfPageOwnerBloggerName(), account.getUsername());

        int ownerId = account.getId();
        BloggerProfile profile = bloggerProfileService.getBloggerProfile(ownerId);
        mv.addObject("blogName", profile.getIntro());
        mv.addObject("aboutMe", profile.getAboutMe());
        mv.addObject("avatarId",
                Optional.ofNullable(profile.getAvatarId())
                        .orElse(bloggerPictureService
                                .getDefaultPicture(BloggerPictureCategoryEnum.DEFAULT_BLOGGER_AVATAR)
                                .getId()));


        ResultBean<BloggerStatisticsDTO> ownerBgStat = statisticsService.getBloggerStatistics(ownerId);
        mv.addObject("ownerBgStat", ownerBgStat.getData());

        int loginBgId;
        if ((loginBgId = sessionManager.getLoginBloggerId(request)) != -1) {
            ResultBean<BloggerStatisticsDTO> loginBgStat = statisticsService.getBloggerStatistics(loginBgId);
            mv.addObject("loginBgStat", loginBgStat.getData());
        }

        BloggerSetting setting = settingService.getSetting(ownerId);
        mv.addObject("setting", setting);

        return mv;
    }

}
