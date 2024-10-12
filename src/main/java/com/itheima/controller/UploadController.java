package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    EmpService empService;
    @Autowired
    AliOSSUtils aliOSSUtils;
//本地存储
//很少使用,存储后前端无法之间访问,且一旦磁盘损坏,所有数据都会丢失
//更对哦是使用云存储


//    接收前端传递过来的文件要使用 MultipartFile类型(API)来接收
    /*@PostMapping("/upload")
    public Result upload(String username , Integer age , MultipartFile image) throws IOException {
        log.info("文件上传:{},{},{}",username,age,image);

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一文件名(不能重复) - uuid (通用唯一识别码)
        String uuid = UUID.randomUUID().toString();

        //构建新的文件名
        //使用uuid的唯一标识码和原始文件名中的文件类型(".jpg")拼接成新的文件名
        //避免后续存储文件时文件名相同被覆盖
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName=uuid+extname;

        log.info("获取到的新文件名:{}",newFileName);

        //本地存储
        //将获取到的文件存储到指定的本地位置
        image.transferTo(new File("E:\\images\\"+newFileName));


        return Result.success();*/


    //上传文件的操作,在添加员工时,添加图像时执行,并将upload返回的图象在oss中的url作为添加员工操作中传递的参数
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException, ClientException {
        log.info("文件上传:{}",image.getOriginalFilename());
        //调用阿里云工具类
        String url = aliOSSUtils.upload(image);
        log.info("成功上传,文件访问路径为:{}",url);
        return Result.success(url);
    }



}


