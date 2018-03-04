package com.duan.blogos.web.api.blogger;

import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerLabelService;
import com.duan.blogos.util.StringUtils;
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
@RequestMapping("/blogger/{bloggerId}/label")
public class BloggerLabelController extends BaseBloggerController {

    @Autowired
    private BloggerLabelService bloggerLabelService;


    /**
     * 获取指定博主创建的标签
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BlogLabel>> list(HttpServletRequest request,
                                            @PathVariable Integer bloggerId,
                                            @RequestParam(value = "offset", required = false) Integer offset,
                                            @RequestParam(value = "rows", required = false) Integer rows) {
        handleAccountCheck(request, bloggerId);

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? bloggerProperties.getRequestBloggerBlogLabelCount() : rows;
        ResultBean<List<BlogLabel>> result = bloggerLabelService.listLabelByBlogger(bloggerId, os, rs);
        if (result == null) handlerEmptyResult(request);

        return result;
    }

    /**
     * 新增标签
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean add(HttpServletRequest request,
                          @PathVariable Integer bloggerId,
                          @RequestParam("title") String title) {

        handleBloggerSignInCheck(request, bloggerId);
        handleTitleCheck(title, request);

        int id = bloggerLabelService.insertLabel(bloggerId, title);
        if (id < 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 修改标签
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @PathVariable Integer labelId,
                             @RequestParam("title") String newTitle) {
        handleBloggerSignInCheck(request, bloggerId);
        handleTitleCheck(newTitle, request);

        boolean result = bloggerLabelService.updateLabel(labelId, bloggerId, newTitle);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

    /**
     * 删除标签
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable("labelId") Integer labelId,
                             @PathVariable Integer bloggerId) {
        handleAccountCheck(request, bloggerId);
        handleBloggerSignInCheck(request, bloggerId);

        boolean result = bloggerLabelService.deleteLabel(bloggerId, labelId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

    // 检查标题合法性
    private void handleTitleCheck(String title, HttpServletRequest request) {
        if (StringUtils.isEmpty(title))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
    }

}
