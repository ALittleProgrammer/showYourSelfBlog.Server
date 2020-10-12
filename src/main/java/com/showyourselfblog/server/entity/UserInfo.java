package com.showyourselfblog.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Description 用户信息实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-14 17:01
 **/
@Entity
@Data
public class UserInfo {
    @Id
    String phone;
    String userId;
    String username;
    String realName;
    int userLv;
    String userPic;
    String password;
    String address;
    /**
     * true男，false女
     **/
    String sex;
    int exception;
    Timestamp registerTime;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", userLv=" + userLv +
                ", userPic='" + userPic + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", exception=" + exception +
                ", registerTime=" + registerTime +
                '}';
    }
}
