package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {
     List<Dept> selectDeptAll();//查询部门数据
     void deptDelete(Integer id);//根据id删除部门
     void deptInsert(Dept dept);//新增部门
     Dept selectDeptById(Integer id);//根据id查询部门
     void updateDept(Dept dept);


}
