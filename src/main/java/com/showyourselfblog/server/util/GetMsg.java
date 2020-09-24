package com.showyourselfblog.server.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 获取错误码对应的MSG
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-20 21:58
 **/
@Component
public class GetMsg {

    static String path;
    static Map<Integer,String> map=new HashMap<Integer,String>(16);

    public static String GetMsgs(int code) throws IOException {
        if (map.isEmpty()){
            mapInit();
        }
        return map.get(code);
    }

    private static void mapInit() throws IOException {

        FileReader fr=new FileReader(path);
        BufferedReader reader=new BufferedReader(fr);
        String line;
        String[] msgs;

        while((line=reader.readLine())!=null){
            line.replace(" ","");
            if (line.charAt(0)=='#'){
                continue;
            }
            line=line.replace(" ","");
            msgs=line.split("=");
            map.put(Integer.valueOf(msgs[0]),msgs[1]);
        }
    }

    @Value(value = "${blog.conf.msg.path}")
    void setPath(String path1){
        path=path1;
    }

}
