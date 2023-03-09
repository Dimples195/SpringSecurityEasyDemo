package com.tp.service.impl;

import com.tp.common.R;
import com.tp.entity.LoginUser;
import com.tp.entity.User;
import com.tp.service.LoginOrLogoutService;
import com.tp.utils.JwtUtil;
import com.tp.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginOrLogoutServiceImpl implements LoginOrLogoutService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    /**
     * 登录
     * @param user 用户信息
     */
    @Override
    public R login(User user) {
        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //AuthenticationManager委托机制对authenticationToken进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication == null) {
            throw new RuntimeException("登陆失败");
        }

        //如果认证通过， 适用user生成jwt jwt存入ResponseResult中返回

        //如果认证通过，拿到当前登录用户的信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //System.out.println(loginUser);
        //获取当前用户ID
        String userID = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJwt(userID);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //把完整的用户信息存入redis userID为key 用户信息为value
        redisCache.setCacheObject("login:" + userID, loginUser);

        return new R(200, "success", map);
    }

    /**
     * 登出
     */
    @Override
    public R logout() {
        //从Security ContextHolder中获取userid
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //根据id删除redis中的信息
        redisCache.deleteObject("login:"+userid);
        return new R(200,"注销成功");
    }

}
