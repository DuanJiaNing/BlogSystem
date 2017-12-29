package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.dao.blogger.BloggerProfileDao;
import com.duan.blogos.entity.blogger.BloggerProfile;
import com.duan.blogos.service.blogger.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private BloggerProfileDao profileDao;

    @Override
    public int insertBloggerProfile(int bloggerId, String phone, String email, String aboutMe, String intro) {

        BloggerProfile profile = new BloggerProfile();
        profile.setAboutMe(aboutMe);
        profile.setBloggerId(bloggerId);
        profile.setEmail(email);
        profile.setIntro(intro);
        profile.setPhone(phone);
        int effect = profileDao.insert(profile);
        if (effect <= 0) return -1;

        return profile.getId();
    }

    @Override
    public boolean updateBloggerProfile(int bloggerId, String newPhone, String newEmail, String newAboutMe, String newIntro) {

        BloggerProfile profile = new BloggerProfile();
        profile.setAboutMe(newAboutMe);
        profile.setBloggerId(bloggerId);
        profile.setEmail(newEmail);
        profile.setIntro(newIntro);
        profile.setPhone(newPhone);
        int effect = profileDao.update(profile);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public boolean deleteBloggerProfile(int bloggerId) {

        int effect = profileDao.delete(bloggerId);
        if (effect <= 0) return false;

        return true;
    }

    @Override
    public BloggerProfile getBloggerProfile(int bloggerId) {
        return profileDao.getProfileByBloggerId(bloggerId);
    }

}
