package com.showyourselfblog.server.controller;

import com.showyourselfblog.server.dao.UserInfoDao;
import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.util.JWTUtil;
import com.showyourselfblog.server.util.Responce;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description 设置用户信息
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-11 20:59
 **/
@RestController
public class SetUserInfo {

    @Resource
    UserInfoDao userDao;

    @PostMapping("/setUserInfo")
    public Responce setUserInfo(@RequestBody Map<String,String> map, HttpServletRequest req){
        String jwt=req.getHeader("jwt");
        Claims claims=JWTUtil.getClaim(jwt);
        String phone=(String) claims.get("phone");
        UserInfo user=userDao.findById(phone).get();
        String address=map.getOrDefault("address",null);
        String realName=map.getOrDefault("realName",null);
        String sex=map.getOrDefault("sex",null);
        String newUsername=map.getOrDefault("username",null);
        user.setAddress(address);
        user.setRealName(realName);
        user.setSex(sex);
        user.setUsername(newUsername==null?user.getUsername():newUsername);
        userDao.save(user);
        return new Responce(1001);
    }
}
