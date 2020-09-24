package com.showyourselfblog.server.controller;

import com.showyourselfblog.server.dao.UserInfoDao;
import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.util.MD5;
import com.showyourselfblog.server.util.Responce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @PostMapping("/login")
    public Responce login(@RequestBody String phone, @RequestBody String password, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (userDao.findById(MD5.md5Key(phone + password)).isPresent()) {
                return new Responce(1008);
            }
            else {
                UserInfo user=new UserInfo();
                user.setPhone(phone);
                user.setPassword(password);
                user.setUserId(MD5.md5Key(phone+password));
                userDao.save(user);
                return new Responce(1001);
            }
        } catch (Exception e) {
            return new Responce(1009);
        }
    }
}
