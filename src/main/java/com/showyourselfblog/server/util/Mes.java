package com.showyourselfblog.server.util;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

/**
 * @Description 发送短信工具
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-10-08 12:20
 **/
@Component
public class Mes {
    @Resource
    private RedisUtil redisUtil;
    public static Mes mes;
    static String accessId;
    static String accessSecret;
    public static long life;
    static Logger logger= LoggerFactory.getLogger(Mes.class);

    @PostConstruct
    public void init() {
        mes = this;
        mes.redisUtil = this.redisUtil;
    }

    static public String senMes(String phoneNumber,String code){
        String res=null;
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "86"+phoneNumber);
        request.putQueryParameter("SignName", "showYourSelf");
        request.putQueryParameter("TemplateCode", "SMS_204117148");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(response.getData());
            res=response.getData();
        } catch (ClientException e) {
            logger.error(e.toString());
        }
        return res;
    }

    static public boolean isValid(String phone,String checkNum){
        return checkNum!=null&&Mes.mes.redisUtil.get(phone)!=null&&Mes.mes.redisUtil.get(phone).equals(checkNum);
    }

    @Value("${blog.conf.mes.accessId}")
    void setAccessId(String accessId1){
        accessId=accessId1;
    }

    @Value("${blog.conf.mes.accessSec}")
    void setAccessSecret(String accessSecret1){
        accessSecret=accessSecret1;
    }

    @Value("${blog.conf.mes.life}")
    void setLife(long life1){
        life=life1;
    }
}
