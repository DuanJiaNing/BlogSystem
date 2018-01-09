package com.duan.blogos.manager;

import com.duan.blogos.dto.blog.BlogCommentDTO;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.dto.blog.BlogMainContentDTO;
import com.duan.blogos.dto.blog.BlogStatisticsDTO;
import com.duan.blogos.dto.blogger.BloggerDTO;
import com.duan.blogos.dto.blogger.BloggerLinkDTO;
import com.duan.blogos.dto.blogger.CollectBlogListItemDTO;
import com.duan.blogos.entity.blog.*;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerLink;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.util.StringUtils;

import java.util.List;

/**
 * Created on 2017/12/25.
 * entity 数据填充为 dto 数据
 *
 * @author DuanJiaNing
 */
public class DataFillingManager {

    public BlogStatisticsDTO blogStatisticsToDTO(BlogStatistics statistics) {

        BlogStatisticsDTO dto = new BlogStatisticsDTO();
        dto.setAdmireCount(statistics.getAdmireCount());
        dto.setBlogId(statistics.getBlogId());
        dto.setCollectCount(statistics.getCollectCount());
        dto.setCommentCount(statistics.getCommentCount());
        dto.setComplainCount(statistics.getComplainCount());
        dto.setId(statistics.getId());
        dto.setLikeCount(statistics.getLikeCount());
        dto.setReplyCommentCount(statistics.getReplyCommentCount());
        dto.setShareCount(statistics.getShareCount());
        dto.setViewCount(statistics.getViewCount());
        return dto;
    }

    public BlogCommentDTO blogCommentToDTO(BlogComment comment, BloggerDTO spokesman, BloggerDTO listener) {
        BlogCommentDTO dto = new BlogCommentDTO();
        dto.setBlogId(comment.getBlogId());
        dto.setContent(comment.getContent());
        dto.setId(comment.getId());
        dto.setReleaseDate(comment.getReleaseDate());
        dto.setState(comment.getState());
        dto.setSpokesman(spokesman);
        dto.setListener(listener);
        return dto;
    }

    public BloggerDTO bloggerAccountToDTO(BloggerAccount account, BloggerProfile profile, BloggerPicture avatar) {
        BloggerDTO dto = new BloggerDTO();
        dto.setId(account.getId());
        dto.setRegisterDate(account.getRegisterDate());
        dto.setUsername(account.getUsername());
        dto.setAvatar(avatar);
        dto.setProfile(profile);
        return dto;
    }

    public BlogListItemDTO blogListItemToDTO(BlogStatistics statistics, List<BlogCategory> categories, Blog blog) {
        BlogListItemDTO dto = new BlogListItemDTO();
        dto.setCategories(categories.toArray(new BlogCategory[categories.size()]));
        dto.setCollectCount(statistics.getCollectCount());
        dto.setCommentCount(statistics.getCommentCount());
        dto.setId(blog.getId());
        dto.setLikeCount(statistics.getLikeCount());
        dto.setReleaseDate(blog.getReleaseDate());
        dto.setSummary(blog.getSummary());
        dto.setTitle(blog.getTitle());
        dto.setViewCount(statistics.getViewCount());
        return dto;

    }

    public BloggerLinkDTO bloggerLinkToDTO(BloggerLink link, BloggerPicture icon) {
        BloggerLinkDTO dto = new BloggerLinkDTO();
        dto.setBewrite(link.getBewrite());
        dto.setBloggerId(link.getBloggerId());
        dto.setIcon(icon);
        dto.setId(link.getId());
        dto.setTitle(link.getTitle());
        dto.setUrl(link.getUrl());
        return dto;
    }

    public BlogMainContentDTO blogMainContentToDTO(Blog blog, List<BlogCategory> categories, List<BlogLabel> labels, String splitChar) {
        BlogMainContentDTO dto = new BlogMainContentDTO();
        dto.setCategories(categories == null ? null : categories.toArray(new BlogCategory[categories.size()]));
        dto.setLabels(labels == null ? null : labels.toArray(new BlogLabel[labels.size()]));
        dto.setId(blog.getId());
        dto.setKeyWords(StringUtils.stringToStringArray(blog.getKeyWords(), splitChar));
        dto.setNearestModifyDate(blog.getNearestModifyDate());
        dto.setReleaseDate(blog.getReleaseDate());
        dto.setStatus(blog.getState());
        dto.setSummary(blog.getSummary());
        dto.setTitle(blog.getTitle());
        dto.setWordCount(blog.getWordCount());
        dto.setContent(blog.getContent());

        return dto;
    }

    public CollectBlogListItemDTO collectBlogListItemToDTO(int bloggerId, BlogCollect collect, BlogListItemDTO blog, BloggerDTO author) {
        CollectBlogListItemDTO dto = new CollectBlogListItemDTO();
        dto.setAuthor(author);
        dto.setBlog(blog);
        dto.setBloggerId(bloggerId);
        dto.setCategoryId(collect.getCategoryId());
        dto.setCollectDate(collect.getCollectDate());
        dto.setId(collect.getId());
        dto.setReason(collect.getReason());
        return dto;
    }
}
