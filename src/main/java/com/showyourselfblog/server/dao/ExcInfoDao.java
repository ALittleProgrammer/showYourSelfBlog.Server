package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.ExcInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description ExcInfo数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:05
 **/
public interface ExcInfoDao extends CrudRepository<ExcInfo, Integer> {
}
