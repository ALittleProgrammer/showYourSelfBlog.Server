package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.CommentsInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description CommentsInfo接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:06
 **/
public interface CommentsInfoDao extends CrudRepository<CommentsInfo,Integer> {
}
