package com.duan.blogos.web.blog;

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

    @RequestMapping
    public ModelAndView pageLike(HttpServletRequest request,
                                 @ModelAttribute
                                 @PathVariable String bloggerName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blogger/setting");

        return mv;
    }

}
