package com.example.forum2.controller;

import com.example.forum2.dto.RegisterDto;
import com.example.forum2.pojo.User;
import com.example.forum2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "登录注册")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册", httpMethod = "POST")
    @PostMapping(value = "/login/register")
    public int register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @ApiOperation(value = "登录", httpMethod = "POST")
    @PostMapping(value = "/login/login")
    public Object sjh(@RequestBody User user) {
        return userService.login(user);
    }

    @ApiOperation(value = "获取登录用户信息", httpMethod = "POST")
    @PostMapping(value = "/login/getUser")
    public Object getUser(@RequestBody User user) {
        return userService.getUser(user);
    }


}
