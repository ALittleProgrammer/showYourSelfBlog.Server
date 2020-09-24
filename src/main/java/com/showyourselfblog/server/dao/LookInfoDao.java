package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.LookInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description LookInfo类数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:01
 **/
public interface LookInfoDao extends CrudRepository<LookInfo,Integer> {
}
