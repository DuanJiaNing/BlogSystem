package com.duan.blogos.dao.blogger;

import com.duan.blogos.entity.blogger.BloggerAccount;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BloggerAccountDao {

    /**
     * 根据博主用户名查询账户
     *
     * @param bloggerName 用户名
     * @return 查询结果
     */
    BloggerAccount getAccountByName(String bloggerName);

    /**
     * 根据博主id查询账户
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    BloggerAccount getAccountById(int bloggerId);
}
