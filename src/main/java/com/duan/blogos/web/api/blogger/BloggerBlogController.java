package com.duan.blogos.web.api.blogger;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.BlogService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/1/15.
 * 博主博文api
 * <p>
 * 1 新增博文
 * 2 获取博文
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
                          @RequestParam(value = "keywords", required = false) String keyWords) {

        // 检查不能为null的参数是否为null
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content) || StringUtils.isEmpty(summary))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogContentCheck(request, title, content, summary, keyWords);

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

    /**
     * 检索博文
     */
    // TODO 待测试
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BlogListItemDTO>> blogList(HttpServletRequest request,
                                                      @PathVariable Integer bloggerId,
                                                      @RequestParam(value = "cids", required = false) String categoryIds,
                                                      @RequestParam(value = "lids", required = false) String labelIds,
                                                      @RequestParam(value = "kword", required = false) String keyWord,
                                                      @RequestParam(value = "offset", required = false) Integer offset,
                                                      @RequestParam(value = "rows", required = false) Integer rows,
                                                      @RequestParam(value = "sort", required = false) String sort,
                                                      @RequestParam(value = "order", required = false) String order,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        handleBloggerSignInCheck(request, bloggerId);

        //检查排序规则
        String sor = sort == null ? Rule.VIEW_COUNT.name() : sort.toUpperCase();
        String ord = order == null ? Order.DESC.name() : order.toUpperCase();
        handleSortRuleCheck(request, sort, ord);

        String sp = websitePropertiesManager.getUrlConditionSplitCharacter();
        int[] cids = StringUtils.intStringDistinctToArray(categoryIds, sp);
        int[] lids = StringUtils.intStringDistinctToArray(labelIds, sp);

        BlogStatusEnum stat = null;
        if (status != null) stat = BlogStatusEnum.valueOf(status);
        if (stat == null) stat = BlogStatusEnum.PUBLIC; // status传参错误

        //执行数据查询
        BlogSortRule rule = new BlogSortRule(Rule.valueOf(sor), Order.valueOf(ord));
        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? bloggerPropertiesManager.getRequestBlogListCount() : rows;
        ResultBean<List<BlogListItemDTO>> listResultBean = blogService.listFilterAll(cids, lids, keyWord, bloggerId,
                os, rs, rule, stat);
        if (listResultBean == null) handlerEmptyResult(request);

        return listResultBean;
    }

    /**
     * 获取指定博文
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public ResultBean<Blog> get(HttpServletRequest request,
                                @PathVariable Integer bloggerId,
                                @PathVariable Integer blogId) {
        handleBloggerSignInCheck(request, bloggerId);

        ResultBean<Blog> blog = blogService.getBlog(bloggerId, blogId);
        if (blog == null) handlerEmptyResult(request);

        return blog;
    }

    /**
     * 更新博文
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.PUT)
    public ResultBean update() {

        return null;
    }

    /**
     * 删除博文
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.DELETE)
    public ResultBean delete() {

        return null;
    }


    // 检查类别和标签
    private void handleCategoryAndLabelCheck(HttpServletRequest request, int bloggerId, int[] cids, int[] lids) {

        if (!CollectionUtils.isEmpty(cids) && !bloggerValidateManager.checkBloggerBlogCategoryExist(bloggerId, cids)
                || (!CollectionUtils.isEmpty(lids) && !blogValidateManager.checkLabelsExist(lids)))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
    }

    //博文内容审核
    private void handleBlogContentCheck(HttpServletRequest request, String title, String content, String summary,
                                        String keyWords) {
        if (!blogValidateManager.verifyBlog(title, content, summary, keyWords))
            throw exceptionManager.getBlogIllegalException(new RequestContext(request));

    }

    // 检查排序规则
    private void handleSortRuleCheck(HttpServletRequest request, String sort, String order) {

        if (sort != null && !Rule.contains(sort)) {
            throw exceptionManager.getBlogSortRuleUndefinedException(new RequestContext(request));
        }

        if (order != null && !Order.contains(order)) {
            throw exceptionManager.getBlogSortOrderUndefinedException(new RequestContext(request));
        }
    }

}
