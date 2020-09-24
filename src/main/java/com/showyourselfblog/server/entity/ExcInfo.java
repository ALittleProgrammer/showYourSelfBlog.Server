package com.showyourselfblog.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description 经验获取记录表实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:23
 **/
@Entity
public class ExcInfo {
    @Id
    int levelUpId;
    int code;
    int exc;
    Timestamp time;

    @Override
    public String toString() {
        return "ExcInfo{" +
                "levelUpId=" + levelUpId +
                ", code=" + code +
                ", exc=" + exc +
                ", time=" + time +
                '}';
    }
}
