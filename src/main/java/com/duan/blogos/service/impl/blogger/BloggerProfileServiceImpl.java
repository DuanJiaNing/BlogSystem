package com.duan.blogos.service.impl.blogger;

import com.duan.blogos.dao.blogger.BloggerPictureDao;
import com.duan.blogos.dao.blogger.BloggerProfileDao;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.manager.ImageManager;
import com.duan.blogos.service.blogger.BloggerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class BloggerProfileServiceImpl implements BloggerProfileService {

    @Autowired
    private BloggerProfileDao profileDao;

    @Autowired
    private BloggerPictureDao pictureDao;

    @Autowired
    private ImageManager imageManager;

    @Override
    public int insertBloggerProfile(int bloggerId, int avatarId, String phone, String email, String aboutMe, String intro) {

        BloggerProfile profile = new BloggerProfile();
        profile.setAboutMe(aboutMe);
        profile.setBloggerId(bloggerId);
        profile.setAvatarId(avatarId < 0 ? null : avatarId);
        profile.setEmail(email);
        profile.setIntro(intro);
        profile.setPhone(phone);
        int effect = profileDao.insert(profile);
        if (effect <= 0) return -1;

        if (avatarId > 0)
            imageManager.imageInsertHandle(bloggerId, avatarId);

        return profile.getId();
    }

    @Override
    public boolean updateBloggerProfile(int bloggerId, int avatarId, String newPhone, String newEmail,
                                        String newAboutMe, String newIntro) {

        BloggerProfile profile = profileDao.getProfileByBloggerId(bloggerId);
        if (profile == null) return false;
        Integer oldAvatarId = profile.getAvatarId();

        profile.setAvatarId(avatarId == -1 ? null : avatarId);
        profile.setPhone(newPhone);
        profile.setEmail(newEmail);
        profile.setAboutMe(newAboutMe);
        profile.setIntro(newIntro);
        int effect = profileDao.update(profile);
        if (effect <= 0) return false;

        if (avatarId > 0)
            imageManager.imageUpdateHandle(bloggerId, avatarId, oldAvatarId);

        return true;
    }

    @Override
    public boolean deleteBloggerProfile(int bloggerId) {

        BloggerProfile profile = profileDao.getProfileByBloggerId(bloggerId);

        int effect = profileDao.delete(bloggerId);
        if (effect <= 0) return false;

        Integer id;
        if ((id = profile.getAvatarId()) != null)
            pictureDao.updateUseCountMinus(id);

        return true;
    }

    @Override
    public BloggerProfile getBloggerProfile(int bloggerId) {
        return profileDao.getProfileByBloggerId(bloggerId);
    }

    @Override
    public BloggerProfile getBloggerProfileByPhone(String phone) {
        return profileDao.getProfileByPhone(phone);
    }

}
