package com.duan.blogos.web.api.blogger;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blogger.CollectBlogListItemDTO;
import com.duan.blogos.common.BlogSortRule;
import com.duan.blogos.restful.ResultBean;
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

    /**
     * 收藏博文清单
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<CollectBlogListItemDTO>> list(HttpServletRequest request,
                                                        @PathVariable("bloggerId") Integer bloggerId,
                                                        @RequestParam(value = "offset", required = false) Integer offset,
                                                        @RequestParam(value = "rows", required = false) Integer rows,
                                                        @RequestParam(value = "sort", required = false) String sort,
                                                        @RequestParam(value = "order", required = false) String order) {
        final RequestContext context = new RequestContext(request);
        handleAccountCheck(request, bloggerId);

        //检查数据合法性
        String sor = sort == null ? Rule.VIEW_COUNT.name() : sort.toUpperCase();
        String ord = order == null ? Order.DESC.name() : order.toUpperCase();
        if (!Rule.contains(sor)) throw exceptionManager.getBlogSortRuleUndefinedException(context);
        if (!Order.contains(ord)) throw exceptionManager.getBlogSortOrderUndefinedException(context);

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? bloggerProperties.getRequestBloggerCollectCount() : rows;

        // 查询数据
        ResultBean<List<CollectBlogListItemDTO>> result = collectBlogService.listCollectBlog(bloggerId,
                bloggerProperties.getDefaultBlogCollectCategory(), os, rs,
                BlogSortRule.valueOf(sor, ord));
        if (result == null) handlerEmptyResult(request);

        return result;
    }

    /**
     * 修改博文收藏
     */
    @RequestMapping(value = "/{blogId}", method = RequestMethod.PUT)
    public ResultBean update(HttpServletRequest request,
                             @PathVariable("blogId") Integer blogId,
                             @PathVariable("bloggerId") Integer bloggerId,
                             @RequestParam(value = "reason", required = false) String newReason) {

        handleBloggerSignInCheck(request, bloggerId);

        if (StringUtils.isEmpty(newReason)) {
            throw exceptionManager.getParameterIllegalException(new RequestContext(request));
        }

        boolean result = collectBlogService.updateCollect(bloggerId, blogId, newReason, -1);
        if (!result) handlerOperateFail(request);

        return new ResultBean<>("");
    }

}
