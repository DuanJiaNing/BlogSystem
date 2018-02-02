package com.duan.blogos.web.api.audience;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blog.BlogCommentDTO;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.manager.AudiencePropertiesManager;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogBrowseService;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2017/12/19.
 * 读者对博文数据的获取api
 * <p>
 * 1 检索指定博主的博文列表
 * 2 获得博文主体内容
 * 3 获得博文评论列表
 * 4 获得博文统计信息
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blog")
public class BlogDataRetrievalController extends BaseBlogController {

    @Autowired
    protected AudiencePropertiesManager audiencePropertiesManager;

    @Autowired
    private BlogRetrievalService retrievalService;

    @Autowired
    private BlogBrowseService blogBrowseService;

    /**
     * 检索指定博主的博文列表
     */
    @RequestMapping(value = "/{bloggerId}", method = RequestMethod.GET)
    public ResultBean<List<BlogListItemDTO>> blogList(HttpServletRequest request,
                                                      @PathVariable Integer bloggerId,
                                                      @RequestParam(value = "cids", required = false) String categoryIds,
                                                      @RequestParam(value = "lids", required = false) String labelIds,
                                                      @RequestParam(value = "kword", required = false) String keyWord,
                                                      @RequestParam(value = "offset", required = false) Integer offset,
                                                      @RequestParam(value = "rows", required = false) Integer rows,
                                                      @RequestParam(value = "sort", required = false) String sort,
                                                      @RequestParam(value = "order", required = false) String order) {
        final RequestContext context = new RequestContext(request);

        //检查账户
        BaseRuntimeException exception = checkAccount(context, bloggerId);
        if (exception != null) throw exception;

        //检查数据合法性
        String sor = sort == null ? Rule.VIEW_COUNT.name() : sort.toUpperCase();
        String ord = order == null ? Order.DESC.name() : order.toUpperCase();
        String ch = websitePropertiesManager.getUrlConditionSplitCharacter();
        checkProperties(categoryIds, ch, labelIds, ch, sor, ord, context);

        //执行数据查询
        BlogSortRule rule = new BlogSortRule(Rule.valueOf(sor), Order.valueOf(ord));

        int[] cids = StringUtils.intStringDistinctToArray(categoryIds, ch);
        int[] lids = StringUtils.intStringDistinctToArray(labelIds, ch);

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? audiencePropertiesManager.getRequestBloggerBlogListCount() : rows;
        ResultBean<List<BlogListItemDTO>> listResultBean = retrievalService.listFilterAll(cids, lids, keyWord, bloggerId,
                os, rs, rule, BlogStatusEnum.PUBLIC);

        if (listResultBean == null) handlerEmptyResult(request);

        return listResultBean;
    }

    /*
     * 检查输入
     */
    private void checkProperties(String categoryIds, String cr, String labelIds, String lr,
                                 String sort, String order, RequestContext context) {

        if (categoryIds != null && !StringUtils.isIntStringSplitByChar(categoryIds, cr)) {
            throw exceptionManager.getParameterStringSplitException(context);
        }

        if (labelIds != null && !StringUtils.isIntStringSplitByChar(labelIds, lr)) {
            throw exceptionManager.getParameterStringSplitException(context);
        }

        if (sort != null && !Rule.contains(sort)) {
            throw exceptionManager.getBlogSortRuleUndefinedException(context);
        }

        if (order != null && !Order.contains(order)) {
            throw exceptionManager.getBlogSortOrderUndefinedException(context);
        }
    }

    /**
     * 获得博文主体内容
     */
    @RequestMapping(value = "/{blogId}/content", method = RequestMethod.GET)
    public ResultBean<BlogMainContentDTO> blogMainContent(HttpServletRequest request,
                                                          @PathVariable Integer blogId) {
        final RequestContext context = new RequestContext(request);

        //检查博文是否存在
        BaseRuntimeException exception = checkBlogExist(context, blogId);
        if (exception != null) throw exception;

        ResultBean<BlogMainContentDTO> mainContent = blogBrowseService.getBlogMainContent(blogId);
        if (mainContent == null) handlerEmptyResult(request);

        return mainContent;
    }

    /**
     * 获得博文评论列表
     */
    @RequestMapping(value = "/{blogId}/comment", method = RequestMethod.GET)
    public ResultBean<List<BlogCommentDTO>> blogComment(HttpServletRequest request,
                                                        @PathVariable Integer blogId,
                                                        @RequestParam(value = "offset", required = false) Integer offset,
                                                        @RequestParam(value = "rows", required = false) Integer rows) {
        final RequestContext context = new RequestContext(request);

        //检查博文是否存在
        BaseRuntimeException exception = checkBlogExist(context, blogId);
        if (exception != null) throw exception;

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? audiencePropertiesManager.getRequestBloggerBlogCommentCount() : rows;
        ResultBean<List<BlogCommentDTO>> resultBean = blogBrowseService.listBlogComment(blogId, os, rs);
        if (resultBean == null) handlerEmptyResult(request);

        return resultBean;
    }

}
