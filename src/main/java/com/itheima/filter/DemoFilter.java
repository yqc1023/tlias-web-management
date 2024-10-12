package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;

//在过滤器链中,默认按照过滤器的类名(字符串)的自然排序分优先级  A排在D之前
//请求进入 Abc -> Demo
//响应放出 Demo -> Abc

//@WebFilter(urlPatterns = "/*")//    '/*' 设置拦截所有请求   '/emps/*'拦截/emps路径下的所有资源  '/login'拦截路径为/login的资源
//因为Filter是javaweb三大主件之一,不是springboot的功能,所以得在启动类上加上注解@ServletComponentScan
public class DemoFilter implements Filter {

    @Override//初始化方法,只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化方法执行了");
       //Filter.super.init(filterConfig);
    }

    @Override//每次拦截到请求后都会调用,可调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo拦截到了请求,放行前的逻辑");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);


        System.out.println("Demo拦截到了请求,放行后的逻辑");
    }

    @Override//销毁方法,只调用一次
    public void destroy() {
        System.out.println("destroy 销毁方法执行了");
        //Filter.super.destroy();
    }
}
