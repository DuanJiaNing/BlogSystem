package com.duan.blogos.service.blogger;

/**
 * Created on 2018/3/11.
 *
 * @author DuanJiaNing
 */
public interface BloggerLikeService {

    /**
     * 检查博主是否喜欢过指定博文
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 已喜欢返回 true
     */
    boolean getLikeState(int bloggerId, int blogId);

}
