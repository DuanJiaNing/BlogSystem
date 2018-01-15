package com.duan.blogos.web.api.blogger;

import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//        if (!blogValidateManager.verifyBlog(title, content))
//            throw exceptionManager.getOperateFailException(request);

        // 新增博文，博文审核过程跳过
        int id = blogService.insertBlog(bloggerId, null, null, BlogStatusEnum.PUBLIC, title, content,
                summary,
                null);

        return null;
    }

}
