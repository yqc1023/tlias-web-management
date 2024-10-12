package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.MyLog;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    private static final Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean selectLimitSelectList(Integer page, Integer pageSize, String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.selectEmp(name,gender,begin,end);
        Page<Emp> p=(Page<Emp>) empList;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }


    @Override
    public void deleteEmp(List<Integer> ids) {
        empMapper.deleteEmp(ids);
    }


    @Override
    public void insertEmp(Emp emp) {

        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insertEmp(emp);

    }



    @Override
    public Emp selectEmpById(Integer id) {
        Emp emp = empMapper.selectEmpById(id);
        return emp;
    }


    @Override
    public void updateEmp(Emp emp) {

        emp.setUpdateTime(LocalDateTime.now());

        empMapper.updateEmp(emp);
    }

    @Override
    public Emp Login(Emp emp) {
        return empMapper.getUsernameAndPassword(emp);
    }


}
