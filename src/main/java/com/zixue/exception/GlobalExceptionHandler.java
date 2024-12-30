package com.zixue.exception;


import com.zixue.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 捕获所有的异常
    public Result exception(Exception e){
        e.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
