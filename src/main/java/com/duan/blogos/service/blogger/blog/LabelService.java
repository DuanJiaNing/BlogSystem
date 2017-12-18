package com.duan.blogos.service.blogger.blog;

import com.duan.blogos.entity.blog.BlogLabel;
import com.duan.blogos.result.ResultBean;

/**
 * Created on 2017/12/18.
 * 标签服务
 *
 * @author DuanJiaNing
 */
public interface LabelService {

    /**
     * 新增标签
     *
     * @param bloggerId 标签创建者（博主）id
     * @param title     标签名
     * @return 新纪录id
     */
    int insertLabel(int bloggerId, String title);

    /**
     * 修改标签
     *
     * @param labelId      标签id
     * @param newBloggerId 新的标签所有者
     * @param newTitle     新标签名
     */
    void updateLabel(int labelId, int newBloggerId, String newTitle);

    /**
     * 删除标签
     *
     * @param labelId 标签id
     * @return 被删除的记录
     */
    BlogLabel deleteLabel(int labelId);

    /**
     * 获得博主创建的所有标签
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    ResultBean<BlogLabel> listLabel(int bloggerId);

}
