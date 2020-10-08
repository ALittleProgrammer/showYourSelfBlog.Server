package com.showyourselfblog.server.controller;

import com.showyourselfblog.server.dao.UserInfoDao;
import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description 登陆注册业务逻辑
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-20 11:06
 **/
@RestController
@RequestMapping("/login")
public class Login {
    @Autowired
    UserInfoDao userDao;
    Logger log = LoggerFactory.getLogger(this.getClass());
    Map<String, Object> someInfo = new HashMap<>(16);


    @PostMapping("/login")
    public Responce login(@RequestBody UserInfo user, HttpServletRequest req, HttpServletResponse resp) {
        if (userDao.findById(user.getPhone()).isPresent()) {
            UserInfo aUser = userDao.findById(user.getPhone()).get();
            if (aUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                someInfo = MapBeanUtil.objectToMap(aUser);
                resp.addHeader("jwt", JWTUtil.generate(someInfo));
                return new Responce(1001);
            }
        }
        return new Responce(1004);
    }

    @PostMapping("/regist")
    public Responce regist(@RequestBody UserInfo user, @RequestBody String checkNum, HttpServletRequest req, HttpServletResponse resp) {
        if (userDao.findById(user.getPhone()).isPresent()) {
            return new Responce(1008);
        }
        if (!Mes.isValid(user.getPhone(),checkNum)){
            return new Responce(1010);
        }
        userDao.save(user);
        String jwt=JWTUtil.generate(MapBeanUtil.objectToMap(user));
        resp.addHeader("jwt",jwt);
        return new Responce(1001);
    }
}
