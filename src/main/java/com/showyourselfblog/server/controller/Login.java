package com.showyourselfblog.server.controller;

import com.showyourselfblog.server.util.Responce;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 登陆注册业务逻辑
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-20 11:06
 **/
@RestController
@RequestMapping("/login")
public class Login {

    @PostMapping("/login")
    public Responce login(@RequestBody String phone,@RequestBody String password,){

    }
}
