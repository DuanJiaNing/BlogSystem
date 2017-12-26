package com.duan.blogos.web.api.audience;

import com.duan.blogos.manager.AudiencePropertiesManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/25.
 * 读者对博文可以进行的操作
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blog/operate")
public class BlogOperateController {

    @Autowired
    private AudiencePropertiesManager propertiesManager;

    @Autowired
    private BlogOperateService operateService;

    @RequestMapping("/comment")
    public ResultBean commentBlog(HttpServletRequest request,
                                  @RequestParam("blogId") Integer blogId,
                                  @RequestParam("spokesmanId") Integer spokesmanId,
                                  @RequestParam("listenerId") Integer listenerId,
                                  @RequestParam("content") String content) {

        return null;
    }

    @RequestMapping("/share")
    public ResultBean shareBlog(HttpServletRequest request,
                                @RequestParam("blogId") Integer blogId,
                                @RequestParam("sharerId") Integer sharerId) {

        return null;
    }

    @RequestMapping("/admire")
    public ResultBean admireBlog(HttpServletRequest request,
                                 @RequestParam("blogId") Integer blogId,
                                 @RequestParam("paierId") Integer paierId,
                                 @RequestParam("money") Float money) {

        return null;
    }

    @RequestMapping("/collect")
    public ResultBean collectBlog(HttpServletRequest request,
                                  @RequestParam("blogId") Integer blogId,
                                  @RequestParam("collectorId") Integer collectorId,
                                  @RequestParam("reason") String reason,
                                  @RequestParam("categoryId") Integer categoryId) {

        return null;
    }

    @RequestMapping("/complain")
    public ResultBean complainBlog(HttpServletRequest request,
                                   @RequestParam("blogId") Integer blogId,
                                   @RequestParam("complainId") Integer complainId,
                                   @RequestParam("content") String content) {

        return null;
    }

    public ResultBean removeCollect(HttpServletRequest request,
                                    @RequestParam("likerId") Integer likerId,
                                    @RequestParam("blogId") Integer blogId) {

        return null;
    }

    public ResultBean removeLike(HttpServletRequest request,
                                 @RequestParam("bloggerId") Integer bloggerId,
                                 @RequestParam("blogId") Integer blogId) {

        return null;
    }

}
