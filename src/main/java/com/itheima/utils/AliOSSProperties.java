package com.itheima.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
//@ConfigurationProperties可以自动识别和匹配在配置文件中定义好的值,需要在参数中声明此配置数值在配置文件中的归属key
//使用@Component将此oss配置数值封装入实体类并将bean放进容器中
//需要时直接调用bean,使用get方法获取
public class AliOSSProperties {
    private  String endpoint ;
    private  String bucketName ;
}
