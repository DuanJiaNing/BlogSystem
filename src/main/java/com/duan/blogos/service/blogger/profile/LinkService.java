package com.duan.blogos.service.blogger.profile;

import com.duan.blogos.dto.blogger.BloggerLinkDTO;
import com.duan.blogos.entity.blogger.BloggerLink;
import com.duan.blogos.result.ResultBean;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 博主友情链接服务
 *
 * @author DuanJiaNing
 */
public interface LinkService {

    /**
     * 获取友情链接，自动按优先级从高到底排序
     *
     * @param bloggerId 博主id
     * @param offset    结果集起始位置
     * @param rows      行数
     * @return 查询结果
     */
    ResultBean<List<BloggerLinkDTO>> listBloggerLink(int bloggerId, int offset, int rows);

    /**
     * 新增友情链接
     *
     * @param bloggerId 博主id
     * @param iconId    图标id
     * @param title     名字
     * @param url       url
     * @param desc      描述
     * @param priority  优先级
     * @return 新纪录id
     */
    int insertBloggerLink(int bloggerId, int iconId, String title, String url, String desc, int priority);

    /**
     * 删除链接
     *
     * @param linkId 链接id
     * @return 删除的记录
     */
    BloggerLink deleteBloggerLink(int linkId);

    /**
     * 更新链接
     *
     * @param linkId       链接id
     * @param newBloggerId 新的博主（链接所属博主），不改变传 -1
     * @param newIconId    新的图片id，不改变传 -1
     * @param newTitle     新的标题，不改变传 null
     * @param newUrl       新的url，不改变传 null
     * @param newDesc      新的描述，不改变传 null
     * @param newPriority  新的优先级，不改变传 -1
     * @return 更新失败为false
     */
    boolean updateBloggerLink(int linkId, int newBloggerId, int newIconId, String newTitle,
                           String newUrl, String newDesc, int newPriority);

}
