package com.duan.blogos.manager.comparator;

import com.duan.blogos.common.Order;
import com.duan.blogos.common.Rule;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.entity.blog.BlogStatistics;

import java.util.Comparator;

/**
 * Created on 2017/12/20.
 * 获取博主博文列表时排序规则的比较器创建工厂
 *
 * @author DuanJiaNing
 */
public class BlogListItemComparatorFactory extends BlogComparatorFactoryAbstract<BlogStatistics> {

    @Override
    protected void initFactory() {

        // Comparator 前 < 后 return 正数 -> 升序

        add(Rule.COMMENT_COUNT, (o1, o2) -> {
            if (order == Order.ASC) {
                return Integer.compare(o1.getCommentCount(), o2.getCommentCount());
            } else {
                return -Integer.compare(o1.getCommentCount(), o2.getCommentCount());
            }
        });

        add(Rule.VIEW_COUNT, (o1, o2) -> {
            if (order == Order.ASC) {
                return Integer.compare(o1.getViewCount(), o2.getViewCount());
            } else {
                return -Integer.compare(o1.getViewCount(), o2.getViewCount());
            }
        });

        add(Rule.COLLECT_COUNT, (o1, o2) -> {
            if (order == Order.ASC) {
                return Integer.compare(o1.getCollectCount(), o2.getCollectCount());
            } else {
                return -Integer.compare(o1.getCollectCount(), o2.getCollectCount());
            }
        });

        add(Rule.LIKE_COUNT, (o1, o2) -> {
            if (order == Order.ASC) {
                return Integer.compare(o1.getLikeCount(), o2.getLikeCount());
            } else {
                return -Integer.compare(o1.getLikeCount(), o2.getLikeCount());
            }
        });

        add(Rule.RELEASE_DATE, (o1, o2) -> {
            if (order == Order.ASC) {
                return o1.getReleaseDate().compareTo(o2.getReleaseDate());
            } else {
                return -o1.getReleaseDate().compareTo(o2.getReleaseDate());
            }
        });

    }

}
