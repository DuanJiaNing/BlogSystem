package com.duan.blogos.service.blogger;

/**
 * Created on 2018/3/13.
 *
 * @author DuanJiaNing
 */
public interface BloggerCommentService {

    /**
     * 新增一条评论记录，同时博文评论次数加一
     *
     * @param blogId      博文id
     * @param spokesmanId 发言者
     * @param listenerId  被评论者
     * @param state       留言状态
     * @param content     评论内容
     * @return 新纪录id
     */
    int insertComment(int blogId, int spokesmanId, int listenerId, int state, String content);

    /**
     * 删除评论
     *
     * @param commentId 评论id
     * @param blogId    博文id
     * @return 删除成功返回true
     */
    boolean deleteComment(int commentId, int blogId);

}
