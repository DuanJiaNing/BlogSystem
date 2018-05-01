package com.duan.blogos.manager;

import com.duan.blogos.dao.blogger.BloggerAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/5/1.
 *
 * @author DuanJiaNing
 */
@Component
public class WebsiteManager {

    @Autowired
    private BloggerAccountDao accountDao;

    /**
     * 获得网站活跃博主 id
     *
     * @param count 获取个数
     * @return id 集合
     */
    public int[] getActiveBloggerIds(int count) {

        // UPDATE: 2018/5/1 更新 完善策略
        Integer[] array = accountDao.listId(count).toArray(new Integer[]{});
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }

        return res;
    }

}
