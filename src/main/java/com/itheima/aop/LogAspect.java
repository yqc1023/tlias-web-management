package com.itheima.aop;


import com.alibaba.fastjson.JSONObject;
import com.itheima.interceptor.LoginCheckIntercepor;
import com.itheima.mapper.OperatelLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect//定义为切面类
@Slf4j
@Component
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperatelLogMapper operatelLogMapper;

    @Pointcut("@annotation(com.itheima.anno.MyLog)")
    private void pt(){}


    @Autowired
    private LoginCheckIntercepor loginCheckIntercepor;

    @Around("pt()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer)claims.get("id");


        String className = joinPoint.getTarget().getClass().getName();
        log.info(" 目标对象的类名:{}",className);

        String methodName = joinPoint.getSignature().getName();
        log.info("目标方法的方法名:{}",methodName);


        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        log.info("目标对象运行时传入的参数:{}",methodParams);

        long star = System.currentTimeMillis();

        Object reslut = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(reslut);

        long end = System.currentTimeMillis();
        long costTime = end - star;

        OperateLog log = new OperateLog(null,operateUser,LocalDateTime.now(),className,methodName,methodParams,returnValue,costTime);

        operatelLogMapper.insert(log);



        return reslut;
    }
}
