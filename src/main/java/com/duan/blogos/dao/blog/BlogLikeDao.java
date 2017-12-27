package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blog.BlogLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
