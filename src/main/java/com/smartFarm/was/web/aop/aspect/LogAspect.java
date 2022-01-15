package com.smartFarm.was.web.aop.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class LogAspect {

    @Before("com.smartFarm.was.web.aop.Pointcuts.allCSR()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Arrays.stream(joinPoint.getArgs())
                .forEach(o -> System.out.println("arg value: " + o.toString()));
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.smartFarm.was.web.aop.Pointcuts.allCSR()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "com.smartFarm.was.web.aop.Pointcuts.allCSR()", returning = "result")
    public void doReturn(JoinPoint joinPoint, String result) {
        log.info("[return2] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.smartFarm.was.web.aop.Pointcuts.allCSR()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", ex);
    }

    @After(value = "com.smartFarm.was.web.aop.Pointcuts.allCSR()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}


