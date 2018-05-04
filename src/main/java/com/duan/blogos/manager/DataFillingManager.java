package com.duan.blogos.manager;

import com.duan.blogos.dto.blog.*;
import com.duan.blogos.dto.blog.BlogListItemDTO;
import com.duan.blogos.dto.blogger.*;
import com.duan.blogos.entity.blog.*;
import com.duan.blogos.entity.blogger.BloggerAccount;
import com.duan.blogos.entity.blogger.BloggerLink;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.util.CollectionUtils;
import com.duan.blogos.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2017/12/25.
 * entity 数据转换为 dto 数据
 *
 * @author DuanJiaNing
 */
@Component
public class DataFillingManager {

    public BlogStatisticsCountDTO blogStatisticsCountToDTO(BlogStatistics statistics) {

        BlogStatisticsCountDTO dto = new BlogStatisticsCountDTO();
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

    public BlogCommentDTO blogCommentToDTO(BlogComment comment, BloggerDTO spokesman) {
        BlogCommentDTO dto = new BlogCommentDTO();
        dto.setBlogId(comment.getBlogId());
        dto.setContent(comment.getContent());
        dto.setId(comment.getId());
        dto.setReleaseDate(comment.getReleaseDate());
        dto.setState(comment.getState());
        dto.setSpokesman(spokesman);
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

    public BlogListItemDTO blogListItemToDTO(BlogStatistics statistics, BlogCategory[] categories, BlogLabel[] labels,
                                             Blog blog, String blogImg) {
        BlogListItemDTO dto = new BlogListItemDTO();
        dto.setCategories(categories);
        dto.setLabels(labels);
        dto.setCollectCount(statistics.getCollectCount());
        dto.setCommentCount(statistics.getCommentCount());
        dto.setId(blog.getId());
        dto.setLikeCount(statistics.getLikeCount());
        dto.setReleaseDate(blog.getReleaseDate());
        dto.setSummary(blog.getSummary());
        dto.setTitle(blog.getTitle());
        dto.setViewCount(statistics.getViewCount());
        dto.setImg(blogImg);
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
        dto.setKeyWords(StringUtils.stringArrayToArray(blog.getKeyWords(), splitChar));
        dto.setNearestModifyDate(blog.getNearestModifyDate());
        dto.setReleaseDate(blog.getReleaseDate());
        dto.setStatus(blog.getState());
        dto.setSummary(blog.getSummary());
        dto.setTitle(blog.getTitle());
        dto.setWordCount(blog.getWordCount());
        dto.setContent(blog.getContent());

        return dto;
    }


    public BloggerCategoryDTO blogCategoryToDTO(BlogCategory category, BloggerPicture icon, int count) {
        BloggerCategoryDTO dto = new BloggerCategoryDTO();
        dto.setBloggerId(category.getBloggerId());
        dto.setCreateDate(category.getCreateDate());
        dto.setBewrite(category.getBewrite());
        dto.setIcon(icon);
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        dto.setCount(count);
        return dto;
    }

    public com.duan.blogos.dto.blogger.BlogListItemDTO bloggerBlogListItemToDTO(Blog blog, BlogStatistics statistics,
                                                                                List<BlogCategory> categories) {
        com.duan.blogos.dto.blogger.BlogListItemDTO dto = new com.duan.blogos.dto.blogger.BlogListItemDTO();
        dto.setCategories(CollectionUtils.isEmpty(categories) ? null : categories.toArray(new BlogCategory[categories.size()]));
        dto.setCollectCount(statistics.getCollectCount());
        dto.setCommentCount(statistics.getCommentCount());
        dto.setComplainCount(statistics.getComplainCount());
        dto.setId(blog.getId());
        dto.setLikeCount(statistics.getLikeCount());
        dto.setNearestModifyDate(blog.getNearestModifyDate());
        dto.setReleaseDate(blog.getReleaseDate());
        dto.setState(blog.getState());
        dto.setSummary(blog.getSummary());
        dto.setTitle(blog.getTitle());
        dto.setViewCount(statistics.getViewCount());
        dto.setWordCount(blog.getWordCount());
        return dto;
    }

    public BlogStatisticsDTO blogStatisticsToDTO(Blog blog, BlogStatistics statistics, BlogCategory[] categories,
                                                 BlogLabel[] labels, BloggerDTO[] likes, BloggerDTO[] collects,
                                                 BloggerDTO[] commenter, String splitChar) {
        BlogStatisticsDTO dto = new BlogStatisticsDTO();
        dto.setCategories(categories);
        dto.setCollects(collects);
        dto.setCommenter(commenter);
        dto.setId(statistics.getId());
        dto.setKeyWords(StringUtils.stringArrayToArray(blog.getKeyWords(), splitChar));
        dto.setLabels(labels);
        dto.setLikes(likes);
        dto.setNearestModifyDate(blog.getNearestModifyDate());
        dto.setReleaseDate(blog.getReleaseDate());
        dto.setState(blog.getState());
        dto.setStatistics(statistics);
        dto.setSummary(blog.getSummary());
        dto.setTitle(blog.getTitle());
        dto.setWordCount(blog.getWordCount());

        return dto;
    }

    public BloggerStatisticsDTO bloggerStatisticToDTO(int blogCount, int wordCount, int likeCount, int likedCount,
                                                      int categoryCount, int labelCount, int collectCount,
                                                      int collectedCount, int linkCount) {
        BloggerStatisticsDTO dto = new BloggerStatisticsDTO();
        dto.setBlogCount(blogCount);
        dto.setWordCount(wordCount);
        dto.setLikeCount(likeCount);
        dto.setLikedCount(likedCount);
        dto.setCategoryCount(categoryCount);
        dto.setLabelCount(labelCount);
        dto.setCollectCount(collectCount);
        dto.setCollectedCount(collectedCount);
        dto.setLinkCount(linkCount);

        return dto;
    }

    public FavouriteBlogListItemDTO collectBlogListItemToDTO(int bloggerId, BlogCollect collect, BlogListItemDTO blog, BloggerDTO author) {
        FavouriteBlogListItemDTO dto = new FavouriteBlogListItemDTO();
        dto.setAuthor(author);
        dto.setBlog(blog);
        dto.setBloggerId(bloggerId);
        dto.setDate(collect.getCollectDate());
        dto.setId(collect.getId());
        dto.setReason(collect.getReason());
        return dto;
    }

    public FavouriteBlogListItemDTO likeBlogListItemToDTO(int bloggerId, BlogLike like, BlogListItemDTO blog, BloggerDTO liker) {
        FavouriteBlogListItemDTO dto = new FavouriteBlogListItemDTO();
        dto.setAuthor(liker);
        dto.setBlog(blog);
        dto.setBloggerId(bloggerId);
        dto.setDate(like.getLikeDate());
        dto.setId(like.getId());
        return dto;
    }

    public BloggerBriefDTO bloggerTobrief(BloggerDTO bloggerDTO, BloggerStatisticsDTO statisticsDTO) {
        BloggerBriefDTO dto = new BloggerBriefDTO();
        dto.setBlogger(bloggerDTO);
        dto.setStatistics(statisticsDTO);
        return dto;
    }
}
