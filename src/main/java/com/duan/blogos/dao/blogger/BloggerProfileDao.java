package com.duan.blogos.dao.blogger;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.entity.blogger.BloggerProfile;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017/12/25.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BloggerProfileDao extends BaseDao<BloggerProfile> {

    /**
     * 根据博主id获得博主资料
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    BloggerProfile getProfileByBloggerId(int bloggerId);

    /**
     * 根据电话查询博主资料
     *
     * @param phone 电话
     * @return 结果
     */
    BloggerProfile getProfileByPhone(String phone);
}
