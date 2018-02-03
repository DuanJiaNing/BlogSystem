package com.duan.blogos.service.audience;

import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.BlogFilter;

import java.util.List;

/**
 * Created on 2017/12/14.
 * 博文检索并排序服务
 * <p>
 * 1 全限定检索
 * 2 关键字检索
 * 3 类别检索
 * 4 标签检索
 *
 * @author DuanJiaNing
 */
public interface BlogRetrievalService extends BlogFilter<ResultBean<List<BlogListItemDTO>>> {

}
