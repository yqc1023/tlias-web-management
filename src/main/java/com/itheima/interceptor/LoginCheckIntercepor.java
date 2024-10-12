package com.itheima.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class LoginCheckIntercepor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginCheckIntercepor.class);

    @Override//目标资源方法运行前运行,返回值如果为true:放行,返回false,不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle 运行了");

        //获取请求url
        String reqUrl = request.getRequestURL().toString();
        log.info("获取请求路径:{}",reqUrl);



        //判断请求url中是否包含login,如果包含,说明是登录操作,放行
        if (reqUrl.contains("/login")) {
            return true;
        }

        //获取请求头中的令牌(token)
        String jwt = request.getHeader("token");
        //判断令牌是否存在,如果不存在,返回错误结果(未登录)

        if (!StringUtils.hasLength(jwt)){
            log.info("令牌不存在,返回错误信息,false");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象---json ---------->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
           return false;
        }
        //解析token,如果解析失败,返回错误结果(未登录)
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析出错,返回错误信息,false");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 要求返回json格式,我们只有Result格式,无法像Controller层一样直接return,
            // 所以使用 ---------->阿里巴巴fastJSON(导入依赖)  进行手动转换
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        //放行
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override   //视图渲染完毕后运行,最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
