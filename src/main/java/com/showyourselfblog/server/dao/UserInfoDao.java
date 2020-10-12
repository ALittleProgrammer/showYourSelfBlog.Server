package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description 用户信息数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 14:26
 **/
public interface UserInfoDao extends CrudRepository<UserInfo,String> {
    /**
     * 通过userId查询用户信息
     * @param userId
     * @return
     */
    @Query("from UserInfo where userId=:userId")
    public UserInfo getuUerName(@Param("userId") String userId);
}
