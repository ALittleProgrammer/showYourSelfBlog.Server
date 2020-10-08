package com.showyourselfblog.server.utiltest;

import com.showyourselfblog.server.entity.UserInfo;
import com.showyourselfblog.server.util.MapBeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description 测试Map和实体互相转化工具
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-25 14:59
 **/
@SpringBootTest
public class MapBeanUtilTest {
    @Test
    void mapToBeanTest(){
        UserInfo user=new UserInfo();
        user.setPhone("18070554018");
        user.setPassword("cacasc");
        MapBeanUtil.objectToMap(user).forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }
}
