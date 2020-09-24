package com.showyourselfblog.server.daotest;

import com.showyourselfblog.server.dao.UserIndoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description userIndoDao测试类
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 14:36
 **/
@SpringBootTest
public class UserIndoTest {
    @Autowired
    UserIndoDao userIndoDao;

    @Test
    void testCon(){
        userIndoDao.findAll().forEach(System.out::println);
    }
}
