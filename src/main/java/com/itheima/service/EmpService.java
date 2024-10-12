package com.itheima.service;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface EmpService {

    PageBean selectLimitSelectList(Integer page, Integer pageSize, String name, Short gender,  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);
    void deleteEmp(List<Integer> ids);
    void insertEmp(Emp emp);



    Emp selectEmpById(Integer id);

    void updateEmp(Emp emp);

    Emp Login(Emp emp);
}
