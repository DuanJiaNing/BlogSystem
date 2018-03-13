package com.duan.blogos.web.api.blogger;

import com.duan.blogos.manager.validate.BlogCommentValidateManager;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerCommentService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

import static com.duan.blogos.enums.BlogCommentStatusEnum.RIGHTFUL;

/**
 * Created on 2018/3/13.
 *
 * @author DuanJiaNing
 */

@RestController
@RequestMapping("/blogger/{bloggerId}/comment")
public class BloggerCommentController extends BaseBloggerController {

    @Autowired
    private BlogCommentValidateManager commentValidateManager;

    @Autowired
    private BloggerCommentService commentService;

    /**
     * 新增评论
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean add(HttpServletRequest request,
                          @PathVariable Integer bloggerId,
                          @RequestParam("blogId") Integer blogId,
                          @RequestParam("content") String content,
                          @RequestParam("listenerId") Integer listenerId) {
        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);
        handleBloggerExist(request, listenerId);

        if (StringUtils.isEmpty_(content) || !commentValidateManager.checkCommentContent(content))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        int id = commentService.insertComment(blogId, bloggerId, listenerId, RIGHTFUL.getCode(), content);
        if (id < 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 删除评论
     */
    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @RequestParam("blogId") Integer blogId,
                             @PathVariable Integer bloggerId,
                             @PathVariable Integer commentId) {
        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistCheck(request, blogId);

        if (!commentService.deleteComment(commentId, blogId))
            handlerOperateFail(request);

        return new ResultBean<>("");
    }

    private void handleBloggerExist(HttpServletRequest request, Integer bloggerId) {
        if (!bloggerValidateManager.checkAccountExist(bloggerId)) {
            throw exceptionManager.getUnknownBloggerException(new RequestContext(request));
        }
    }

}
