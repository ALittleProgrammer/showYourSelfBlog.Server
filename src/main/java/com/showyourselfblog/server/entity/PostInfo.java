package com.showyourselfblog.server.entity;

import lombok.Data;

import javax.persistence.Entity;
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
    @Id
    String tid;
    String userId;
    String tPath;
    String title;
    int nOfCom;
    int nOfCol;
    int nOfFor;
    int nOfUn;
    Timestamp uptime;

    @Override
    public String toString() {
        return "PostInfo{" +
                "tid='" + tid + '\'' +
                ", userId='" + userId + '\'' +
                ", path='" + tPath + '\'' +
                ", title='" + title + '\'' +
                ", nOfCom=" + nOfCom +
                ", nOfCol=" + nOfCol +
                ", nOfFor=" + nOfFor +
                ", nOfUn=" + nOfUn +
                ", uptime=" + uptime +
                '}';
    }
}
