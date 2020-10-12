package com.showyourselfblog.server.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Description data是list版的返回值
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-10 15:26
 **/
@Data
public class ResponceLi {
    int code;
    String msg;
    JSONArray data;
    boolean success;

    public ResponceLi(int ecode) {
        code=ecode;
        msg=GetMsg.GetMsgs(code);
        success= "OK".equalsIgnoreCase(msg);
    }

    public ResponceLi(int ecode,JSONArray data1){
        data=data1;
        code=ecode;
        msg=GetMsg.GetMsgs(code);
        success= "OK".equalsIgnoreCase(msg);
    }
}
