package com.duan.blogos.web.api.blogger;

import com.duan.blogos.dto.blogger.BloggerLinkDTO;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerLinkService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2017/12/28.
 * 博主友情链接api
 * <p>
 * 1 获取链接
 * 2 新增链接
 * 3 更新链接
 * 4 删除链接
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/link")
public class BloggerLinkController extends BaseBloggerController {

    @Autowired
    private BloggerLinkService bloggerLinkService;

    /**
     * 获取链接
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BloggerLinkDTO>> get(HttpServletRequest request,
                                                @PathVariable Integer bloggerId,
                                                @RequestParam(value = "offset", required = false) Integer offset,
                                                @RequestParam(value = "rows", required = false) Integer rows) {

        handleAccountCheck(request, bloggerId);

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? bloggerProperties.getRequestBloggerLinkCount() : rows;
        ResultBean<List<BloggerLinkDTO>> result = bloggerLinkService.listBloggerLink(bloggerId, os, rs);
        if (result == null) handlerEmptyResult(request);

        return result;
    }

    /**
     * 新增链接
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean add(HttpServletRequest request,
                          @PathVariable Integer bloggerId,
                          @RequestParam(value = "iconId", required = false) Integer iconId,
                          @RequestParam("title") String title,
                          @RequestParam("url") String url,
                          @RequestParam(value = "bewrite", required = false) String bewrite) {
        handleBloggerSignInCheck(request, bloggerId);
        handlePictureExistCheck(request, bloggerId, iconId);

        //检查title和url规范
        if (StringUtils.isEmpty(title) || !StringUtils.isURL(url))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        int id = bloggerLinkService.insertBloggerLink(bloggerId, iconId == null ? -1 : iconId, title, url, bewrite);
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 更新链接
     */
    @RequestMapping(value = "/{linkId}", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @PathVariable Integer linkId,
                             @RequestParam(value = "iconId", required = false) Integer newIconId,
                             @RequestParam(value = "title", required = false) String newTitle,
                             @RequestParam(value = "url", required = false) String newUrl,
                             @RequestParam(value = "bewrite", required = false) String newBewrite) {
        RequestContext context = new RequestContext(request);

        //都为null则无需更新
        if (newIconId == null && newTitle == null && newUrl == null && newBewrite == null) {
            throw exceptionManager.getParameterIllegalException(context);
        }

        handleBloggerSignInCheck(request, bloggerId);
        handlePictureExistCheck(request, bloggerId, newIconId);
        checkLinkExist(linkId, context);

        //检查url规范
        if (newUrl != null && !StringUtils.isURL(newUrl)) {
            throw exceptionManager.getParameterIllegalException(context);
        }

        boolean result = bloggerLinkService.updateBloggerLink(linkId, newIconId == null ? -1 : newIconId, newTitle, newUrl, newBewrite);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

    /**
     * 删除链接
     */
    @RequestMapping(value = "/{linkId}", method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @PathVariable Integer linkId) {
        handleBloggerSignInCheck(request, bloggerId);
        RequestContext context = new RequestContext(request);
        checkLinkExist(linkId, context);

        boolean result = bloggerLinkService.deleteBloggerLink(linkId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }


    //检查链接是否存在
    private void checkLinkExist(Integer linkId, RequestContext context) {
        if (linkId == null || linkId <= 0 || !bloggerLinkService.getLinkForCheckExist(linkId)) {
            throw exceptionManager.getUnknownLinkException(context);
        }
    }

}
