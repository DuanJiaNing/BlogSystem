package com.duan.blogos.manager.comparator;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/12/20.
 * 博文排序所需的比较器创建工厂，比较器名称定义于{@link Rule}中
 *
 * @author DuanJiaNing
 */
public abstract class BlogComparatorFactoryAbstract<T> {

    private Map<Rule, Comparator<T>> coms = new HashMap<>();
    Order order = Order.ASC;

    BlogComparatorFactoryAbstract() {
        initFactory();
    }

    /**
     * 初始所有备用比较器
     */
    protected abstract void initFactory();

    public Comparator<T> get(Rule rule, Order order) {
        this.order = order;
        return coms.get(rule);
    }

    void add(Rule key, Comparator<T> comparator) {
        coms.put(key, comparator);
    }

}
