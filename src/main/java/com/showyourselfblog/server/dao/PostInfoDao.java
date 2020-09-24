package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.PostInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description Dao数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:00
 **/
public interface PostInfoDao extends CrudRepository<PostInfo,String> {
}
