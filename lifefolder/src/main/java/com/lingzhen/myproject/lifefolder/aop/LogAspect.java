package com.lingzhen.myproject.lifefolder.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    // 语法：execution([访问修饰符] 返回类型 [类路径]方法名称(参数类型) [异常类型])
    // *：匹配任何
    // (..)：匹配任何参数matches any number (zero or more)
    // execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
    @Pointcut("execution(* com.lingzhen.myproject.lifefolder.service.UserService.*(..))")
    public void logPointcut(){}


    @AfterReturning(pointcut = "com.lingzhen.myproject.lifefolder.aop.LogAspect.logPointcut()",
        returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        Object[] obj = joinPoint.getArgs();
        System.out.println("类："+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        System.out.print("参数：");
        for (Object temp : obj) {
            System.out.print(temp+",");
        }
        System.out.println();
        System.out.println("--LogAspect END---");
    }

    @AfterThrowing(pointcut = "com.lingzhen.myproject.lifefolder.aop.LogAspect.logPointcut()",
        throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, DataAccessException e) {
        Object[] obj = joinPoint.getArgs();
        System.out.println("类："+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        System.out.print("参数：");
        for (Object temp : obj) {
            System.out.print(temp+",");
        }
        System.out.println();
        System.out.println("异常："+e.getMessage());
        System.out.println("--LogAspect Throwing---");
    }


}
