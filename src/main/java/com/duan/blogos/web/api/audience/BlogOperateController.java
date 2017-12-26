package com.duan.blogos.web.api.audience;

import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.ExceptionManager;
import com.duan.blogos.manager.validate.BlogValidateManager;
import com.duan.blogos.manager.validate.BloggerValidateManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogOperateService;
import com.duan.blogos.util.StringUtils;
import com.duan.blogos.web.api.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/12/25.
 * 读者对博文可以进行的操作
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blog/operate")
public class BlogOperateController extends BaseBlogController {

    @Autowired
    private BlogOperateService operateService;

    /**
     * 评论博文
     */
    @RequestMapping("/comment")
    public ResultBean commentBlog(HttpServletRequest request,
                                  @RequestParam("blogId") Integer blogId,
                                  @RequestParam("spokesmanId") Integer spokesmanId,
                                  @RequestParam("listenerId") Integer listenerId,
                                  @RequestParam("content") String content) {
        RequestContext context = new RequestContext(request);

        //检查
        BaseRuntimeException exception = check(context, blogId, spokesmanId, listenerId);
        if (exception != null) throw exception;
        if (StringUtils.isEmpty(content)) throw exceptionManager.getParameterIllegalException(context);

        //执行操作
        int id = operateService.insertComment(blogId, spokesmanId, listenerId, content);
        if (id <= 0) throw exceptionManager.getOperateFailException(context);

        return new ResultBean<>(id);
    }

    /**
     * 分享博文
     */
    @RequestMapping("/share")
    public ResultBean shareBlog(HttpServletRequest request,
                                @RequestParam("blogId") Integer blogId,
                                @RequestParam("sharerId") Integer sharerId) {
        RequestContext context = new RequestContext(request);

        //检查
        BaseRuntimeException exception = check(context, blogId, sharerId);
        if (exception != null) throw exception;

        //执行
        int count = operateService.insertShare(blogId, sharerId);

        return new ResultBean<>(count);
    }

    /**
     * 赞赏博文
     */
    @RequestMapping("/admire")
    public ResultBean admireBlog(HttpServletRequest request,
                                 @RequestParam("blogId") Integer blogId,
                                 @RequestParam("paierId") Integer paierId,
                                 @RequestParam("money") Float money) {
        RequestContext context = new RequestContext(request);

        //检查
        BaseRuntimeException exception = check(context, blogId, paierId);
        if (exception != null) throw exception;
        if (Math.min(money, 0f) == money) throw exceptionManager.getParameterIllegalException(context);

        //执行
        int id = operateService.insertAdmire(blogId, paierId, money);
        if (id <= 0) throw exceptionManager.getOperateFailException(context);

        return new ResultBean<>(id);
    }

    /**
     * 收藏博文
     */
    @RequestMapping("/collect")
    public ResultBean collectBlog(HttpServletRequest request,
                                  @RequestParam("blogId") Integer blogId,
                                  @RequestParam("collectorId") Integer collectorId,
                                  @RequestParam("reason") String reason,
                                  @RequestParam("categoryId") Integer categoryId) {
        RequestContext context = new RequestContext(request);

        // 检查
        BaseRuntimeException exception = check(context, blogId, collectorId);
        if (exception != null) throw exception;
        if (StringUtils.isEmpty(reason) || !bloggerValidateManager.checkBloggerCategoryExist(collectorId, categoryId)) {
            throw exceptionManager.getParameterIllegalException(context);
        }

        //执行
        int id = operateService.insertCollect(blogId, collectorId, reason, categoryId);
        if (id <= 0) throw exceptionManager.getOperateFailException(context);

        return new ResultBean<>(id);
    }

    /**
     * 投诉博文
     */
    @RequestMapping("/complain")
    public ResultBean complainBlog(HttpServletRequest request,
                                   @RequestParam("blogId") Integer blogId,
                                   @RequestParam("complainId") Integer complainId,
                                   @RequestParam("content") String content) {
        RequestContext context = new RequestContext(request);

        //检查
        BaseRuntimeException exception = check(context, blogId, complainId);
        if (exception != null) throw exception;
        if (StringUtils.isEmpty(content)) throw exceptionManager.getParameterIllegalException(context);

        //执行
        int id = operateService.insertComplain(blogId, complainId, content);
        if (id <= 0) throw exceptionManager.getOperateFailException(context);

        return new ResultBean<>(id);
    }

    /**
     * 取消收藏
     */
    @RequestMapping("/collect/remove")
    public ResultBean removeCollect(HttpServletRequest request,
                                    @RequestParam("bloggerId") Integer bloggerId,
                                    @RequestParam("blogId") Integer blogId) {
        RequestContext context = new RequestContext(request);

        //检查
        BaseRuntimeException exception = check(context, blogId, bloggerId);
        if (exception != null) throw exception;

        //执行
        operateService.deleteCollect(bloggerId, blogId);


        return null;
    }


    public ResultBean removeLike(HttpServletRequest request,
                                 @RequestParam("bloggerId") Integer bloggerId,
                                 @RequestParam("blogId") Integer blogId) {

        RequestContext context = new RequestContext(request);
        return null;
    }


    @ExceptionHandler
    public ResultBean handlerException(RuntimeException e) {

        return null;
    }


}
