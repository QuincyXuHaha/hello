package com.quincy.aop;

import com.quincy.MyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author xuguangquan
 * @date 2019/7/19 星期五
 */
@Component
@Aspect
public class GlobalExceptionAop {


    @Around("execution(* com.quincy.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            if (e instanceof MyException) {
                return "自定义异常";
            }else if (e instanceof NullPointerException) {
                return "空指针异常";
            }else {
                return "系统异常";
            }
        }
    }
}
