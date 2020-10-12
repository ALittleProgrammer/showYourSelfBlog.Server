package com.showyourselfblog.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description 经验获取记录表实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-16 16:23
 **/
@Entity
@Data
public class ExcInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
