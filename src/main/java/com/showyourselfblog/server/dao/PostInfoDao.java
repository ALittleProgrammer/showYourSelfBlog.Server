package com.showyourselfblog.server.dao;

import com.showyourselfblog.server.entity.PostInfo;
import com.showyourselfblog.server.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @Description Dao数据接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:00
 **/
public interface PostInfoDao extends CrudRepository<PostInfo,Integer> {
    /**
     * 进行首页推荐列表的查询
     * @param pageable
     * @return
     */
    @Query("from PostInfo order by nOfLike DESC")
    public List<PostInfo> getList(Pageable pageable);

    /**
     * 按keyWord搜索
     * @param keyWord
     * @return
     */
    @Query("from PostInfo where text like %:keyWord% or intro like %:keyWord% or title like %:keyWord% order by nOfWat desc ")
    public List<PostInfo> search(@Param("keyWord") String keyWord);

    /**
     * 按userId搜索
     * @param userId
     * @return
     */
    @Query("from PostInfo where userId=:userId order by uptime DESC")
    public List<PostInfo> getByAuth(@Param("userId") String userId);
}
