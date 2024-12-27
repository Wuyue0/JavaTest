package com.zixue.aop;

import com.alibaba.fastjson.JSON;
import com.zixue.mapper.OperateLogMapper;
import com.zixue.pojo.OperateLog;
import com.zixue.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private HttpServletRequest  request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.zixue.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
         // 操做人的id
         // 获取请求头令牌
         String jwt =  request.getHeader("token");
         Claims claims = JwtUtils.parseJwt(jwt);
         Integer operateUser = (Integer) claims.get("id");
         log.info("jwt:{}", claims);
        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();
        // 操作方法名
        String mehtodName = joinPoint.getSignature().getName();
        // 方法参数
        Object[] args = joinPoint.getArgs();
        String  methodParams = Arrays.toString(args);
        // 计算耗时
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        // 返回值
        String returnValue = JSON.toJSONString(result);
        // 执行耗时
        Long costTime = end - begin;
        // 初始化对象
        OperateLog  operateLog = new OperateLog(null, operateUser, operateTime, className, mehtodName, methodParams, returnValue, costTime);
        log.info("插入的对象", operateLog);
        // 记录操作日志
        operateLogMapper.insert(operateLog);
        log.info("日志记录完毕");
        return result;
    }
}
