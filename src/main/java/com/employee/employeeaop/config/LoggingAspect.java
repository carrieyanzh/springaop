package com.employee.employeeaop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.employee.employeeaop.service.EmployeeService.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Method Called: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.aop.employee.service.EmployeeService.*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        logger.info("Method Executed: " + joinPoint.getSignature().getName());
        logger.info("Result: " + result);
    }
}