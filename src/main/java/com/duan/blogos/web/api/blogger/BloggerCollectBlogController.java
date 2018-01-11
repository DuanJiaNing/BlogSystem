package com.duan.blogos.web.api.blogger;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blogger.CollectBlogListItemDTO;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.blog.CategoryService;
import com.duan.blogos.service.blogger.profile.CollectBlogService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/1/9.
 * 博主收藏博文
 * <p>
 * 1 收藏博文清单
 * 2 取消博文收藏
 * 3 修改博文收藏
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/collect")
public class BloggerCollectBlogController extends BaseBloggerController {

    @Autowired
    private CollectBlogService collectBlogService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 收藏博文清单
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<CollectBlogListItemDTO>> get(HttpServletRequest request,
                                                        @PathVariable("bloggerId") Integer bloggerId,
                                                        @RequestParam(value = "category", required = false) Integer category,
                                                        @RequestParam(value = "offset", required = false) Integer offset,
                                                        @RequestParam(value = "rows", required = false) Integer rows,
                                                        @RequestParam(value = "sort", required = false) String sort,
                                                        @RequestParam(value = "order", required = false) String order) {
        final RequestContext context = new RequestContext(request);
        handleAccountCheck(request, bloggerId);

        // 检查类别存在否
        if (category != null && !category.equals(bloggerPropertiesManager.getDefaultBlogCollectCategory()) &&
                categoryService.countCategoryForExistCheck(bloggerId, category) <= 0)
            throw exceptionManager.getUnknownCategoryException(context);

        //检查数据合法性
        String sor = sort == null ? Rule.VIEW_COUNT.name() : sort.toUpperCase();
        String ord = order == null ? Order.DESC.name() : order.toUpperCase();
        if (!Rule.contains(sor)) throw exceptionManager.getBlogSortRuleUndefinedException(context);
        if (!Order.contains(ord)) throw exceptionManager.getBlogSortOrderUndefinedException(context);

        int cid = category == null ? bloggerPropertiesManager.getDefaultBlogCollectCategory() : category;
        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? bloggerPropertiesManager.getRequestBloggerCollectCount() : rows;

        // 查询数据
        ResultBean<List<CollectBlogListItemDTO>> result = collectBlogService.listCollectBlog(bloggerId, cid, os, rs,
                BlogSortRule.valueOf(sor, ord));
        if (result == null) handlerEmptyResult(request);

        return result;
    }

    /**
     * 取消博文收藏
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.DELETE)
    public ResultBean cancel(HttpServletRequest request,
                             @PathVariable("bloggerId") Integer bloggerId,
                             @PathVariable("blogId") Integer blogId) {
        handleAccountCheck(request, bloggerId);

        boolean result = collectBlogService.deleteCollectBlog(bloggerId, blogId);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

    /**
     * 修改博文收藏
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable("blogId") Integer blogId,
                             @PathVariable("bloggerId") Integer bloggerId,
                             @RequestParam(value = "reason", required = false) String newReason,
                             @RequestParam(value = "category", required = false) Integer newCategory) {
        final RequestContext context = new RequestContext(request);

        if (StringUtils.isEmpty(newReason) && newCategory == null) {
            throw exceptionManager.getParameterIllegalException(context);
        }

        handleAccountCheck(request, bloggerId);

        // 检查类别存在否
        if (newCategory != null && !newCategory.equals(bloggerPropertiesManager.getDefaultBlogCollectCategory()) &&
                categoryService.countCategoryForExistCheck(bloggerId, newCategory) <= 0)
            throw exceptionManager.getUnknownCategoryException(context);

        boolean result = collectBlogService.updateCollect(bloggerId, blogId, newReason, newCategory);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

}
