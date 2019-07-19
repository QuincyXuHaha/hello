package com.quincy.aop;

import com.quincy.annotation.CustomizableAnnotation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author quincy
 * @date 2018/2/27 星期二
 */
@Aspect
@Component
public class CustomizableAnnotationInterceptor {

    @Pointcut("@annotation(com.quincy.annotation.CustomizableAnnotation)")
    private void cut() {
    }

    @After("cut()&&@annotation(custom)")
    public void process(CustomizableAnnotation custom) {
        System.out.println("扫描到该自定义注解了.");
        System.out.println(custom.name());
    }




}
