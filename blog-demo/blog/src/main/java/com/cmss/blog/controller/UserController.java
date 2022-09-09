package com.cmss.blog.controller;

import com.cmss.blog.service.UserService;
import com.cmss.blog.vo.CommenResult;
import com.cmss.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public CommenResult register(@RequestBody LoginParam loginParam){

        return userService.register(loginParam);
    }

    @PostMapping("login")
    public CommenResult login(@RequestBody LoginParam loginParam){

        return userService.login(loginParam);
    }

    @PostMapping("loginout")
    public CommenResult loginOut(@RequestBody LoginParam loginParam){

        return userService.loginOut(loginParam);
    }

}
