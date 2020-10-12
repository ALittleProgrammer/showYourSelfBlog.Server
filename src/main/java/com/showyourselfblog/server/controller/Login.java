package com.showyourselfblog.server.controller;

import com.showyourselfblog.server.dao.LoginInfoDao;
import com.showyourselfblog.server.dao.UserInfoDao;
import com.showyourselfblog.server.entity.LoginInfo;
import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.entity.UserInfoR;
import com.showyourselfblog.server.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @Resource
    LoginInfoDao loginDao;

    Logger log = LoggerFactory.getLogger(this.getClass());
    Map<String, Object> someInfo = new HashMap<>(16);


    @PostMapping("/login")
    public Responce login(@RequestBody UserInfo user, HttpServletRequest req, HttpServletResponse resp) {
        if (userDao.findById(user.getPhone()).isPresent()) {
            UserInfo aUser = userDao.findById(user.getPhone()).get();
            if (aUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                someInfo = MapBeanUtil.objectToMap(aUser);
                resp.addHeader("jwt", JWTUtil.generate(someInfo));
                LoginInfo login=new LoginInfo();
                login.setUserId(aUser.getUserId());
                login.setTime(new Timestamp(System.currentTimeMillis()));
                login.setIp(GetIpAddress.getIpAddress(req));
                IPEntity ipEntity=IPUtils.getIPMsg(login.getIp());
                login.setCity(ipEntity.getCountryName()+" "+ipEntity.getProvinceName()+" "+ipEntity.getCityName());
                //loginDao.save(login);
                return new Responce(1001);
            }
        }
        return new Responce(1004);
    }

    @PostMapping("/regist")
    public Responce regist(@RequestBody UserInfoR user, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (userDao.findById(user.getPhone()).isPresent()) {
            return new Responce(1008);
        }
        if (Mes.isValid(user.getPhone(),user.getCheckNum())){
            user.setUserId(MD5.md5(user.getPhone()+user.getCheckNum()+user.getPassword()));
            user.setRegisterTime(Timestamp.valueOf(LocalDateTime.now()));
            user.setUserLv(0);
            user.setException(0);
            userDao.save(user.getUser());
            String jwt=JWTUtil.generate(MapBeanUtil.objectToMap(user));
            resp.addHeader("jwt",jwt);
            return new Responce(1001);
        }
        return new Responce(1010);
    }

    @PostMapping("/forget")
    public Responce forget(@RequestBody Map<String,String> map,HttpServletRequest req,HttpServletResponse resp){
        String phone= map.getOrDefault("phone", null);
        String checkNum=map.getOrDefault("checkNum",null);
        String pwd=map.getOrDefault("newPWD",null);
        if (!userDao.findById(phone).isPresent())
        {
            return new Responce(1007);
        }
        if (!Mes.isValid(phone,checkNum)){
            return new Responce(1010);
        }
        UserInfo user=userDao.findById(phone).get();
        user.setPassword(pwd);
        userDao.save(user);
        return new Responce(1001);
    }
}
