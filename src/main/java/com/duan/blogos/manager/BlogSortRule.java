package com.duan.blogos.manager;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;

/**
 * Created on 2017/12/16.
 * 博文排序规则定义
 *
 * @author DuanJiaNing
 */
public class BlogSortRule {

    /*
     * 排序依据
     */
    private final Rule rule;

    /*
     * 升序或降序
     */
    private final Order order;

    public BlogSortRule(Rule rule, Order order) {
        this.rule = rule;
        this.order = order;
    }

    public Rule getRule() {
        return rule;
    }

    public Order getOrder() {
        return order;
    }

    public static BlogSortRule valueOf(String ruleName, String orderName) {
        Rule rule = Rule.valueOf(ruleName);
        Order order = Order.valueOf(orderName);
        return new BlogSortRule(rule, order);
    }

}
