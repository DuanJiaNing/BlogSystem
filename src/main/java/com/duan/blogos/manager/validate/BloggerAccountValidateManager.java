package com.duan.blogos.manager.validate;

import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.service.blogger.BloggerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2017/12/26.
 * 博主账户验证
 *
 * @author DuanJiaNing
 */
@Component
public class BloggerAccountValidateManager {

    @Autowired
    private BloggerAccountService bloggerAccountService;

    /**
     * 检查博主是否存在
     *
     * @param id 博主id
     * @return 存在返回账户，否则返回null
     */
    public BloggerAccount checkAccount(Integer id) {
        BloggerAccount account;
        if (id == null || (account = bloggerAccountService.getAccount(id)) == null) {
            return null;
        }
        return account;
    }

}
