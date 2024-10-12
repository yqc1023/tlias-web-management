package com.itheima.controller;


import com.itheima.anno.MyLog;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j//获取日志记录对象(由lombok提供)
//private static Logger log = LoggerFactory.getLogger(DeptController.class);java自带
@RestController
@RequestMapping("/depts")//将所有url中公共的路径"/depts"抽取到类上
public class DeptController {

    @Autowired
    private DeptService deptService;
    //@RequestMapping(value = "/depts" , method = RequestMethod.GET)//method:限制访问类型
    @GetMapping
    public Result selectDeptAll(){
        log.info("查询全部员工数据");
        List<Dept> depts = deptService.selectDeptAll();
        return Result.success(depts);
    }

    @MyLog
    @DeleteMapping("/{id}")
    public Result deleteDeptById(@PathVariable Integer id){
        log.info("按照id删除部门及其关联的员工数据");
        System.out.println(id);
        deptService.deptDelete(id);
        return Result.success();
    }
    @MyLog
    @PostMapping
    public Result insertDept(@RequestBody Dept dept){
        log.info("新增部门");
        deptService.deptInsert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectDeptById(@PathVariable int id){
        log.info("查询指定部门");
        Dept dept = deptService.selectDeptById(id);
        return Result.success(dept);
    }
    @MyLog
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
        log.info("更新指定部门");
        deptService.updateDept(dept);
        return Result.success();
    }



}
