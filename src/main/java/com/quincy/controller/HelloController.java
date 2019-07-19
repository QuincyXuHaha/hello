package com.quincy.controller;

import com.quincy.MyException;
import com.quincy.annotation.CustomizableAnnotation;
import com.quincy.demo.Good;
import com.quincy.main.MyTest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @GetMapping("/test3")
    public Object test3() {
        throw new MyException();
    }

    @GetMapping("/test4")
    public Object test4() {
        throw new NullPointerException();
    }

    @PostMapping("/test5")
    public Object test5(@Valid @RequestBody Good good) {
        return good;
    }

    @PostMapping("/test6")
    public Object test6(@Valid @ModelAttribute Good good) {
        return good;
    }

    @PostMapping("/test7")
    public Object test7( @RequestParam String name) {
        return name;
    }
}
