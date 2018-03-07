package com.duan.blogos.web.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/3/7.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/read_blog")
public class BlogReadPageController {

    @RequestMapping
    public ModelAndView mainPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blogger/read_blog");

        return mv;
    }

}
