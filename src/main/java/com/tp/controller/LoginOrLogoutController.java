package com.tp.controller;

import com.tp.common.R;
import com.tp.entity.User;
import com.tp.service.LoginOrLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginOrLogoutController {

    @Autowired
    private LoginOrLogoutService loginOrLogoutService;

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        return loginOrLogoutService.login(user);
    }

    @PostMapping("/logout")
    public R logout() {
        return loginOrLogoutService.logout();
    }

}
