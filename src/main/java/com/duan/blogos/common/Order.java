package com.duan.blogos.common;

/**
 * Created on 2017/12/16.
 * 排序顺序
 *
 * @author DuanJiaNing
 */
public enum Order {

    /**
     * 升序
     */
    ASC("升序"),

    /**
     * 降序
     */
    DESC("降序");

    private final String title;

    Order(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }

    public static boolean contains(String name) {
        for (Order order : values()) {
            if (order.name().equals(name)) return true;
        }

        return false;
    }

}
