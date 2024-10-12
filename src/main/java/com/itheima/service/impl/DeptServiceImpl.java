package com.itheima.service.impl;


import com.itheima.anno.MyLog;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.pojo.Emp;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;
    @Override
    public List<Dept> selectDeptAll() {
        List<Dept> depts = deptMapper.selectDeptAll();
        return depts;
    }

    @Transactional(rollbackFor = Exception.class)
    //进行事务管理,方法执行前,开启事务;
    //成功执行完毕,提交事务;出现异常,回滚事务
    //只能回滚属于RuntimeException的异常事务,否则回滚失效
    //可以在后面加上rollbackFor,用于声明出现什么异常时事务会回滚(Exception.class代表所有异常)


    @Override
    public void deptDelete(Integer id) {
        //根据部门id删除部门及其关联的员工数据
        try {
            empMapper.deleteByDeptId(id);
            deptMapper.deleteDeptById(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了删除部门的操作,此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }


    }



    @Override
    public void deptInsert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insertDept(dept);
    }

    @Override
    public Dept selectDeptById(Integer id) {
        Dept dept = deptMapper.selectDeptById(id);
        return dept;
    }


    @Override
    public void updateDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.unpdateDept(dept);
    }




}
