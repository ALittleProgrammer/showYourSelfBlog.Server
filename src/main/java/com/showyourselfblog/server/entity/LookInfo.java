package com.showyourselfblog.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description 浏览记录表实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:24
 **/
@Entity
public class LookInfo {
    @Id
    int lid;
    String userId;
    int tid;
    Timestamp lookTime;

    @Override
    public String toString() {
        return "LookInfo{" +
                "lid=" + lid +
                ", userId='" + userId + '\'' +
                ", tid=" + tid +
                ", lookTime=" + lookTime +
                '}';
    }
}
