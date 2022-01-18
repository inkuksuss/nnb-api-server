package com.smartFarm.was.web.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

@Slf4j
@Aspect
public class AuthenticationAspect {

    @Before("@annotation(com.smartFarm.was.web.aop.annotation.Authentication)")
    public void doBefore(JoinPoint joinPoint) {
    }

    @AfterReturning(value = "@annotation(com.smartFarm.was.web.aop.annotation.Authentication)", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
    }

    @AfterReturning(value = "@annotation(com.smartFarm.was.web.aop.annotation.Authentication)", returning = "result")
    public void doReturn(JoinPoint joinPoint, String result) {
    }

    @AfterThrowing(value = "@annotation(com.smartFarm.was.web.aop.annotation.Authentication)", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", ex);
    }

    @After(value = "@annotation(com.smartFarm.was.web.aop.annotation.Authentication)")
    public void doAfter(JoinPoint joinPoint) {

    }
}
