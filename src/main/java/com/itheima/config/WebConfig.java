package com.itheima.config;

import com.itheima.interceptor.LoginCheckIntercepor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//声明此类为配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LoginCheckIntercepor loginCheckIntercepor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckIntercepor)//添加一个拦截器(就是先前定义好的LoginCheckIntercepor,使用方法注入调用)
                .addPathPatterns("/**");//定义拦截器需要拦截复资源
                //.excludePathPatterns("/login");//定义拦截器不需要拦截的资源
    }
}
