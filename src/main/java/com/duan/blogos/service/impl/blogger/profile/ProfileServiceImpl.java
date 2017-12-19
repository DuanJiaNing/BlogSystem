package com.duan.blogos.service.impl.blogger.profile;

import com.duan.blogos.service.blogger.profile.ProfileService;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/12/19.
 *
 * @author DuanJiaNing
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    @Override
    public int insertBloggerProfile(int bloggerId, String phone, String email, String aboutMe, String intro) {
        return 0;
    }

    @Override
    public boolean updateBloggerAvatar(int pictureId) {
        return false;
    }

    @Override
    public boolean updateBloggerProfile(int bloggerId, String newPhone, String newEmail, String newAboutMe, String newIntro) {
        return false;
    }
}
