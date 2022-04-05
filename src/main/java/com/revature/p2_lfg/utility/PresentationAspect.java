package com.revature.p2_lfg.utility;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("presentationAspect")
@Aspect
public class PresentationAspect {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Before("com.revature.p2_lfg.utility.SystemArchitect.controller()")
    public void beforeServiceCheck(JoinPoint jp){
        dLog.debug("Class: " + jp.getSignature().getDeclaringType() + "\nMethod: " + jp.getSignature().getName());
    }

    @After("com.revature.p2_lfg.utility.SystemArchitect.controller()")
    public void afterServiceCheck(JoinPoint jp){
        //dLog.debug(jp.getSignature().getName());
    }

    @AfterReturning(value = "com.revature.p2_lfg.utility.SystemArchitect.controller()", returning = "returnedValue")
    public void afterServiceCheck(JoinPoint jp, Object returnedValue){
        if(returnedValue != null){
            dLog.info("Class: " + jp.getSignature().getDeclaringType() + "\nReturning: " + returnedValue.toString());
            iLog.info("Class: " + jp.getSignature().getDeclaringType() + "\nReturning: " + returnedValue.toString());
        }else{
            dLog.info("Class: " + jp.getSignature().getDeclaringType() + "\nReturning: null");
            iLog.info("Class: " + jp.getSignature().getDeclaringType() + "\nReturning: null");
        }
    }

    @AfterThrowing(value = "com.revature.p2_lfg.utility.SystemArchitect.controller()", throwing = "thrownException")
    public void afterThrowingCheck(JoinPoint jp, Object thrownException){
        dLog.error("Class: " + jp.getSignature().getDeclaringType() + "\nThrowing: " + thrownException.toString());
    }

}
