package com.tp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tp.entity.LoginUser;
import com.tp.entity.User;
import com.tp.mapper.MenuMapper;
import com.tp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //认证，即校验用户是否存在 查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq((username != null && !"".equals(username)), User::getUserName, username);
        User user = userMapper.selectOne(wrapper);
        //如果没有查询到用户
        if (user == null) {
            System.out.println("用户名错误");
            return new LoginUser();
        }

        //TODO 查询对应用户信息
        //定义一个权限集合
        //List<String> permissions = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());

        //将数据封装为UserDetails返回
        return new LoginUser(user, permissions);
    }
}
