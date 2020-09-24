package com.showyourselfblog.server.utiltest;

import com.showyourselfblog.server.util.MD5;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description md5测试
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:24
 **/
@SpringBootTest
public class Md5Test {
    Logger log= LoggerFactory.getLogger(this.getClass());
    @Test
    void md5Test() throws Exception {
         log.info(MD5.md5("pjk") );
    }
}
