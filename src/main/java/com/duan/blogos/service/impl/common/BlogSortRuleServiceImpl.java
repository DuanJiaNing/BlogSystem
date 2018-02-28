package com.duan.blogos.service.impl.common;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blog.BlogSortRuleDTO;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.common.BlogSortRuleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018/2/12.
 *
 * @author DuanJiaNing
 */
@Service
public class BlogSortRuleServiceImpl implements BlogSortRuleService {

    @Override
    public ResultBean<List<BlogSortRuleDTO>> listSortRule() {

        List<BlogSortRuleDTO> list = new ArrayList<>();
        Arrays.stream(Rule.values()).forEach(rule -> list.add(new BlogSortRuleDTO(rule.name().toLowerCase(), rule.title())));

        return new ResultBean<>(list);
    }

    @Override
    public ResultBean<List<BlogSortRuleDTO>> listSortOrder() {

        List<BlogSortRuleDTO> list = new ArrayList<>();
        Arrays.stream(Order.values()).forEach(order -> list.add(new BlogSortRuleDTO(order.name().toLowerCase(), order.title())));

        return new ResultBean<>(list);
    }
}
