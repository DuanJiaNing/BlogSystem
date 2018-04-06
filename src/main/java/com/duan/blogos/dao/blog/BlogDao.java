package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogStatusEnum;
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

    /**
     * 查询博文id
     *
     * @param bloggerId 博主id
     * @param title     博文标题（同一博主的标题不能重复）
     * @return 查询结果
     */
    Integer getBlogIdByUniqueKey(@Param("bloggerId") int bloggerId,
                                 @Param("title") String title);

    /**
     * 通过博文id查询博文
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Blog getBlogById(int blogId);

    /**
     * 通过博文id查询博文id，该方法只为检查博文是否存在
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    Integer getBlogIdById(int blogId);

    /**
     * 查询出指定博主的所有博文包含着的类别
     *
     * @param bloggerId 博主id
     * @return 只查询了博文类别和博文id的集合
     */
    List<Blog> listAllCategoryByBloggerId(int bloggerId);

    /**
     * 查询出指定博主的所有博文包含的标签
     *
     * @param bloggerId 博主id
     * @return 只查询了博文标签和博文id的结果集
     */
    List<Blog> listAllLabelByBloggerId(int bloggerId);

    /**
     * 根据博主获得其所有博文的字数统计
     *
     * @param bloggerId 博主id
     * @return 只有 wordCount 属性有值的结果
     */
    List<Blog> listAllWordCountByBloggerId(@Param("bloggerId") int bloggerId,
                                           @Param("state") int state);

    /**
     * 统计指定类别的博文数量
     *
     * @param bloggerId  博主id
     * @param categoryId 类别id
     * @return 数量
     */
    Integer countBlogByCategory(@Param("bloggerId") int bloggerId,
                                @Param("categoryId") int categoryId,
                                @Param("state") int state);

    /**
     * 统计指定博主的博文数量
     *
     * @param bloggerId 博主id
     * @return 数量
     */
    Integer countBlogByBloggerId(@Param("bloggerId") int bloggerId,
                                 @Param("state") int state);

    /**
     * 查询指定博主的所有博文，限定查询博文的内容为 md 或 html。
     *
     * @param bloggerId 博主id
     * @param format    md 或 html
     * @return 查询结果，format 为 md 时只查询 content_md，为 html 时只查询 content
     */
    List<Blog> listAllByFormat(@Param("bloggerId") int bloggerId,
                               @Param("format") int format);

    /**
     * 查询博主的所有博文的id
     *
     * @param bloggerId 博主id
     * @return 查询结果，只查询 id
     */
    List<Blog> listAllIdByBloggerId(int bloggerId);
}
