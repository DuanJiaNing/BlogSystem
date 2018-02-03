package com.duan.blogos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created on 2017/12/16.
 * 博文排序规则
 *
 * @author DuanJiaNing
 */
@Getter
@AllArgsConstructor
public class BlogSortRule {

    /**
     * 排序依据
     */
    private final Rule rule;

    /**
     * 升序或降序
     */
    private final Order order;

    public static BlogSortRule valueOf(String ruleName, String orderName) {
        Rule rule = Rule.valueOf(ruleName);
        Order order = Order.valueOf(orderName);
        return new BlogSortRule(rule, order);
    }

}
