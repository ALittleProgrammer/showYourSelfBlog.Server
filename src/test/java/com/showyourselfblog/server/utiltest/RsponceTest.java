package com.showyourselfblog.server.utiltest;

import com.showyourselfblog.server.util.GetMsg;
import com.showyourselfblog.server.util.Responce;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Description Responce测试
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-20 22:22
 **/
@SpringBootTest
public class RsponceTest {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Test
    void test(){
        logger.info(new Responce(1002).toString());
    }
}
