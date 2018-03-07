package com.duan.blogos.web.blog;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import com.duan.blogos.service.blogger.BloggerPictureService;
import com.duan.blogos.service.blogger.BloggerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created on 2018/2/5.
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

    @RequestMapping("/archives")
    public ModelAndView mainPage(@PathVariable String bloggerName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blogger/main");

        BloggerAccount account = accountService.getAccount(bloggerName);
        if (account == null) {
            mv.setViewName("error/unknown_blogger");
            mv.addObject(bloggerProperties.getSessionNameOfErrorMsg(), "博主不存在！");
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

        ResultBean<BloggerStatisticsDTO> statistics = statisticsService.getBloggerStatistics(account.getId());
        mv.addObject("statistics", statistics.getData());

        return mv;
    }

}
