package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description 用户信息数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 14:26
 **/
public interface UserInfoDao extends CrudRepository<UserInfo,String> {

}
