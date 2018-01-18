package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.dao.blogger.BloggerLinkDao;
import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dto.blogger.BloggerLinkDTO;
import com.duan.blogos.entity.blogger.BloggerLink;
import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.manager.BloggerPropertiesManager;
import com.duan.blogos.manager.DataFillingManager;
import com.duan.blogos.result.ResultBean;
import com.duan.blogos.service.blogger.profile.LinkService;
import com.duan.blogos.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private BloggerLinkDao linkDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private DataFillingManager fillingManager;

    @Autowired
    private BloggerPropertiesManager bloggerPropertiesManager;

    @Override
    public ResultBean<List<BloggerLinkDTO>> listBloggerLink(int bloggerId, int offset, int rows) {

        List<BloggerLink> list = linkDao.listBlogLinkByBloggerId(bloggerId, offset, rows);

        List<BloggerLinkDTO> result = new ArrayList<>();
        for (BloggerLink link : list) {
            Integer iconId = link.getIconId();
            BloggerPicture icon = iconId == null ?
                    pictureDao.getBloggerUniquePicture(bloggerPropertiesManager.getPictureManagerBloggerId(),
                            BloggerPictureCategoryEnum.BLOGGER_LINK_ICON.getCode()) :
                    pictureDao.getPictureById(iconId);
            BloggerLinkDTO dto = fillingManager.bloggerLinkToDTO(link, icon);
            result.add(dto);
        }

        return CollectionUtils.isEmpty(result) ? null : new ResultBean<>(result);
    }

    @Override
    public int insertBloggerLink(int bloggerId, int iconId, String title, String url, String bewrite) {

        BloggerLink link = new BloggerLink();
        link.setBewrite(bewrite);
        link.setBloggerId(bloggerId);
        link.setIconId(iconId < 0 ? null : iconId);
        link.setTitle(title);
        link.setUrl(url);
        int effect = linkDao.insert(link);
        if (effect < 0) return -1;

        return link.getId();
    }

    @Override
    public boolean updateBloggerLink(int linkId, int newBloggerId, int newIconId, String newTitle, String newUrl, String newBewrite) {
        BloggerLink link = new BloggerLink();
        link.setBewrite(newBewrite);
        link.setBloggerId(newBloggerId < 0 ? null : newBloggerId);
        link.setIconId(newIconId < 0 ? null : newIconId);
        link.setTitle(newTitle);
        link.setUrl(newUrl);
        link.setId(linkId);
        int effect = linkDao.update(link);
        if (effect < 0) return false;

        return true;
    }

    @Override
    public boolean deleteBloggerLink(int linkId) {
        int effect = linkDao.delete(linkId);
        if (effect < 0) return false;
        return true;
    }

    @Override
    public boolean getLinkForCheckExist(int linkId) {
        Integer id = linkDao.getLinkForCheckExist(linkId);
        if (id == null || id != linkId) return false;
        return true;
    }
}
