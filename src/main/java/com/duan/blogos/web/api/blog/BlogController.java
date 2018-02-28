package com.duan.blogos.web.api.blog;

import com.duan.blogos.common.BlogSortRule;
import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.manager.properties.WebsiteProperties;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.audience.BlogBrowseService;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/2/4.
 * <p>
 * 1. 检索指定博主的博文列表
 * 2. 获得博文主体内容
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blog")
public class BlogController extends BaseBlogController {

    @Autowired
    private BlogRetrievalService retrievalService;

    @Autowired
    private BlogBrowseService blogBrowseService;

    @Autowired
    protected WebsiteProperties websiteProperties;

    /**
     * 检索指定博主的博文列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<BlogListItemDTO>> list(HttpServletRequest request,
                                                  @RequestParam("bloggerId") Integer bloggerId,
                                                  @RequestParam(value = "cids", required = false) String categoryIds,
                                                  @RequestParam(value = "lids", required = false) String labelIds,
                                                  @RequestParam(value = "kword", required = false) String keyWord,
                                                  @RequestParam(value = "offset", required = false) Integer offset,
                                                  @RequestParam(value = "rows", required = false) Integer rows,
                                                  @RequestParam(value = "sort", required = false) String sort,
                                                  @RequestParam(value = "order", required = false) String order) {
        handleAccountCheck(request, bloggerId);

        //检查数据合法性
        String sor = StringUtils.isEmpty_(sort) ? Rule.VIEW_COUNT.name() : sort.toUpperCase();
        String ord = StringUtils.isEmpty_(order) ? Order.DESC.name() : order.toUpperCase();
        handleSortRuleCheck(request, sor, ord);

        String ch = websiteProperties.getUrlConditionSplitCharacter();
        int[] cids = StringUtils.intStringDistinctToArray(categoryIds, ch);
        int[] lids = StringUtils.intStringDistinctToArray(labelIds, ch);
        //检查博文类别和标签
        handleCategoryAndLabelCheck(request, bloggerId, cids, lids);

        //执行数据查询
        BlogSortRule rule = new BlogSortRule(Rule.valueOf(sor), Order.valueOf(ord));
        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? audienceProperties.getRequestBloggerBlogListCount() : rows;
        ResultBean<List<BlogListItemDTO>> listResultBean = retrievalService.listFilterAll(cids, lids, keyWord, bloggerId,
                os, rs, rule, BlogStatusEnum.PUBLIC);

        if (listResultBean == null) handlerEmptyResult(request);

        return listResultBean;
    }

    /**
     * 获得检索结果数量，只有在发起一次检索后才能获得正确的值
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResultBean getCount() {
        return new ResultBean<>(retrievalService.getFilterCount());
    }

    /**
     * 获得博文主体内容
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public ResultBean<BlogMainContentDTO> get(HttpServletRequest request,
                                              @PathVariable Integer blogId) {
        handleBlogExistCheck(request, blogId);

        ResultBean<BlogMainContentDTO> mainContent = blogBrowseService.getBlogMainContent(blogId);
        if (mainContent == null) handlerEmptyResult(request);

        return mainContent;
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
