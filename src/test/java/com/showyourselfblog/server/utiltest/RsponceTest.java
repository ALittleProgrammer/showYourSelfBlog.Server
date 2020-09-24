package com.showyourselfblog.server.utiltest;

import com.showyourselfblog.server.util.GetMsg;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

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
        logger.info(GetMsg.GetMsgs(1001));
    }
}
