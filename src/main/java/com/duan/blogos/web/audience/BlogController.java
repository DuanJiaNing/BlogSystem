package com.duan.blogos.web.audience;

import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.service.audience.BlogBrowseService;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.service.blogger.BloggerAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/{bloggerName}")
public class BlogController {

    @Resource(name = "blogBrowseService")
    private BlogBrowseService browseService;

    @Resource(name = "blogRetrievalService")
    private BlogRetrievalService retrievalService;

    @Resource(name = "bloggerAccountService")
    private BloggerAccountService bloggerAccountService;

    @RequestMapping
    public String bloggerBlogList(@PathVariable("bloggerName")
                                  @ModelAttribute("bloggerName") String name) {
        BloggerAccount account = bloggerAccountService.getAccount(name);
        if (account == null) {
            return "404";
        }
        return "/audience/blogger_home";
    }


}
