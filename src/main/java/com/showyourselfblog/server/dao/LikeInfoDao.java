package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.LikeInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @Description LikeInfo类数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:04
 **/
public interface LikeInfoDao extends CrudRepository<LikeInfo,Integer> {
    /**
     * 查询本人本博客的点赞数据
     * @param tid
     * @param userId
     * @return
     */
    @Query("from LikeInfo where tid=:tid and userId=:userId")
    LikeInfo findByUserTid(@Param("tid") Integer tid,@Param("userId") String userId);
}
