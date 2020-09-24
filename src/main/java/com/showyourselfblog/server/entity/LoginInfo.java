package com.showyourselfblog.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description 登陆记录表实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:11
 **/
@Entity
public class LoginInfo {
    @Id
    int id;
    String userId;
    String ip;
    String city;

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", ip='" + ip + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
