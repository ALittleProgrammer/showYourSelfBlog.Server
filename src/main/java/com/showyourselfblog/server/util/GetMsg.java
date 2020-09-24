package com.showyourselfblog.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 获取错误码对应的MSG
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-20 21:58
 **/
public class GetMsg {
    static Map<Integer,String> map=new HashMap<Integer,String>(16);
    public static String GetMsgs(int code){
        if (map.isEmpty()){
            mapInit();
        }
        return map.get(code);
    }

    private static void mapInit(){
        map.put(1001,"ok");
        map.put(1002,"身份过期/异常，需要重新登陆");
        map.put(1003,"密码错误");
        map.put(1004,"账号不存在");
        map.put(1005,"账号被封禁");
        map.put(1006,"验证码错误");
        map.put(1007,"手机号不存在");
        map.put(1008,"手机号已被注册");
    }

}
