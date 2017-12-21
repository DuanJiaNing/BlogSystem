package com.duan.blogos.web.api.audience;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.exception.runtime.*;
import com.duan.blogos.manager.AudiencePropertiesManager;
import com.duan.blogos.manager.BlogSortRule;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.audience.BlogRetrievalService;
import com.duan.blogos.service.blogger.BloggerAccountService;
import com.duan.blogos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@ControllerAdvice
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private AudiencePropertiesManager audiencePropertiesManager;

    @Autowired
    private BlogRetrievalService retrievalService;

    @Autowired
    private BloggerAccountService bloggerAccountService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean<List<BlogListItemDTO>> bloggerBlogList(@RequestParam(value = "id", required = false) Integer id,
                                                             @RequestParam(value = "cids", required = false) String categoryIds,
                                                             @RequestParam(value = "lids", required = false) String labelIds,
                                                             @RequestParam(value = "kword", required = false) String keyWord,
                                                             @RequestParam(value = "offset", required = false) Integer offset,
                                                             @RequestParam(value = "rows", required = false) Integer rows,
                                                             @RequestParam(value = "sort", required = false) String sort,
                                                             @RequestParam(value = "order", required = false) String order) {

        //检查账户
        BloggerAccount account = checkAccount(id);

        //检查数据合法性
        String sor = sort == null ? Rule.VIEW_COUNT.name() : sort.toUpperCase();
        String ord = order == null ? Order.DESC.name() : order.toUpperCase();
        String ch = audiencePropertiesManager.getUrlConditionSplitCharacter();
        checkProperties(categoryIds, ch, labelIds, ch, sor, ord);

        //执行数据查询
        BlogSortRule rule = new BlogSortRule(Rule.valueOf(sor), Order.valueOf(ord));

        int[] cids = StringUtils.intStringToArray(categoryIds, ch);
        int[] lids = StringUtils.intStringToArray(labelIds, ch);

        int os = offset == null || offset < 0 ? 0 : offset;
        int rs = rows == null || rows < 0 ? audiencePropertiesManager.getRequestBloggerBlogListCount() : rows;
        return retrievalService.listFilterAll(cids, lids, keyWord, account.getId(), os, rs, rule);
    }

    /*
     * 检查输入
     */
    private void checkProperties(String categoryIds, String cr, String labelIds, String lr, String sort, String order) {
        if (categoryIds != null && !StringUtils.isIntStringSplitByChar(categoryIds, cr)) {
            throw new StringSplitException("数字字符串未按指定字符间隔");
        }

        if (labelIds != null && !StringUtils.isIntStringSplitByChar(labelIds, lr)) {
            throw new StringSplitException("数字字符串未按指定字符间隔");
        }

        if (sort != null && !Rule.contains(sort)) {
            throw new BlogSortRuleUndefinedException("博文排序规则未定义");
        }

        if (order != null && !Order.contains(order)) {
            throw new BlogSortOrderUndefinedException("排序顺序未定义");
        }
    }

    /*
     * 检查博主是否存在
     */
    private BloggerAccount checkAccount(Integer id) {
        BloggerAccount account;
        if (id == null || (account = bloggerAccountService.getAccount(id)) == null) {
            throw new UnknownBloggerException("博主不存在");
        }
        return account;
    }

    /*
     * 统一处理异常
     */
    @ExceptionHandler(BaseRuntimeException.class)
    @ResponseBody
    public ResultBean exceptionHandler(Throwable e) {
        return new ResultBean(e);
    }

}
