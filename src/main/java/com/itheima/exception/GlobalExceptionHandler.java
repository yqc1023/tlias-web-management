package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice//定义此类为全局异常处理器
//用于处理各层中抛出的异常,并统一返回和抛出
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//指定要捕获的异常类型   Exception.class代表所有异常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起,操作失败,请联系管理员");
    }

}
