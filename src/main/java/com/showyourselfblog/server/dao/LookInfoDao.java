package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.LookInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @Description LookInfo类数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:01
 **/
public interface LookInfoDao extends CrudRepository<LookInfo,Integer> {
    /**
     * 查询浏览记录
     * @param tid
     * @param userId
     * @return
     */
    @Query("from LookInfo where tid=:tid and userId=:userId")
    Optional<LookInfo> findByUserTId(@Param("tid") Integer tid, @Param("userId") String userId);
}
