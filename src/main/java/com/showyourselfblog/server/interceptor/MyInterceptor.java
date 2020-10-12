package com.showyourselfblog.server.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.showyourselfblog.server.util.JWTUtil;
import com.showyourselfblog.server.util.Responce;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @Description 过滤器类
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-26 18:01
 **/
@Configuration
public class MyInterceptor implements HandlerInterceptor {

    String[] apiMap=new String[]{"/login","/regist","/forget","/error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt=request.getHeader("jwt");
        String url=request.getRequestURI();
        url="/"+url.split("/")[url.split("/").length-1];
        for (String i:apiMap){
            if (i.equalsIgnoreCase(url)){
                return true;
            }
        }
        if (!JWTUtil.isExpired(jwt)){
            if (JWTUtil.beUpdate(jwt)){
                String newJwt=JWTUtil.generate(JWTUtil.getClaim(jwt));
                response.setHeader("jwt",newJwt);
            }
            return true;
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter writer=response.getWriter();
        writer.println(JSONObject.toJSONString(new Responce(1002)));
        writer.flush();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
