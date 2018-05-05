package com.duan.blogos.web.blog;

import com.duan.blogos.dto.blogger.BloggerStatisticsDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerBlogService;
import com.duan.blogos.service.blogger.BloggerStatisticsService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/2/13.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/edit_blog")
public class EditBlogPageController {

    @Autowired
    private BloggerValidateManager bloggerValidateManager;

    @Autowired
    private BloggerBlogService blogService;

    @Autowired
    private BloggerStatisticsService statisticsService;

    @RequestMapping
    public ModelAndView mainPage(HttpServletRequest request,
                                 @RequestParam(value = "bid", required = false) Integer bloggerId,
                                 @RequestParam(value = "blogId", required = false) Integer blogId) {
        ModelAndView mv = new ModelAndView();

        if (bloggerId == null || !bloggerValidateManager.checkBloggerSignIn(request, bloggerId)) {
            return new ModelAndView("redirect:/login");
        } else {
            if (blogId != null) {
                ResultBean<Blog> blog = blogService.getBlog(bloggerId, blogId);
                Blog data = blog.getData();
                mv.addObject("blogId", blogId);
                mv.addObject("categoryId", data.getCategoryIds());
                mv.addObject("labelIds", data.getLabelIds());
                mv.addObject("blogTitle", data.getTitle());
                mv.addObject("blogSummary", data.getSummary());
                if (data.getState().equals(BlogStatusEnum.PRIVATE.getCode())) {
                    mv.addObject("blogIsPrivate", true);
                }
                mv.addObject("blogContentMd", StringUtils.stringToUnicode(data.getContentMd()));
            }

            ResultBean<BloggerStatisticsDTO> loginBgStat = statisticsService.getBloggerStatistics(bloggerId);
            mv.addObject("loginBgStat", loginBgStat.getData());

            mv.setViewName("/blogger/edit_blog");
        }

        return mv;
    }

}
