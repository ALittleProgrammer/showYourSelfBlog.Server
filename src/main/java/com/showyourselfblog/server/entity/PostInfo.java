package com.showyourselfblog.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description 帖子记录数据表实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:14
 **/
@Entity
@Data
public class PostInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer tid;
    String userId;
    String text;
    String title;
    int nOfCom;
    int nOfCol;
    int nOfFor;
    int nOfUn;
    int nOfLike;
    int nOfWat;
    Timestamp uptime;
    String intro;

    @Override
    public String toString() {
        return "PostInfo{" +
                "tid='" + tid + '\'' +
                ", userId='" + userId + '\'' +
                ", path='" + text + '\'' +
                ", title='" + title + '\'' +
                ", nOfCom=" + nOfCom +
                ", nOfCol=" + nOfCol +
                ", nOfFor=" + nOfFor +
                ", nOfUn=" + nOfUn +
                ", uptime=" + uptime +
                '}';
    }
}
