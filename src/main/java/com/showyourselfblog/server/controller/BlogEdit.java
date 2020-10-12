package com.showyourselfblog.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.showyourselfblog.server.dao.PostInfoDao;
import com.showyourselfblog.server.dao.UserInfoDao;
import com.showyourselfblog.server.entity.PostInfo;
import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.util.JWTUtil;
import com.showyourselfblog.server.util.MD5;
import com.showyourselfblog.server.util.Responce;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 博文编辑类
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-11 13:38
 **/
@RestController
public class BlogEdit {

    @Value("${blog.conf.img.path}")
    String path;

    @Value("${blog.conf.img.url}")
    String url;

    @Value("${blog.conf.userPic.path}")
    String usrPicPath;

    @Resource
    PostInfoDao postDao;

    @Resource
    UserInfoDao userDao;

    @PostMapping("/blogCommit")
    public Responce blogCommit(@RequestBody Map<String,String> map, HttpServletRequest req, HttpServletResponse resp){
        String jwt=req.getHeader("jwt");
        Boolean c=JWTUtil.verify(jwt);
        System.out.println(c);
        String userId= (String) JWTUtil.getClaim(jwt).get("userId");
        PostInfo postInfo=new PostInfo();
        postInfo.setUserId(userId);
        postInfo.setTitle((String) map.get("tittle"));
        postInfo.setIntro((String)map.get("intro"));
        postInfo.setText((String) map.get("text"));
        postInfo.setUptime(new Timestamp(System.currentTimeMillis()));
        postDao.save(postInfo);
        return new Responce(1001);
    }

    @PostMapping("/upUserPic")
    public Responce upUsrPic(@RequestParam("file") MultipartFile file,HttpServletRequest req){
        Responce res=getResponce(file, req, usrPicPath);
        UserInfo user=userDao.findById((String)JWTUtil.getClaim(req.getHeader("jwt")).get("phone")).get();
        user.setUserPic((String) res.getData().get("url"));
        userDao.save(user);
        return res;
    }

    private Responce getResponce(@RequestParam("file") MultipartFile file, HttpServletRequest req, String usrPicUrl) {
        String oldName=file.getOriginalFilename();
        String imgType = oldName.substring(oldName.lastIndexOf("."));
        String userId= (String) JWTUtil.getClaim(req.getHeader("jwt")).get("userId");
        String realName= MD5.md5(userId+ UUID.randomUUID().toString())+imgType;
        String fileName=writeUploadFile(file,usrPicUrl,realName);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("url", url+usrPicUrl.split("/")[usrPicUrl.split("/").length-1]+"/"+fileName);
        return new Responce(1001,jsonObject);
    }


    @PostMapping("/upPic")
    public Responce upPic(@RequestParam("file") MultipartFile file,HttpServletRequest req){
        return getResponce(file, req, url);
    }

    String writeUploadFile(MultipartFile file, String realpath, String fileName) {
        File fileDir = new File(realpath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(realpath + "/" + fileName);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return fileName;
    }
}
