package com.showyourselfblog.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description 点赞数据表
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:20
 **/
@Entity
public class LikeInfo {
    @Id
    int id;
    String userId;
    int comId;
    int tid;
    boolean upOrUn;
    boolean isTrue;
    boolean isCom;

    @Override
    public String toString() {
        return "LikeInfo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", comId=" + comId +
                ", tid=" + tid +
                ", upOrUn=" + upOrUn +
                ", isTrue=" + isTrue +
                ", isCom=" + isCom +
                '}';
    }
}
