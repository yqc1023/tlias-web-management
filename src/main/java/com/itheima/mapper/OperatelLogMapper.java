package com.itheima.mapper;

import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperatelLogMapper {

    @Insert("insert into operate_log ( operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) VALUES (#{operateUser},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insert(OperateLog operateLog);

}
