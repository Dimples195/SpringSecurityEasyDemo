package com.tp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tp.entity.User;
import com.tp.mapper.UserMapper;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        String username = "zhangsan";
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void password(){
        String password = "a123456";
        String tp = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(tp);
    }

    @Test
    public void time(){
        System.out.println(LocalDateTime.now());
    }
}
