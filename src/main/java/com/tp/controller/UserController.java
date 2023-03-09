package com.tp.controller;

import com.tp.common.R;
import com.tp.common.annotation.RequiresPermissions;
import com.tp.entity.User;
import com.tp.entity.dto.UserDto;
import com.tp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @RequiresPermissions("user:query")
    public R<List<User>> list(UserDto dto) {
        return userService.list(dto);
    }

    @PostMapping("/add")
    @RequiresPermissions("user:add")
    public R add(@RequestBody User user) {
        return userService.add(user);
    }

    @PostMapping("/update")
    @RequiresPermissions("user:update")
    public R update(@RequestBody User user){
        return userService.update(user);
    }
}
