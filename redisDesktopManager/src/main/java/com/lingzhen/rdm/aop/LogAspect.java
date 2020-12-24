package com.lingzhen.rdm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    // 语法：execution([访问修饰符] 返回类型 [类路径]方法名称(参数类型) [异常类型])
    // *：匹配任何
    // .. 所有子包
    // (..)：匹配任何参数matches any number (zero or more)
    // execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
    @Pointcut("execution(* com.lingzhen.rdm.service.RedisDesktopManagerService.*(..))")
    public void logPointcut(){}


    @AfterReturning(pointcut = "com.lingzhen.rdm.aop.LogAspect.logPointcut()",
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

}
