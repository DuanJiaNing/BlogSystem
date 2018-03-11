package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blog.BlogLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/27.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogLikeDao extends BaseDao<BlogLike> {

    /**
     * 根据博主和博文删除喜欢记录
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 操作影响的行数
     */
    int deleteLikeByBloggerId(@Param("bloggerId") int bloggerId, @Param("blogId") int blogId);

    /**
     * 根据博文id获得所有喜欢记录
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    List<BlogLike> listAllLikeByBlogId(int blogId);

    /**
     * 统计指定博主给出喜欢的次数
     *
     * @param bloggerId 博主id
     * @return 数量
     */
    Integer countLikeByLikerId(int bloggerId);

    /**
     * 根据博主id和博文id获取记录
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 查询记录
     */
    BlogLike getLike(@Param("bloggerId") int bloggerId, @Param("blogId") int blogId);
}
