package com.showyourselfblog.server.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Description 返回的评论实体类
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-10 21:48
 **/
@Data
public class ReCommit {
    String username;
    String text;
    Timestamp time;
}
