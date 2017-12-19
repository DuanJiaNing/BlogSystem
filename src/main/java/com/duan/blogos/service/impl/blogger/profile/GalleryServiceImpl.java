package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.entity.blogger.BloggerPicture;
import com.duan.blogos.enums.BloggerPictureCategoryEnum;
import com.duan.blogos.service.blogger.profile.GalleryService;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("galleryService")
public class GalleryServiceImpl implements GalleryService {
    @Override
    public int insertPicture(int bloggerId, String desc, BloggerPictureCategoryEnum category, String title) {
        return 0;
    }

    @Override
    public BloggerPicture deletePicture(int pictureId, boolean deleteOnDisk) {
        return null;
    }

    @Override
    public boolean updatePictureCategory(int pictureId, BloggerPictureCategoryEnum newCategory) {
        return false;
    }

    @Override
    public BloggerPicture getPicture(int pictureId) {
        return null;
    }
}
