package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//表示次注解在运行时生效
@Target(ElementType.METHOD)//表示次注解作用在方法上
public @interface MyLog {
}
