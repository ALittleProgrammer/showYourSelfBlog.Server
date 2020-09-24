package com.showyourselfblog.server.util;
import org.springframework.util.DigestUtils;

/**
 * @Description MD5加密类
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-19 18:14
 **/
public class MD5 {
    /**
     * MD5方法
     *
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) throws Exception {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String md5) throws Exception {
        return md5.equalsIgnoreCase(md5(text));
    }
}