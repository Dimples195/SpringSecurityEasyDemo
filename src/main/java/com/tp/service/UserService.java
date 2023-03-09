package com.tp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tp.common.R;
import com.tp.entity.User;
import com.tp.entity.dto.UserDto;
import com.tp.mapper.UserMapper;
import com.tp.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询列表
     */
    public R<List<User>> list(UserDto dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        String userName = dto.getUserName();
        wrapper.eq((userName != null && !"".equals(userName)), User::getUserName, userName);
        List<User> users = userMapper.selectList(wrapper);
        return new R<>(200, users);
    }

    /**
     * 新增用户
     */
    public R add(User user) {
        if (isExist(user)) return new R("用户名重复");
        user.setPassword(BCryptUtil.encipher(user.getPassword()));
        user.setCreateTime(LocalDateTime.now().toString());
        return userMapper.insert(user) > 0 ? new R("新增成功") : new R("新增失败");
    }

    /**
     * 修改用户信息
     */
    public R update(User user) {
        if (isExist(user)) return new R("用户名重复");
        return userMapper.updateById(user) > 0 ? new R("修改成功") : new R("修改失败");
    }

    /**
     * 判断当前用户名是否存在
     */
    private boolean isExist(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        String userName = user.getUserName();
        wrapper.eq((!"".equals(userName) && userName != null), User::getUserName, userName);
        User one = userMapper.selectOne(wrapper);
        return one != null;
    }
}
