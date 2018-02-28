package com.duan.blogos.web.api.blogger;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.common.BlogSortRule;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerBlogService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created on 2018/1/15.
 * 博主博文api
 * <p>
 * 1 新增博文
 * 2 获取博文
 * 3 获取指定博文
 * 4 修改博文
 * 5 删除博文
 * 6 批量删除博文
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/blog")
public class BloggerBlogController extends BaseBloggerController {

    @Autowired
    private BloggerBlogService bloggerBlogService;

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
        if (StringUtils.isEmpty_(title) || StringUtils.isEmpty_(content) || StringUtils.isEmpty_(summary))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogContentCheck(request, title, content, summary, keyWords);

        String sp = websiteProperties.getUrlConditionSplitCharacter();
        int[] cids = StringUtils.intStringDistinctToArray(categoryIds, sp);
        int[] lids = StringUtils.intStringDistinctToArray(labelIds, sp);

        //检查博文类别和标签
        handleCategoryAndLabelCheck(request, bloggerId, cids, lids);

        String[] kw = StringUtils.stringArrayToArray(keyWords, sp);
        // UPDATE: 2018/1/16 更新 博文审核
        int id = bloggerBlogService.insertBlog(bloggerId, cids, lids, BlogStatusEnum.PUBLIC, title, content, summary, kw);
        if (id <= 0) handlerOperateFail(request);

        return new ResultBean<>(id);
    }

    /**
     * 检索博文
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BlogListItemDTO>> list(HttpServletRequest request,
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
        handleSortRuleCheck(request, sor, ord);

        String sp = websiteProperties.getUrlConditionSplitCharacter();
        int[] cids = StringUtils.intStringDistinctToArray(categoryIds, sp);
        int[] lids = StringUtils.intStringDistinctToArray(labelIds, sp);
        //检查博文类别和标签
        handleCategoryAndLabelCheck(request, bloggerId, cids, lids);

        BlogStatusEnum stat = null;
        if (status != null) stat = BlogStatusEnum.valueOf(status);
        if (stat == null) stat = BlogStatusEnum.PUBLIC; // status传参错误

        //执行数据查询
        BlogSortRule rule = new BlogSortRule(Rule.valueOf(sor), Order.valueOf(ord));
        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? bloggerProperties.getRequestBlogListCount() : rows;
        ResultBean<List<BlogListItemDTO>> listResultBean = bloggerBlogService.listFilterAll(cids, lids, keyWord, bloggerId,
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

        ResultBean<Blog> blog = bloggerBlogService.getBlog(bloggerId, blogId);
        if (blog == null) handlerEmptyResult(request);

        return blog;
    }

    /**
     * 更新博文
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @PathVariable Integer blogId,
                             @RequestParam(value = "title", required = false) String newTitle,
                             @RequestParam(value = "content", required = false) String newContent,
                             @RequestParam(value = "summary", required = false) String newSummary,
                             @RequestParam(value = "cids", required = false) String newCategoryIds,
                             @RequestParam(value = "lids", required = false) String newLabelIds,
                             @RequestParam(value = "kword", required = false) String newKeyWord,
                             @RequestParam(value = "status", required = false) Integer newStatus) {

        // 所有参数都为null，则不更新。
        if (Stream.of(newTitle, newContent, newSummary, newCategoryIds, newLabelIds, newKeyWord, newStatus)
                .filter(Objects::nonNull).count() <= 0)
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        // 检查修改到的博文状态是否允许
        if (newStatus != null && !blogValidateManager.isBlogStatusAllow(newStatus))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistAndCreatorCheck(request, bloggerId, blogId);
        handleBlogContentCheck(request, newTitle, newContent, newSummary, newKeyWord);

        String sp = websiteProperties.getUrlConditionSplitCharacter();
        int[] cids = newCategoryIds == null ? null : StringUtils.intStringDistinctToArray(newCategoryIds, sp);
        int[] lids = newLabelIds == null ? null : StringUtils.intStringDistinctToArray(newLabelIds, sp);

        //检查博文类别和标签
        handleCategoryAndLabelCheck(request, bloggerId, cids, lids);

        String[] kw = newKeyWord == null ? null : StringUtils.stringArrayToArray(newKeyWord, sp);
        BlogStatusEnum stat = newStatus == null ? null : BlogStatusEnum.valueOf(newStatus);

        //执行更新
        if (!bloggerBlogService.updateBlog(bloggerId, blogId, cids, lids, stat, newTitle, newContent, newSummary, kw))
            handlerOperateFail(request);

        return new ResultBean<>("");
    }

    /**
     * 删除博文
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.DELETE)
    public ResultBean delete(HttpServletRequest request,
                             @PathVariable Integer bloggerId,
                             @PathVariable Integer blogId) {

        handleBloggerSignInCheck(request, bloggerId);
        handleBlogExistAndCreatorCheck(request, bloggerId, blogId);

        if (!bloggerBlogService.deleteBlog(bloggerId, blogId))
            handlerOperateFail(request);

        return new ResultBean<>("");
    }

    /**
     * 批量删除博文
     */
    @RequestMapping(value = "/patch", method = RequestMethod.DELETE)
    public ResultBean deletePatch(HttpServletRequest request,
                                  @PathVariable Integer bloggerId,
                                  @RequestParam("ids") String ids) {

        handleBloggerSignInCheck(request, bloggerId);

        int[] blogIds = StringUtils.intStringDistinctToArray(ids,
                websiteProperties.getUrlConditionSplitCharacter());
        if (CollectionUtils.isEmpty(blogIds))
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));

        for (int id : blogIds) {
            handleBlogExistAndCreatorCheck(request, bloggerId, id);
        }

        if (!bloggerBlogService.deleteBlogPatch(bloggerId, blogIds))
            handlerOperateFail(request);

        return new ResultBean<>("");
    }

    // 检查博文是否存在，且博文是否属于指定博主
    private void handleBlogExistAndCreatorCheck(HttpServletRequest request, int bloggerId, int blogId) {
        if (!blogValidateManager.isCreatorOfBlog(bloggerId, blogId))
            throw exceptionManager.getUnknownBlogException(new RequestContext(request));
    }

    // 检查类别和标签
    private void handleCategoryAndLabelCheck(HttpServletRequest request, int bloggerId, int[] cids, int[] lids) {

        if (!CollectionUtils.isEmpty(cids)) {
            for (int id : cids) {
                if (!bloggerValidateManager.checkBloggerBlogCategoryExist(bloggerId, id))
                    throw exceptionManager.getParameterIllegalException(new RequestContext(request));
            }
        }

        if (!CollectionUtils.isEmpty(lids)) {
            for (int id : lids) {
                if (!blogValidateManager.checkLabelsExist(id))
                    throw exceptionManager.getParameterIllegalException(new RequestContext(request));
            }
        }

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
