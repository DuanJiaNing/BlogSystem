package com.duan.blogos.web.blog;

import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.dto.blog.BlogStatisticsCountDTO;
import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.exception.api.blogger.UnknownBloggerException;
import com.duan.blogos.manager.BloggerSessionManager;
import com.duan.blogos.manager.properties.BloggerProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.audience.BlogBrowseService;
import com.duan.blogos.service.blogger.*;
import com.duan.blogos.service.common.BlogStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/{bloggerName}/blog/{blogName}")
public class BlogReadPageController {

    @Autowired
    private BloggerAccountService accountService;

    @Autowired
    private BloggerBlogService blogService;

    @Autowired
    private BlogStatisticsService statisticsService;

    @Autowired
    private BloggerStatisticsService bloggerStatisticsService;

    @Autowired
    private BloggerProperties bloggerProperties;

    @Autowired
    private BloggerSessionManager sessionManager;

    @Autowired
    private BlogBrowseService blogBrowseService;

    @Autowired
    private BloggerLikeBlogService likeService;

    @Autowired
    private BloggerCollectBlogService collectBlogService;

    @RequestMapping
    public ModelAndView page(HttpServletRequest request,
                             @PathVariable String bloggerName,
                             @PathVariable String blogName) {
        ModelAndView mv = new ModelAndView();

        // 博文作者博主账户
        BloggerAccount account = accountService.getAccount(bloggerName);

        if (account == null) {
            request.setAttribute("code", UnknownBloggerException.code);
            mv.setViewName("/blogger/register");
            return mv;
        }

        int blogId = blogService.getBlogId(account.getId(), blogName);
        if (blogId == -1) {
            mv.setViewName("error/error");
            mv.addObject("code", 5);
            mv.addObject(bloggerProperties.getSessionNameOfErrorMsg(), "博文不存在！");
            return mv;
        }

        // 博文浏览次数自增1
        statisticsService.updateBlogViewCountPlus(blogId);

        ResultBean<BlogMainContentDTO> mainContent = blogBrowseService.getBlogMainContent(blogId);
        ResultBean<BlogStatisticsCountDTO> statistics = statisticsService.getBlogStatisticsCount(blogId);

        mv.addObject("blogOwnerBloggerId", account.getId());
        mv.addObject("main", mainContent.getData());
        mv.addObject("stat", statistics.getData());

        // 登陆博主 id
        int loginBloggerId = sessionManager.getLoginBloggerId(request);

        ResultBean<BloggerStatisticsDTO> loginBgStat = bloggerStatisticsService.getBloggerStatistics(loginBloggerId);
        mv.addObject("loginBgStat", loginBgStat.getData());

        if (loginBloggerId != -1) {
            if (likeService.getLikeState(loginBloggerId, blogId))
                mv.addObject("likeState", true);
            if (collectBlogService.getCollectState(loginBloggerId, blogId))
                mv.addObject("collectState", true);
        }

        mv.setViewName("blogger/read_blog");
        return mv;
    }

}
