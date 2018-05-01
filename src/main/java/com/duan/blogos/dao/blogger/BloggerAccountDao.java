package com.duan.blogos.dao.blogger;

import com.duan.blogos.dao.BaseDao;
import com.duan.blogos.dao.blog.BlogDao;
import com.duan.blogos.entity.blogger.BloggerAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BloggerAccountDao extends BaseDao<BloggerAccount> {

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

    /**
     * 获取id
     * @param count 获取个数
     * @return id数组
     */
    // UPDATE: 2018/5/1 更新 调整活跃用户获取策略后删除
    List<Integer> listId(int count);
}
