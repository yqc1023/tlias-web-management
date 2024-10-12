package com.itheima.filter;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求url
        String requrl = request.getRequestURL().toString();
        log.info("请求路径的url:{}",requrl);


        //判断请求url中是否包含login,如果包含,说明是登录操作,放行
       if (requrl.contains("login")) {
           filterChain.doFilter(request, response);
           return;
       }


        //获取请求头中的令牌(token)
        //使用jwt的工具类,里面有解析jwt的方法
        //方法传递的jwt被封装在request中,调用getHeader()获取,期中getHeader()方法参数为此令牌的名字(token)
        String jwt = request.getHeader("token");

        //判断令牌是否存在,如果不存在,返回错误结果(未登录)
        if (!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象---json ---------->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);


            response.getWriter().write(notLogin);
            return;

        }
        //解析token,如果解析失败,返回错误结果(未登录)
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }
        //放行
        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
