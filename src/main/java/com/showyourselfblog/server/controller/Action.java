package com.showyourselfblog.server.controller;

import com.showyourselfblog.server.dao.CommentsInfoDao;
import com.showyourselfblog.server.dao.LikeInfoDao;
import com.showyourselfblog.server.dao.PostInfoDao;
import com.showyourselfblog.server.dao.UserInfoDao;
import com.showyourselfblog.server.entity.CommentsInfo;
import com.showyourselfblog.server.entity.LikeInfo;
import com.showyourselfblog.server.entity.PostInfo;
import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.util.JWTUtil;
import com.showyourselfblog.server.util.Responce;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @Description 点赞等行为接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-11 23:50
 **/
@RestController
public class Action {

    @Resource
    UserInfoDao userDao;
    @Resource
    LikeInfoDao likeDao;
    @Resource
    CommentsInfoDao comDao;
    @Resource
    PostInfoDao postDao;

    @PostMapping("/doLike")
    public Responce doLike(@RequestBody Map<String,String> map, HttpServletRequest req){
        String phone= (String) JWTUtil.getClaim(req.getHeader("jwt")).get("phone");
        String tid=map.get("tid");
        boolean isLike=Boolean.parseBoolean(map.get("like"));
        UserInfo user=userDao.findById(phone).get();
        LikeInfo like=likeDao.findByUserTid(Integer.valueOf(tid),user.getUserId());
        PostInfo postInfo=postDao.findById(Integer.parseInt(tid)).get();
        if (like==null||!like.isTrue()){
            LikeInfo newLike=new LikeInfo();
            newLike.setTid(Integer.parseInt(tid));
            newLike.setUpOrUn(isLike);
            newLike.setUserId(user.getUserId());
            newLike.setTrue(true);
            newLike.setId(like.getId());
            likeDao.save(newLike);

            postInfo.setNOfLike(postInfo.getNOfLike()+(isLike ?1:0));
            postInfo.setNOfUn(postInfo.getNOfUn()+(!isLike?1:0));
        }else if (like.isTrue()){
            if (isLike==like.isUpOrUn()){
                postInfo.setNOfLike(postInfo.getNOfLike()+(isLike ?-1:0));
                postInfo.setNOfUn(postInfo.getNOfUn()+(!isLike?-1:0));
                like.setTrue(false);
            }else {
                postInfo.setNOfLike(postInfo.getNOfLike()+(isLike ?1:0));
                postInfo.setNOfUn(postInfo.getNOfUn()+(!isLike?1:0));
                like.setUpOrUn(!like.isUpOrUn());
            }
            likeDao.save(like);
        }
        postDao.save(postInfo);
        return new Responce(1001);
    }

    @PostMapping("/upCom")
    public Responce doCom(@RequestBody Map<String,String> param,HttpServletRequest req){
        String tid=param.get("tid");
        String text=param.get("text");
        CommentsInfo coms=new CommentsInfo();
        PostInfo post=postDao.findById(Integer.parseInt(tid)).get();
        coms.setTid(tid);
        coms.setTime(new Timestamp(System.currentTimeMillis()));
        coms.setText(text);
        String userId=(String) JWTUtil.getClaim(req.getHeader("jwt")).get("userId");
        coms.setUserId(userId);
        comDao.save(coms);
        post.setNOfCom(post.getNOfCom()+1);
        postDao.save(post);
        return new Responce(1001);
    }
}
