package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.entity.blog.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/12.
 * 对博文的CRUD
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
     * @see BlogStatusEnum
     */
    List<Blog> listBlog(@Param("bloggerId") int bloggerId,
                        @Param("status") int status);

    /**
     * 根据博主id和文章状态查询其发布的指定偏移位置和数量的博文
     *
     * @param bloggerId 博主id
     * @param status    博文状态
     * @param offset    偏移位置
     * @param rows      行数
     * @return
     * @see BlogStatusEnum
     */
    List<Blog> listBlogWithLimit(@Param("bloggerId") int bloggerId,
                                 @Param("status") int status,
                                 @Param("offset") int offset,
                                 @Param("rows") int rows);

    /**
     * 查询出所有的博文
     *
     * @return
     */
    List<Blog> listAll();

    /**
     * 查询博主创建的所有类别和标签
     *
     * @param bloggerId 博主id
     * @param status    博文状态
     * @return 查询结果
     */
    List<Blog> listAllCategoryAndLabel(@Param("bloggerId") int bloggerId, @Param("status") int status);

    /**
     * 根据博文id查询博文
     *
     * @param ids    博文id
     * @param status 博文状态
     * @param offset 偏移位置
     * @param rows   行数
     * @return 查询结果
     */
    List<Blog> listBlogByBlogIds(@Param("ids") List<Integer> ids,
                                 @Param("status") int status,
                                 @Param("offset") int offset,
                                 @Param("rows") int rows);
}
