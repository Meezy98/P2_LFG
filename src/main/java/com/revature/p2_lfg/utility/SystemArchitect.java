package com.revature.p2_lfg.utility;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("systemArchitect")
@Aspect
public class SystemArchitect {


    @Pointcut("within(com.revature.p2_lfg.presentation.controllers..*)")
    public void inController(){}

    @Pointcut("within(com.revature.p2_lfg.service..*)")
    public void inService(){}

    @Pointcut("within(com.revature.p2_lfg.repository..*)")
    public void publicOperations() {}

    @Pointcut("execution(* com.revature.p2_lfg.service..*.*(..))")
    public void businessService(){}

    @Pointcut("execution(* com.revature.p2_lfg.repository..*.*(..))")
    public void dataAccessOperation(){}

    @Pointcut("execution(* com.revature.p2_lfg.presentation..*.*(..))")
    public void controller(){}


}
