package com.showyourselfblog.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Description 评论数据表实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:17
 **/
@Entity
@Data
public class CommentsInfo {
    @Id
    int id;
    String tid;
    String userId;
    String atUserId;
    boolean isAt;
    String text;
    Timestamp time;
    int likeNum;
    int unlikeNum;

    @Override
    public String toString() {
        return "CommentsInfo{" +
                "id=" + id +
                ", tid='" + tid + '\'' +
                ", userId='" + userId + '\'' +
                ", atUserId='" + atUserId + '\'' +
                ", isAt=" + isAt +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", likeNum=" + likeNum +
                ", unlikeNum=" + unlikeNum +
                '}';
    }
}
