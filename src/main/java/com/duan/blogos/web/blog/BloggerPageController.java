package com.duan.blogos.web.blog;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.BloggerSessionManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import com.duan.blogos.service.blogger.BloggerPictureService;
import com.duan.blogos.service.blogger.BloggerProfileService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.session.mgt.SessionManager;
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

    @RequestMapping("/archives")
    public ModelAndView mainPage(HttpServletRequest request,
                                 @PathVariable String bloggerName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blogger/main");

        BloggerAccount account = accountService.getAccount(bloggerName);
        if (account == null) {
            mv.addObject("code", 6);
            mv.addObject(bloggerProperties.getSessionNameOfErrorMsg(), "博主不存在！");
            mv.setViewName("error/error");
            return mv;
        }

        mv.addObject(bloggerProperties.getNameOfPageOwnerBloggerId(), account.getId());
        mv.addObject(bloggerProperties.getNameOfPageOwnerBloggerName(), account.getUsername());

        int id = account.getId();
        BloggerProfile profile = bloggerProfileService.getBloggerProfile(id);
        mv.addObject("blogName", profile.getIntro());
        mv.addObject("aboutMe", profile.getAboutMe());
        mv.addObject("avatarId",
                Optional.ofNullable(profile.getAvatarId())
                        .orElse(bloggerPictureService
                                .getDefaultPicture(BloggerPictureCategoryEnum.DEFAULT_BLOGGER_AVATAR)
                                .getId()));


        ResultBean<BloggerStatisticsDTO> ownerBgStat = statisticsService.getBloggerStatistics(account.getId());
        mv.addObject("ownerBgStat", ownerBgStat.getData());

        int loginBgId;
        if ((loginBgId = sessionManager.getLoginBloggerId(request)) != -1) {
            ResultBean<BloggerStatisticsDTO> loginBgStat = statisticsService.getBloggerStatistics(loginBgId);
            mv.addObject("loginBgStat", loginBgStat.getData());
        }

        return mv;
    }

}
