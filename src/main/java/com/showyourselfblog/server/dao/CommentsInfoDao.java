package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.CommentsInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description CommentsInfo接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:06
 **/
public interface CommentsInfoDao extends CrudRepository<CommentsInfo,Integer> {
    /**
     * 查询一条博文的所有评论
     * @param tid
     * @return
     */
    @Query("from CommentsInfo where tid=:tid order by time DESC")
    public List<CommentsInfo> findByTid(@Param("tid") String tid);
}
