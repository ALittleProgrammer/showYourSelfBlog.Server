package com.showyourselfblog.server.controller;

import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.util.Mes;
import com.showyourselfblog.server.util.RedisUtil;
import com.showyourselfblog.server.util.Responce;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @Description 获取验证码接口
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-08 20:07
 **/
@RestController

public class GetMes {
    @Resource
    RedisUtil redisUtil;

    @PostMapping("/getMes")
    public Responce getMessage(@RequestBody UserInfo user, HttpServletRequest req, HttpServletResponse resp){
        String phoneNotFound ="isv.MOBILE_NUMBER_ILLEGAL";
        String ok="OK";
        String businessLimit="isv.BUSINESS_LIMIT_CONTROL";
        String code=String.valueOf((new Random().nextInt(899998))+100001);
        String resData=Mes.senMes(user.getPhone(),code);
        if (resData.contains(ok))
        {
            redisUtil.add(user.getPhone(),code,Mes.life);
            return new Responce(1001);
        }
        if (resData.contains(phoneNotFound)){
            return new Responce(1012);
        }
        if (resData.contains(businessLimit))
        {
            return new Responce(1013);
        }
        return new Responce(1011);
    }
}
