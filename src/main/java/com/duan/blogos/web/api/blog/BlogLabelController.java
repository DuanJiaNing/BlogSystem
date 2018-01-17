package com.duan.blogos.web.api.blog;

import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blog.BlogLabelService;
import com.duan.blogos.util.StringUtils;
import com.duan.blogos.web.api.audience.BaseBlogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/1/12.
 * 博文标签API，标签的使用不限定博主，即只要标签存在，任何博主都可以使用
 * <p>
 * 1 查看所有标签
 * 2 查看指定标签
 * 3 修改标签
 * 4 删除标签
 * 5 增加标签
 * 6 获取指定博主创建的标签
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blog/label")
public class BlogLabelController extends BaseBlogController {

    @Autowired
    private BlogLabelService blogLabelService;

    /**
     * 查看所有标签
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BlogLabel>> get(HttpServletRequest request,
                                           @RequestParam(value = "offset", required = false) Integer offset,
                                           @RequestParam(value = "rows", required = false) Integer rows) {

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? blogPropertiesManager.getRequestBloggerBlogLabelCount() : rows;
        ResultBean<List<BlogLabel>> resultBean = blogLabelService.listLabel(os, rs);
        if (resultBean == null) handlerEmptyResult(request);

        return resultBean;
    }


    /**
     * 获取指定标签
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public ResultBean<BlogLabel> getLabel(HttpServletRequest request, @PathVariable("labelId") Integer labelId) {

        BlogLabel label = blogLabelService.getLabel(labelId);
        if (label == null) handlerEmptyResult(request);

        return new ResultBean<>(label);
    }

    /**
     * 获取指定博主创建的标签
     */
    @RequestMapping(value = "/blogger", method = RequestMethod.GET)
    public ResultBean<List<BlogLabel>> getLabelWithBlogger(HttpServletRequest request,
                                                           @RequestParam("bloggerId") Integer bloggerId,
                                                           @RequestParam(value = "offset", required = false) Integer offset,
                                                           @RequestParam(value = "rows", required = false) Integer rows) {
        handleAccountCheck(request, bloggerId);

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? blogPropertiesManager.getRequestBloggerBlogLabelCount() : rows;
        ResultBean<List<BlogLabel>> result = blogLabelService.listLabelByBlogger(bloggerId, os, rs);
        if (result == null) handlerEmptyResult(request);

        return result;
    }

    /**
     * 新增标签
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean add(HttpServletRequest request,
                          @RequestParam("bloggerId") Integer bloggerId,
                          @RequestParam("title") String title) {

        handleBloggerSignInCheck(request, bloggerId);
        handleTitleCheck(title, request);

        int id = blogLabelService.insertLabel(bloggerId, title);
        if (id < 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    // 检查标题合法性
    private void handleTitleCheck(String title, HttpServletRequest request) {
        if (StringUtils.isEmpty(title))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
    }

    // 检查博主是否登录
    private void handleBloggerSignInCheck(HttpServletRequest request, Integer bloggerId) {
        handleAccountCheck(request, bloggerId);

        if (!bloggerValidateManager.checkBloggerSignIn(request, bloggerId))
            throw exceptionManager.getBloggerNotLoggedInException(new RequestContext(request));
    }

    /**
     * 修改标签
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable("labelId") Integer labelId,
                             @RequestParam("bloggerId") Integer bloggerId,
                             @RequestParam("title") String newTitle) {
        handleBloggerSignInCheck(request, bloggerId);
        handleTitleCheck(newTitle, request);

        boolean result = blogLabelService.updateLabel(labelId, bloggerId, newTitle);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

    /**
     * 删除标签
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable("labelId") Integer labelId,
                             @RequestParam("bloggerId") Integer bloggerId) {
        handleAccountCheck(request, bloggerId);
        handleBloggerSignInCheck(request, bloggerId);

        boolean result = blogLabelService.deleteLabel(bloggerId, labelId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }


}
