package com.duan.blogos.web.api.blogger;

import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.BlogService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/1/15.
 * 博主博文api
 * <p>
 * 1 新增博文
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/blog")
public class BloggerBlogController extends BaseBloggerController {

    @Autowired
    private BlogService blogService;

    /**
     * 新增博文
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean add(HttpServletRequest request,
                          @PathVariable Integer bloggerId,
                          @RequestParam(value = "cids", required = false) String categoryIds,
                          @RequestParam(value = "lids", required = false) String labelIds,
                          @RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("summary") String summary,
                          @RequestParam(value = "keyWords", required = false) String keyWords) {

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogContentCheck(request, title, summary, content, keyWords);

        String sp = websitePropertiesManager.getUrlConditionSplitCharacter();
        int[] cids = StringUtils.intStringDistinctToArray(categoryIds, sp);
        int[] lids = StringUtils.intStringDistinctToArray(labelIds, sp);

        //检查博文类别和标签
        handleCategoryAndLabelCheck(request, bloggerId, cids, lids);

        int id = blogService.insertBlog(bloggerId, cids, lids, BlogStatusEnum.PUBLIC, title, content,
                summary, StringUtils.stringArrayToArray(keyWords, sp));
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    // 检查类别和标签
    private void handleCategoryAndLabelCheck(HttpServletRequest request, Integer bloggerId, int[] cids, int[] lids) {
        if (bloggerValidateManager.checkBloggerBlogCategoryExist(bloggerId, cids) ||
                blogValidateManager.checkLabelsExist(lids)) {
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
        }
    }

    private void handleBlogContentCheck(HttpServletRequest request, String keyWords, String title, String summary,
                                        String content) {
        //博文内容自动审核
        if (!blogValidateManager.verifyBlog(title, content, summary, keyWords))
            throw exceptionManager.getBlogIllegalException(new RequestContext(request));

    }

}
