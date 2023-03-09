package com.tp.service;

import com.tp.common.R;
import com.tp.entity.User;

public interface LoginOrLogoutService {
    R login(User user);

    R logout();

}
