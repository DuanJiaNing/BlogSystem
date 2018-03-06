package com.duan.blogos.web.blog;

import com.duan.blogos.manager.validate.BloggerValidateManager;
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
public class EditBlogController {

    @Autowired
    private BloggerValidateManager bloggerValidateManager;

    @RequestMapping
    public ModelAndView mainPage(HttpServletRequest request,
                                 @RequestParam(value = "bid", required = false) Integer bloggerId) {
        ModelAndView mv = new ModelAndView();

        if (bloggerId == null || !bloggerValidateManager.checkBloggerSignIn(request, bloggerId)) {
            mv.setViewName("/error/error");
            mv.addObject("errorMsg", "请先登录");
        } else {
            mv.setViewName("/blogger/edit_blog");
        }

        return mv;
    }

}
