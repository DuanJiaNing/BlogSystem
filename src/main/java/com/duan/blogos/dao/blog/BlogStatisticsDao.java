package com.duan.blogos.dao.blog;

import com.duan.blogos.entity.blog.BlogStatistics;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017/12/20.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogStatisticsDao {

    /**
     * 查询博文的统计信息
     *
     * @param blogId 对应博文id
     * @return 查询结果
     */
    BlogStatistics getStatistics(int blogId);
}
