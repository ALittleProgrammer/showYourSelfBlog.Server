package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.LoginInfo;
import com.showyourselfblog.server.entity.LookInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description LoginInfo类数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:02
 **/
public interface LoginInfoDao extends CrudRepository<LoginInfo,Integer> {
}
