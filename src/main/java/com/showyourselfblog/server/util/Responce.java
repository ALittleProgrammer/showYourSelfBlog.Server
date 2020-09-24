package com.showyourselfblog.server.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 返回数据实体
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-20 13:03
 **/
public class Responce {
    int code;
    String msg;
    JSONObject data;
    boolean success;

    Responce(int Ecode){
        code=Ecode;
        GetMsg.GetMsgs(code);
    }


}
