package com.duan.blogos.web.api.blogger;

import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.audience.BlogOperateService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/25.
 * 读者对博文可以进行的操作
 * <p>
 * 1 分享博文
 * 2 收藏博文
 * 3 投诉博文
 * 4 喜欢博文
 * 5 取消收藏
 * 6 取消喜欢
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/{blogId}")
public class BloggerOperateController extends BaseBloggerController {

    @Autowired
    private BlogOperateService operateService;

    /**
     * 分享博文
     */
    @RequestMapping(value = "/operate=share", method = RequestMethod.POST)
    public ResultBean shareBlog(HttpServletRequest request,
                                @PathVariable Integer blogId,
                                @PathVariable Integer bloggerId) {
        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);

        //执行
        int count = operateService.insertShare(blogId, bloggerId);

        return new ResultBean<>(count);
    }

    /**
     * 收藏博文
     */
    @RequestMapping(value = "/operate=collect", method = RequestMethod.POST)
    public ResultBean collectBlog(HttpServletRequest request,
                                  @PathVariable Integer blogId,
                                  @PathVariable Integer bloggerId,
                                  @RequestParam(value = "reason", required = false) String reason) {

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);

        // 如果博文属于当前博主，收藏失败d
        if (blogValidateManager.isCreatorOfBlog(bloggerId, blogId)) {
            handlerOperateFail(request);
        }

        //执行
        // UPDATE: 2018/1/19 更新 收藏到自己的某一类别不开发，只收藏到一个类别中
        int id = operateService.insertCollect(blogId, bloggerId, reason,
                bloggerProperties.getDefaultBlogCollectCategory());
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 投诉博文
     */
    @RequestMapping(value = "/operate=complain", method = RequestMethod.POST)
    public ResultBean complainBlog(HttpServletRequest request,
                                   @PathVariable Integer blogId,
                                   @PathVariable Integer bloggerId,
                                   @RequestParam("content") String content) {
        if (StringUtils.isEmpty_(content))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);

        //执行
        int id = operateService.insertComplain(blogId, bloggerId, content);
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 喜欢博文
     */
    @RequestMapping(value = "/operate=like", method = RequestMethod.POST)
    public ResultBean likeBlog(HttpServletRequest request,
                               @PathVariable Integer blogId,
                               @PathVariable Integer bloggerId) {

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);

        //执行
        int count = operateService.insertLike(blogId, bloggerId);

        return new ResultBean<>(count);
    }

    /**
     * 取消收藏
     */
    @RequestMapping(value = "/operate=collect", method = RequestMethod.DELETE)
    public ResultBean removeCollect(HttpServletRequest request,
                                    @PathVariable Integer blogId,
                                    @PathVariable Integer bloggerId) {

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);

        //执行
        boolean result = operateService.deleteCollect(bloggerId, blogId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");

    }

    /**
     * 取消喜欢
     */
    @RequestMapping(value = "/operate=like", method = RequestMethod.DELETE)
    public ResultBean removeLike(HttpServletRequest request,
                                 @PathVariable Integer blogId,
                                 @PathVariable Integer bloggerId) {
        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);

        //执行
        boolean result = operateService.deleteLike(bloggerId, blogId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

}
