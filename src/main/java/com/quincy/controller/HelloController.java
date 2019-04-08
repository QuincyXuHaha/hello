package com.quincy.controller;

import com.quincy.annotation.CustomizableAnnotation;
import com.quincy.main.MyTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author quincy
 * @date 2018/1/29 星期一
 */
@RestController
public class HelloController {

    @Resource
    private MyTest myTest;

    @GetMapping("/hello")
    public String hello() {
        return "hello boot";
    }

    @GetMapping("/test")
    public void test() {
        myTest.test();
    }

    @GetMapping("/test2")
    @CustomizableAnnotation
    public void test2() {
    }
}
