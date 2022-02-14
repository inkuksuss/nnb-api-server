package com.smartFarm.was.web.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class getMemberIdAspect {

    @Around("@annotation(com.smartFarm.was.web.aop.annotation.GetMemberId)")
    public void setMemberIdAtRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Integer credentials = (Integer) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        long memberId = credentials.longValue();
        Object proceed = proceedingJoinPoint.proceed();
        proceed.

    }

//    @After(value = "@annotation(com.smartFarm.was.web.aop.annotation.GetMemberId)", returning = "result")
//    public void doReturn(JoinPoint joinPoint, Object result) {
//    }
//
//    @AfterReturning(value = "@annotation(com.smartFarm.was.web.aop.annotation.GetMemberId)", returning = "result")
//    public void doReturn(JoinPoint joinPoint, String result) {
//    }
//
//    @AfterThrowing(value = "@annotation(com.smartFarm.was.web.aop.annotation.GetMemberId)", throwing = "ex")
//    public void doThrowing(JoinPoint joinPoint, Exception ex) {
//        log.info("[ex] {} message={}", ex);
//    }
//
//    @After(value = "@annotation(com.smartFarm.was.web.aop.annotation.GetMemberId)")
//    public void doAfter(JoinPoint joinPoint) {

//    }
}
