package org.dy.security04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caiwl
 * @date 2020/8/9 14:05
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(){
        return "hello, welcome";
    }

    @GetMapping("/admin/hello")
    public String helloAdmin(){
        return "hello admin";
    }

    @GetMapping("/vip/hello")
    public String helloVip(){
        return "hello vip";
    }

    @GetMapping("/user/hello")
    public String helloUser(){
        return "hello user";
    }
}
