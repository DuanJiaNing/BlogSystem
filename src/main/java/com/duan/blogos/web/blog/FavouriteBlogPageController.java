package com.duan.blogos.web.blog;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.manager.BloggerSessionManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
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
@RequestMapping("/{pageOwnerBloggerName}/blog/favourite")
public class FavouriteBlogPageController {

    @Autowired
    private BloggerAccountService accountService;

    @Autowired
    private BloggerSessionManager sessionManager;

    @Autowired
    private BloggerProperties bloggerProperties;

    @Autowired
    private BloggerStatisticsService statisticsService;

    @RequestMapping("/like")
    public ModelAndView pageLike(HttpServletRequest request,
                                 @ModelAttribute
                                 @PathVariable String pageOwnerBloggerName) {
        ModelAndView mv = new ModelAndView();
        setCommon(mv, request, pageOwnerBloggerName);

        mv.addObject("type", "like");
        return mv;
    }

    @RequestMapping("/collect")
    public ModelAndView pageCollect(HttpServletRequest request,
                                    @ModelAttribute
                                    @PathVariable String pageOwnerBloggerName) {
        ModelAndView mv = new ModelAndView();
        setCommon(mv, request, pageOwnerBloggerName);

        mv.addObject("type", "collect");
        return mv;
    }

    private void setCommon(ModelAndView mv, HttpServletRequest request, String bloggerName) {
        mv.setViewName("/blogger/favourite_blog");

        // 登陆博主 id
        int loginBloggerId = sessionManager.getLoginBloggerId(request);
        ResultBean<BloggerStatisticsDTO> loginBgStat = statisticsService.getBloggerStatistics(loginBloggerId);
        mv.addObject("loginBgStat", loginBgStat.getData());

        BloggerAccount account = accountService.getAccount(bloggerName);
        mv.addObject(bloggerProperties.getNameOfPageOwnerBloggerId(), account.getId());
        mv.addObject(bloggerProperties.getNameOfPageOwnerBloggerName(), account.getUsername());

    }

}
