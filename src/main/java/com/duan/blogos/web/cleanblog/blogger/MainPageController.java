package com.duan.blogos.web.cleanblog.blogger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2018/2/5.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/{bloggerName}")
public class MainPageController {

    @RequestMapping
    public ModelAndView mainPage(@PathVariable String bloggerName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blogger/main");
        mv.addObject("bloggerId", 12);
        mv.addObject("bloggerName", bloggerName);

        return mv;
    }

}
