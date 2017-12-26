package com.duan.blogos.web.api.audience;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blog.BlogCommentDTO;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.dto.blog.BlogStatisticsDTO;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.exception.BaseRuntimeException;
import com.duan.blogos.exception.UnknownBloggerException;
import com.duan.blogos.manager.AudiencePropertiesManager;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogBrowseService;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2017/12/19.
 * 读者对博文数据的获取api
 * <p>
 * tip：RequestParam的required为true，而请求时没有该属性会返回404错误
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blog/get")
public class BlogDataRetrievalController extends BaseBlogController {

    @Autowired
    private BlogRetrievalService retrievalService;

    @Autowired
    private BlogBrowseService blogBrowseService;

    @Autowired
    private AudiencePropertiesManager audiencePropertiesManager;

    /**
     * 检索博文
     * 文档见 doc/wiki/audience/博主博文检索.md
     * 查询时博文状态调用者无法指定，只能查询 {@link BlogStatusEnum#PUBLIC}的
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultBean<List<BlogListItemDTO>> blogList(HttpServletRequest request,
                                                      @RequestParam("bloggerId") Integer bloggerId,
                                                      @RequestParam(value = "cids", required = false) String categoryIds,
                                                      @RequestParam(value = "lids", required = false) String labelIds,
                                                      @RequestParam(value = "kword", required = false) String keyWord,
                                                      @RequestParam(value = "offset", required = false) Integer offset,
                                                      @RequestParam(value = "rows", required = false) Integer rows,
                                                      @RequestParam(value = "sort", required = false) String sort,
                                                      @RequestParam(value = "order", required = false) String order) {
        final RequestContext context = new RequestContext(request);

        //检查账户
        UnknownBloggerException exception = checkAccount(context, bloggerId);
        if (exception != null) throw exception;

        //检查数据合法性
        String sor = sort == null ? Rule.VIEW_COUNT.name() : sort.toUpperCase();
        String ord = order == null ? Order.DESC.name() : order.toUpperCase();
        String ch = audiencePropertiesManager.getUrlConditionSplitCharacter();
        checkProperties(categoryIds, ch, labelIds, ch, sor, ord, context);

        //执行数据查询
        BlogSortRule rule = new BlogSortRule(Rule.valueOf(sor), Order.valueOf(ord));

        int[] cids = StringUtils.intStringDistinctToArray(categoryIds, ch);
        int[] lids = StringUtils.intStringDistinctToArray(labelIds, ch);

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? audiencePropertiesManager.getRequestBloggerBlogListCount() : rows;
        ResultBean<List<BlogListItemDTO>> listResultBean = retrievalService.listFilterAll(cids, lids, keyWord, bloggerId, os, rs, rule);

        if (listResultBean == null) handlerEmptyResult(context);

        return listResultBean;
    }

    /*
     * 检查输入
     */
    private void checkProperties(String categoryIds, String cr, String labelIds, String lr,
                                 String sort, String order, RequestContext context) {

        if (categoryIds != null && !StringUtils.isIntStringSplitByChar(categoryIds, cr)) {
            throw exceptionManager.getStringSplitException(context);
        }

        if (labelIds != null && !StringUtils.isIntStringSplitByChar(labelIds, lr)) {
            throw exceptionManager.getStringSplitException(context);
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
     * 文档见 doc/wiki/audience/博文主体内容.md
     */
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public ResultBean<BlogMainContentDTO> blogMainContent(HttpServletRequest request,
                                                          @Param("blogId") Integer blogId) {
        final RequestContext context = new RequestContext(request);

        //检查博文是否存在
        BaseRuntimeException exception = checkBlogExist(context, blogId);
        if (exception != null) throw exception;

        ResultBean<BlogMainContentDTO> mainContent = blogBrowseService.getBlogMainContent(blogId);
        if (mainContent == null) handlerEmptyResult(context);

        return mainContent;
    }

    /**
     * 获得博文评论列表
     * 文档见 doc/wiki/audience/博文评论列表.md
     */
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ResultBean<List<BlogCommentDTO>> blogComment(HttpServletRequest request,
                                                        @Param("blogId") Integer blogId,
                                                        @RequestParam(value = "offset", required = false) Integer offset,
                                                        @RequestParam(value = "rows", required = false) Integer rows) {
        final RequestContext context = new RequestContext(request);

        //检查博文是否存在
        BaseRuntimeException exception = checkBlogExist(context, blogId);
        if (exception != null) throw exception;

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? audiencePropertiesManager.getRequestBloggerBlogCommentCount() : rows;
        ResultBean<List<BlogCommentDTO>> resultBean = blogBrowseService.listBlogComment(blogId, os, rs);
        if (resultBean == null) handlerEmptyResult(context);

        return resultBean;
    }

    /**
     * 获得博文统计信息
     * 文档见 doc/wiki/audience/博文统计信息.md
     */
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResultBean<BlogStatisticsDTO> blogStatistics(HttpServletRequest request,
                                                        @Param("blogId") Integer blogId) {
        final RequestContext context = new RequestContext(request);

        //检查博文是否存在
        BaseRuntimeException exception = checkBlogExist(context, blogId);
        if (exception != null) throw exception;

        ResultBean<BlogStatisticsDTO> statistics = blogBrowseService.getBlogStatistics(blogId);
        if (statistics == null) handlerEmptyResult(context);

        return statistics;
    }

}
