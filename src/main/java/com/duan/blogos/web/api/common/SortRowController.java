package com.duan.blogos.web.api.common;

import com.duan.blogos.dto.blog.BlogSortRuleDTO;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.common.BlogSortRuleService;
import com.duan.blogos.web.api.BaseCheckController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/2/12.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/sort-rule")
public class SortRowController extends BaseCheckController {

    @Autowired
    private BlogSortRuleService sortRuleService;

    /**
     * 获得排序规则
     */
    @RequestMapping(value = "/rule", method = RequestMethod.GET)
    public ResultBean<List<BlogSortRuleDTO>> list(HttpServletRequest request) {

        ResultBean<List<BlogSortRuleDTO>> result = sortRuleService.listSortRule();
        if (result == null) handlerEmptyResult(request);

        return result;
    }

    /**1
     * 获得排序顺序
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResultBean<List<BlogSortRuleDTO>> listOrder(HttpServletRequest request) {

        ResultBean<List<BlogSortRuleDTO>> result = sortRuleService.listSortOrder();
        if (result == null) handlerEmptyResult(request);

        return result;
    }
}
