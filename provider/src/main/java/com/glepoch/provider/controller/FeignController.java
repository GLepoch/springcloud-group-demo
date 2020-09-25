package com.glepoch.provider.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/6/14/0014
 * @version: 1.0
 */
@RestController
@RateLimiter(name = "rlA")
@RequestMapping("/feign")
public class FeignController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello1/{name}")
    public String hello1(@PathVariable String name){
        System.out.println("provider:"+new Date()+name+">>>"+port);
        return "provider"+new Date()+name+">>>"+port;
    }

    @GetMapping("/hello2")
    public String hello2(String name){
        System.out.println("provider:"+new Date()+name+">>>"+port);
        return "provider"+new Date()+name+">>>"+port;
    }

    @GetMapping("/hello3")
    public String hello3(@RequestParam Map names){
        System.out.println("provider:"+new Date()+(String) names.get("name")+">>>"+port);
        return "provider"+new Date()+(String) names.get("name")+">>>"+port;
    }
}