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
}
