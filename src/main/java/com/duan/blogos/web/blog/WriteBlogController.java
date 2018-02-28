package com.duan.blogos.web.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/2/13.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/write_blog")
public class WriteBlogController {

    @RequestMapping
    public ModelAndView mainPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("blogger/write_blog");
        return mv;
    }

}
