package com.itheima.mapper;


import com.itheima.anno.MyLog;
import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //分页查询员工信息
    List<Emp> selectEmp(String name, Short gender,  LocalDate begin,   LocalDate end);

    //删除员工
    void deleteEmp(List<Integer> ids);

    //添加员工

    void insertEmp(Emp emp);

    //根据id查询员工信息

    @Select("select * from emp where id = #{id}")
    Emp selectEmpById(Integer id);

    //根据id修改员工信息

    void updateEmp(Emp emp);


    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);

    //根据部门id删除该部门下的员工数据

    @Delete("delete from emp  where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);


    //分页查询获取列表数据*/
   /* @Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> selectEmpList(Integer start, Integer pageSize);*/
}
