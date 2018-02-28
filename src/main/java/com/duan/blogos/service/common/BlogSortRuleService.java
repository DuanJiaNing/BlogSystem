package com.duan.blogos.service.common;

import com.duan.blogos.dto.blog.BlogSortRuleDTO;
import com.duan.blogos.restful.ResultBean;

import java.util.List;

/**
 * Created on 2018/2/12.
 *
 * @author DuanJiaNing
 */
public interface BlogSortRuleService {

    /**
     * 获得所有的排序规则
     *
     * @return 结果
     */
    ResultBean<List<BlogSortRuleDTO>> listSortRule();

    /**
     * 获得排序顺序
     *
     * @return 结果
     */
    ResultBean<List<BlogSortRuleDTO>> listSortOrder();

}
