package com.duan.blogos.service.blogger;

import com.duan.blogos.common.BlogSortRule;
import com.duan.blogos.dto.blogger.FavouriteBlogListItemDTO;
import com.duan.blogos.restful.ResultBean;

import java.util.List;

/**
 * Created on 2018/3/11.
 *
 * @author DuanJiaNing
 */
public interface BloggerLikeBlogService {

    /**
     * 检查博主是否喜欢过指定博文
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 已喜欢返回 true
     */
    boolean getLikeState(int bloggerId, int blogId);

    /**
     * 获取博主喜欢博文列表
     *
     * @param bloggerId    博主id
     * @param offset       偏移
     * @param rows         行数
     * @param blogSortRule 排序规则
     * @return 查询结果
     */
    ResultBean<List<FavouriteBlogListItemDTO>> listLikeBlog(int bloggerId, int offset, int rows,
                                                            BlogSortRule blogSortRule);

    /**
     * 统计喜欢量
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    int countByBloggerId(int bloggerId);
}
