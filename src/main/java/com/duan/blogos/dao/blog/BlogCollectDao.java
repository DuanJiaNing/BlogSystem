package com.duan.blogos.dao.blog;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blog.BlogCollect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017/12/27.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogCollectDao extends BaseDao<BlogCollect> {

    /**
     * 根据博主和博文删除收藏文章
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 执行结果
     */
    int deleteCollectByBloggerId(@Param("bloggerId") int bloggerId, @Param("blogId") int blogId);

}
