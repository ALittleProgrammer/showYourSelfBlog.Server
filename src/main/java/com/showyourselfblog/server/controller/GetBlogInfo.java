package com.showyourselfblog.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.showyourselfblog.server.dao.*;
import com.showyourselfblog.server.entity.*;
import com.showyourselfblog.server.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description 获取博文信息控制层
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-10 13:58
 **/
@RestController
public class GetBlogInfo {

    @Resource
    CommentsInfoDao comDao;

    @Resource
    PostInfoDao postDao;
    @Resource
    RedisUtil redisUtil;
    @Resource
    UserInfoDao userDao;
    @Resource
    LikeInfoDao likeDao;

    @Value("${blog.conf.listName}")
    String listName;

    @Resource
    LookInfoDao lookDao;

    @Value("${blog.conf.list.blogNum}")
    int blogNum;

    @GetMapping("/getList")
    public ResponceLi getList(HttpServletResponse resp, HttpServletRequest req){
        if (redisUtil.isValid(listName)){
            return new ResponceLi(1001,JSONObject.parseArray(redisUtil.get(listName)));
        }
        List<PostInfo> list=new ArrayList<>();
        list=postDao.getList(PageRequest.of(0,10));
        JSONArray array=new JSONArray();
        for (int i=0;i<blogNum&&i<list.size();i++)
        {
            Map<String,Object> temp=MapBeanUtil.objectToMap(list.get(i));
            UserInfo auth=userDao.getuUerName((String) temp.get("userId"));
            temp.put("username",auth.getUsername());
            temp.put("userPic",auth.getUserPic());
            temp.put("userLv",auth.getUserLv());
            temp.remove("text");
            temp.remove("userId");
            array.add(temp);
        }
        redisUtil.add(listName,array.toJSONString(), (long) (24*60*60*1000));
        return new ResponceLi(1001,array);
    }

    @PostMapping("/readOne")
    public Responce readOne(@RequestBody HashMap<String, String> map, HttpServletRequest req, HttpServletResponse resp){
        JSONArray array=new JSONArray();
        String tid=map.get("tid");
        comDao.findByTid(tid).forEach(c->{
            ReCommit temp=new ReCommit();
            temp.setUsername(userDao.getuUerName(c.getUserId()).getUsername());
            temp.setText(c.getText());
            temp.setTime(c.getTime());
            array.add(temp);
        });
        JSONObject resData=(JSONObject) JSONObject.toJSON(postDao.findById(Integer.valueOf(tid)));
        UserInfo auth=userDao.getuUerName((String) resData.get("userId"));

        PostInfo postInfo=postDao.findById(Integer.parseInt(tid)).get();
        postInfo.setNOfWat(postInfo.getNOfWat()+1);
        postDao.save(postInfo);

        LikeInfo likeInfo=likeDao.findByUserTid(Integer.valueOf(tid),auth.getUserId());
        resData.put("auth",auth.getUsername());
        resData.put("authLv",auth.getUserLv());
        resData.put("authPic",auth.getUserPic());
        if (likeInfo!=null){
            if (likeInfo.isTrue()){
                resData.put("isLike",likeInfo.isUpOrUn());
            }
        }
        resData.put("commit",array);
        Optional<LookInfo> lookInfo=lookDao.findByUserTId(Integer.parseInt(tid),auth.getUserId());
        LookInfo look = lookInfo.orElseGet(LookInfo::new);
        look.setLookTime(new Timestamp(System.currentTimeMillis()));
        look.setTid(Integer.parseInt(tid));
        look.setUserId(auth.getUserId());
        lookDao.save(look);
        return new Responce(1001,resData);
    }

    @PostMapping("/search")
    public ResponceLi search(@RequestBody HashMap<String,String[]> map,HttpServletResponse resp,HttpServletRequest req){
        String[] keyWords =map.get("keyWord");
        JSONArray list=new JSONArray();
        for (String i:keyWords){
            List<PostInfo> postList= postDao.search(i);
            AtomicReference<UserInfo> temp=new AtomicReference<>();
            AtomicReference<Map<String, Object>> mapTemp = new AtomicReference<>();
            postList.forEach(c->{
                mapTemp.set(MapBeanUtil.objectToMap(c));
                temp.set(userDao.getuUerName((String) mapTemp.get().get("userId")));
                mapTemp.get().put("auth",temp.get().getUsername());
                mapTemp.get().put("authLv",temp.get().getUserLv());
                mapTemp.get().put("authPic",temp.get().getUserPic());
                mapTemp.get().remove("userId");
                list.add(mapTemp);
            });
        }
        return new ResponceLi(1001,list);
    }

    @GetMapping("/getMyBlog")
    public ResponceLi getBlog(HttpServletRequest req){
        List<PostInfo> postList=postDao.getByAuth((String) JWTUtil.getClaim(req.getHeader("jwt")).get("userId"));

        JSONArray array = new JSONArray();
        if (postList.size()==0){
            array=null;
        }else {
            array.addAll(postList);
        }
        return new ResponceLi(1001,array);
    }
}
