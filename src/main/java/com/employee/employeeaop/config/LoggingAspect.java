package com.employee.employeeaop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

/*  @Pointcut(value= "execution(* com.employee.employeeaop.service.EmployeeService.*(..))")
  public void logPointcutMethod(JoinPoint joinPoint) {
    logger.info("Pointcut: Method Called: " + joinPoint.getSignature().getName());
  }*/


 /* @Pointcut("execution(* com.employee.employeeaop.service.EmployeeService.*")
  public void myMethodPointcut() {
    System.out.println("test===================");
  }*/


  @Pointcut(value = "execution(* com.employee.employeeaop.service.EmployeeService.*(..))")
  private void logDisplayingBalance() {

  }

  //Declares the around advice that is applied before and after the method matching with a pointcut expression
  @Around(value = "logDisplayingBalance()")
  public void aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
    System.out.println("The method aroundAdvice() before invokation of the method " + jp.getSignature().getName() + " method");
    try {
      jp.proceed();
    } finally {

    }
    System.out.println("The method aroundAdvice() after invokation of the method " + jp.getSignature().getName() + " method");
  }

  @Before("execution(* com.employee.employeeaop.service.EmployeeService.*(..))")
  public void logBeforeMethod(JoinPoint joinPoint) {
    logger.info("Before: Method Called: " + joinPoint.getSignature().getName());
  }

  @Before(value = "execution(* com.employee.employeeaop.service.EmployeeService.*(..)) && args(id)")
  public void logBeforeMethod1(JoinPoint joinPoint, Long id) {
    logger.info("Before: Method with args Called: " + joinPoint.getSignature().getName());
  }

  @After(value = "execution(* com.employee.employeeaop.service.EmployeeService.*(..)) && args(id)")
  public void logAfterMethod(JoinPoint joinPoint, Long id) {
    logger.info("After Method Called: " + joinPoint.getSignature().getName());
  }


  @AfterReturning(value = "execution(* com.employee.employeeaop.service.EmployeeService.*(..))", returning = "result")
  public void logAfterMethod(JoinPoint joinPoint, Object result) {
    System.out.println("AfterReturning========");
    logger.info("AfterReturning Method Executed: " + joinPoint.getSignature().getName());
    logger.info("AfterReturning Result: " + result);
  }


  @AfterThrowing(value="execution(* com.employee.employeeaop.service.EmployeeService.*(..))",throwing="ex")
  public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex)
  {
    System.out.println("After Throwing exception in method:"+joinPoint.getSignature());
    System.out.println("Exception is:"+ex.getMessage());
  }
}
