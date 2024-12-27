package com.zixue.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeAspect {

    // 切入点表达式 抽取公共的切入点表达式
    @Pointcut("execution(* com.zixue.service.impl.DeptServiceImpl.*(..))")
    public void pt(){}

    @Before("pt()")
    public void before(){
        log.info("前置通知～before");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕通知前～around～before");
        Object result = joinPoint.proceed();
        log.info("环绕通知后～around～after");
        return result;
    }

    @After("pt()")
    public void after(){
        log.info("方法不管出错还是不出错都会执行～后置通知～after");
    }

    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("方法正常执行后～afterReturning");
    }


    @AfterThrowing(pointcut = "pt()", throwing = "e")
    public void afterThrowing(Exception e){
        log.info("方法出错了～afterThrowing");
    }

}
