package com.duan.blogos.web.api.audience;

import com.duan.blogos.manager.AudiencePropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2017/12/25.
 * 读者对博文可以进行的操作
 *
 * @author DuanJiaNing
 */
// TODO
@Controller
@RequestMapping("/blog/operate")
public class BlogOperateController {

    @Autowired
    private AudiencePropertiesManager propertiesManager;

    @RequestMapping("/comment")
    public void commentBlog() {
    }

    @RequestMapping("/share")
    public void shareBlog() {
    }

    @RequestMapping("/admire")
    public void admireBlog() {
    }

    @RequestMapping("/collect")
    public void collectBlog() {
    }

    @RequestMapping("/complain")
    public void complainBlog() {
    }

}
