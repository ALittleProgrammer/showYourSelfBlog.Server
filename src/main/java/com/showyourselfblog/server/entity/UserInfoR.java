package com.showyourselfblog.server.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Description 为注册使用的userInfo类
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-11 01:08
 **/
@Data
public class UserInfoR {
    String phone;
    String userId;
    String username;
    String realName;
    int userLv;
    String userPic;
    String password;
    String address;
    String sex;
    int exception;
    Timestamp registerTime;
    String checkNum;

    public UserInfo getUser(){
        UserInfo user=new UserInfo();
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setUserId(this.userId);
        user.setAddress(this.address);
        user.setException(this.exception);
        user.setUserLv(this.userLv);
        user.setRealName(this.realName);
        user.setSex(this.sex);
        user.setRegisterTime(this.registerTime);
        user.setUserPic(this.userPic);
        user.setUsername(this.username);
        return user;
    }
}
