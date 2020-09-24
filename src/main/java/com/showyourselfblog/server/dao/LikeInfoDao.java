package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.LikeInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description LikeInfo类数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:04
 **/
public interface LikeInfoDao extends CrudRepository<LikeInfo,Integer> {
}
