package com.duan.blogos.service.blogger;

import com.duan.blogos.entity.blogger.BloggerSetting;

/**
 * Created on 2018/3/26.
 *
 * @author DuanJiaNing
 */
public interface BloggerSettingService {

    /**
     * 获得博主配置
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    BloggerSetting getSetting(int bloggerId);

    /**
     * 更新博主主页个人信息栏位置
     *
     * @param pos 0为左，1为右
     * @param bloggerId 博主id
     * @return 更新成功为true
     */
    boolean updateMainPageNavPos(int bloggerId,int pos);

}
