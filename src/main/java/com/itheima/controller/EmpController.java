package com.itheima.controller;


import com.itheima.anno.MyLog;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result selectEmpsAll(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam String name,
                                Short gender,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询员工信息");
        PageBean pagebean = empService.selectLimitSelectList(page, pageSize,name,gender,begin,end);
        return Result.success(pagebean);
    }
    @MyLog
    @DeleteMapping("/{ids}")
    public Result deleteEmps(@PathVariable List<Integer> ids) {
        log.info("删除员工信息");
        empService.deleteEmp(ids);
        return Result.success();
    }

    /*@PostMapping("/upload")
    public Result upload(MultipartFile file){
        empService.upload(file);
        return Result.success();
    }*/


    @MyLog
    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工信息");
        empService.insertEmp(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectEmpById(@PathVariable Integer id) {
        log.info("根据id为:{}查询指定用户",id);
        Emp emp = empService.selectEmpById(id);
        return Result.success(emp);
    }
    @MyLog
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp){
        log.info("获得修改后的数据:{}",emp);
        empService.updateEmp(emp);
        return Result.success();
    }

}
