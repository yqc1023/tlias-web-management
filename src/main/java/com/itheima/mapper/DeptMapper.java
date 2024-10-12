package com.itheima.mapper;


import com.itheima.anno.MyLog;
import com.itheima.pojo.Dept;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

     @Select("select id, name, create_time, update_time from dept")
     List<Dept> selectDeptAll();


     void deleteDeptById(Integer id);


     @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
     void insertDept(Dept dept);

     @Select("select * from dept where id=#{id}")
     public Dept selectDeptById(int id);




     public void unpdateDept(Dept dept);
}
