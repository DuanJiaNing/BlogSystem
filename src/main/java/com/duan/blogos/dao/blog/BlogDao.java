package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.BlogStatus;
import com.duan.blogos.entity.blog.Blog;
import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/12.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogDao extends BaseDao<Blog> {


    /**
     * 根据博主id和文章状态查询其发布的文章
     *
     * @param bloggerId 博主id
     * @param status    博文状态
     * @return 查询到的文章
     * @see BlogStatus
     */
    List<Blog> queryBlog(@Param("bloggerId") int bloggerId,
                         @Param("status") int status);

    /**
     * 根据博主id和文章状态查询其发布的指定偏移位置和数量的博文
     *
     * @param bloggerId 博主id
     * @param status    博文状态
     * @param offset    偏移位置
     * @param rows      行数
     * @return
     * @see BlogStatus
     */
    List<Blog> queryBlogWithLimit(@Param("bloggerId") int bloggerId,
                                  @Param("status") int status,
                                  @Param("offset") int offset,
                                  @Param("rows") int rows);

    /**
     * 查询出所有的博文
     *
     * @return
     */
    List<Blog> queryAll();

}
