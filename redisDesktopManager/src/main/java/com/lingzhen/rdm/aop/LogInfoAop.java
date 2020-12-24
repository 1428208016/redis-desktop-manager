package com.lingzhen.rdm.aop;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author yishenheng
 * @date 2020-01-09 16:48
 * 日志AOP
 */
//@Aspect
//@Component
//@Slf4j
public class LogInfoAop {

    /**
     * 扫描所有的控制层 打印出参入参，方便查看日志。定位问题
     */
//    @Pointcut(value = "execution(* com.operation.manager.controller.*Controller.*(..))")
    public void publicPackage() {

    }


    /**
     * 输出调用的接口名称和参数
     *
     * @param joinPoint aop
     */
//    @Before(value = "publicPackage()")
//    public void before(JoinPoint joinPoint) {
//        String url = getUrl();
//        Object[] param = joinPoint.getArgs();
//        log.info("开始调用接口----->{},接收参数----->{}", url, param);
//    }


    /**
     * 结束后输出参数
     *
     * @param joinPoint    aop
     * @param resultObject 返回参数
     */
//    @AfterReturning(returning = "resultObject", value = "publicPackage()")
//    public void afterReturningWave(JoinPoint joinPoint, Object resultObject) {
//        String url = getUrl();
//        Object[] param = joinPoint.getArgs();
//        log.info("开始调用接口----->{},接收参数----->{},返回参数----->{}", url, param, JSON.toJSONString(resultObject));
//
//    }

    /**
     * 获取当前访问的URL
     *
     * @return url
     */
    private String getUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        return attributes.getRequest().getRequestURI();
    }


}
