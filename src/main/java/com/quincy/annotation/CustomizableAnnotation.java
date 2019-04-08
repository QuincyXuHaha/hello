package com.quincy.annotation;

import java.lang.annotation.*;

/**
 * @author quincy
 * @date 2018/2/27 星期二
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizableAnnotation {

    String name() default "customizableAnnotation";
}
