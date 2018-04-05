package com.duan.blogos.service.blogger;

import com.duan.blogos.dto.blog.BlogTitleIdDTO;
import com.duan.blogos.dto.blogger.BlogListItemDTO;
import com.duan.blogos.entity.blog.Blog;
import com.duan.blogos.enums.BlogFormatEnum;
import com.duan.blogos.enums.BlogStatusEnum;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.service.BlogFilter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created on 2017/12/18.
 * 博主对自己的博文管理服务
 *
 * @author DuanJiaNing
 */
public interface BloggerBlogService extends BlogFilter<ResultBean<List<BlogListItemDTO>>> {

    /**
     * 1 新增博客
     * 2 为博文生成一条统计信息记录
     * 3 解析博文中引用的本地图片（以使其useCount自增）
     * 4 lucene添加索引
     *
     * @param bloggerId   博主id
     * @param categories  类别
     * @param labels      标签
     * @param status      状态
     * @param title       标题
     * @param content     内容
     * @param contentMd   md内容
     * @param summary     摘要
     * @param keyWords    关键字
     * @param analysisImg 解析博文中的图片引用
     * @return 新纪录id
     */
    int insertBlog(int bloggerId, int[] categories, int[] labels, BlogStatusEnum status,
                   String title, String content, String contentMd, String summary, String[] keyWords, boolean analysisImg);

    /**
     * 1 更新博文
     * 2 更新博文中引用的本地图片（取消引用的useCount--，新增的useCount++）
     * 3 更新lucene
     *
     * @param bloggerId     博主id
     * @param blogId        博文id
     * @param newCategories 新类别，不修改传null
     * @param newLabels     新标签，不修改传null
     * @param newStatus     新状态，不修改传null
     * @param newTitle      新标题，不修改传null
     * @param newContent    新内容，不修改传null
     * @param newContentMd  md内容，不修改传null
     * @param newSummary    新摘要，不修改传null
     * @param newKeyWords   新关键字，，不修改传null
     * @return 更新失败为false
     */
    boolean updateBlog(int bloggerId, int blogId, int[] newCategories, int[] newLabels, BlogStatusEnum newStatus,
                       String newTitle, String newContent, String newContentMd, String newSummary, String[] newKeyWords);

    /**
     * 1 删除博文
     * 2 删除统计信息记录
     * 3 博文中引用的图片useCount--
     * 4 删除lucene索引
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 删除的记录
     */
    boolean deleteBlog(int bloggerId, int blogId);

    /**
     * 批量删除博文
     *
     * @param bloggerId 博主id
     * @param blogIds   博文id
     * @return 操作失败为false
     */
    boolean deleteBlogPatch(int bloggerId, int[] blogIds);

    /**
     * 检查博文是否存在
     *
     * @param blogId 博文id
     * @return 存在返回true，否则false
     */
    boolean getBlogForCheckExist(int blogId);

    /**
     * 获得指定博主的指定博文
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 查询结果
     */
    ResultBean<Blog> getBlog(int bloggerId, int blogId);

    /**
     * 通过博主id和博文名获得博文id
     *
     * @param bloggerId 博主id
     * @param blogName  博文标题
     * @return 存在返回id，否则-1
     */
    int getBlogId(int bloggerId, String blogName);

    /**
     * 通过上传的 zip 文件批量导入博文
     *
     * @param file      文件
     * @param bloggerId 博主id
     * @return 成功导入的博文标题和id
     */
    List<BlogTitleIdDTO> insertBlogPatch(MultipartFile file, int bloggerId);

    /**
     * 生成用于 [导出所有博文] 功能的 zip 文件
     *
     * @param bloggerId 博主 id
     * @param format    格式
     * @return zip 文件全路径
     */
    String getAllBlogForDownload(int bloggerId, BlogFormatEnum format);
}
