package com.lingzhen.rdm.aop;

import com.lingzhen.rdm.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户访问日志切面
 * @author lingz
 * @date 2020-09-26 19:15
 */
@Aspect
@Component
public class AccessLogAspect {

    @Autowired
    private UserService userService;

    @Pointcut("execution(* com.lingzhen.myproject.lifefolder.controller.*..*(..))")
    public void aspect(){}

    @Before("com.lingzhen.rdm.aop.AccessLogAspect.aspect()")
    public void before(JoinPoint joinPoint) {
        // 方法名
        String method = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        // 参数
        Object[] obj = joinPoint.getArgs();
        String para = "";
        for (Object temp : obj) {
            para += temp+",";
        }
        userService.insertAccessLog(method,para);
    }

}
