package com.duan.blogos.web.api.blogger;

import com.duan.blogos.common.BlogSortRule;
import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blogger.FavouriteBlogListItemDTO;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.blogger.BloggerCollectBlogService;
import com.duan.blogos.service.blogger.BloggerLikeBlogService;
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
@RequestMapping("/blogger/{bloggerId}/like")
public class BloggerLikeBlogController extends BaseBloggerController {

    @Autowired
    private BloggerLikeBlogService likeBlogService;

    /**
     * 收藏博文清单
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<List<FavouriteBlogListItemDTO>> list(HttpServletRequest request,
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
        ResultBean<List<FavouriteBlogListItemDTO>> result = likeBlogService.listLikeBlog(bloggerId, os, rs,
                BlogSortRule.valueOf(sor, ord));
        if (result == null) handlerEmptyResult(request);

        return result;
    }


    /**
     * 统计收藏收藏量
     */
    @RequestMapping("/count")
    public ResultBean count(HttpServletRequest request,
                            @PathVariable("bloggerId") Integer bloggerId) {

        handleAccountCheck(request, bloggerId);

        return new ResultBean<>(likeBlogService.countByBloggerId(bloggerId));
    }
}
