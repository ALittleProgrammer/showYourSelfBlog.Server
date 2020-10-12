package com.showyourselfblog.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description 登陆记录表实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:11
 **/
@Entity
@Data
public class LoginInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    String userId;
    String ip;
    String city;
    Timestamp time;

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
