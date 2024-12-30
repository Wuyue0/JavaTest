package com.zixue.controller;


import com.zixue.pojo.Result;
import com.zixue.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    AliOSSUtils aliOSSUtils;
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传:{},{},{}", name, age, image);
//        // 构造唯一的文件名 uuid
//        String originFileName = image.getOriginalFilename();
//        int index = originFileName.lastIndexOf(".");
//        String extname = originFileName.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        image.transferTo(new File("E:\\"+ newFileName));
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("原始文件上传:{}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("oss文件路径的名字:{}", url);
        return Result.success(url);
    }

    @DeleteMapping("/upload/{url}")
    public Result delete(@PathVariable(name="url") String fileName){
        log.info("删除oss文件名字:{}", fileName);
        String result = aliOSSUtils.delete(fileName);
        if(result != null) return Result.error(result);
        return Result.success("删除成功");
    }
}
