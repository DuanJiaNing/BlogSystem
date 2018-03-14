package com.duan.blogos.dao.blogger;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blogger.BloggerLink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/29.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BloggerLinkDao extends BaseDao<BloggerLink> {

    /**
     * 根据博主id查询其友情链接
     *
     * @param bloggerId 博主id
     * @param offset    偏移位置
     * @param rows      行数
     * @return 查询结果
     */
    List<BloggerLink> listBlogLinkByBloggerId(@Param("bloggerId") int bloggerId,
                                              @Param("offset") int offset,
                                              @Param("rows") int rows);

    /**
     * 检查链接是否存在
     *
     * @param linkId 链接id
     * @return 链接id
     */
    Integer getLinkForCheckExist(int linkId);

    /**
     * 根据id查询链接
     *
     * @param linkId id
     * @return 查询结果
     */
    BloggerLink getLink(int linkId);

    /**
     * 统计博主的链接数量
     *
     * @param bloggerId 博主id
     * @return 统计结果
     */
    int countLinkByBloggerId(int bloggerId);
}
