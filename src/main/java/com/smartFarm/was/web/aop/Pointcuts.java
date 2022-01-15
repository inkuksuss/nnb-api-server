package com.smartFarm.was.web.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.smartFarm.was.web.repository..*(..))")
    public void allRepository() {}

    @Pointcut("execution(* com.smartFarm.was.web.service..*(..))")
    public void allService() {}

    @Pointcut("execution(* com.smartFarm.was.web.controller..*(..))")
    public void allController() {}

    @Pointcut("allRepository() || allService() || allController()")
    public void allCSR() {}
}
