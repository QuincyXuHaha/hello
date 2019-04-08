package com.quincy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author quincy
 * @date 2018/2/27 星期二
 */
@Aspect // declare aspect
@Component // auto scan
public class LogInterceptor {

    @Pointcut("execution(public * com.quincy.main.*.*(..))")
    private void cut() {
    }

    @Before("cut()")
    public void before() {
        System.out.println("=====业务处理之前=====");
    }

    @After("cut()")
    public void after() {
        System.out.println("=====业务处理之后=====");
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println(pjp.getSignature() + "=====业务处理前=====");
        Object o = null;
        try {
            o = pjp.proceed();
            System.out.println(pjp.getSignature() + "=====业务处理后=====");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }
}
