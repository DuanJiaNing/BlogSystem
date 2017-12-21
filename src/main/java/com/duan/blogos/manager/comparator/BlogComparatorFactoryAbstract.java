package com.duan.blogos.manager.comparator;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.exception.BlogSortRuleUndefinedException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/12/20.
 * 博文排序所需的比较器创建工厂，比较器名称定义与{@link Rule}中
 *
 * @author DuanJiaNing
 */
public abstract class BlogComparatorFactoryAbstract<T> {

    private Map<Rule, Comparator<T>> coms = null;

    Order order = Order.ASC;

    /**
     * 初始所有备用比较器
     */
    protected abstract void initFactory();

    public Comparator<T> get(Rule rule, Order order) {
        check(rule);

        if (coms == null) {
            coms = new HashMap<>();
            initFactory();
        }

        this.order = order;
        return coms.get(rule);
    }

    void add(Rule key, Comparator<T> comparator) {
        check(key);
        coms.put(key, comparator);
    }

    private static void check(Rule rule) {
        for (Rule r : Rule.values()) {
            if (r.equals(rule)) return;
        }
        throw new BlogSortRuleUndefinedException();
    }

}
