package com.glepoch.consumer.controller;

import com.glepoch.consumer.service.FeignService;
import com.glepoch.consumer.service.HelloService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/6/14/0014
 * @version: 1.0
 */
@RestController
@RateLimiter(name = "rlA")
public class HelloController {
    @Autowired
    FeignService feignService;

    @Autowired
    HelloService helloService;

    @Value("${server.port}")

    @GetMapping("/hello1")
    public void hello1(){
        String crs = helloService.hello1();
        System.out.println(crs);
/*        System.out.println("===============测试接口参数=================");
        String hello1 = feignService.hello1("hello1");
        String hello2 = feignService.hello2("hello2");
        Map<String, String> map=new HashMap<>();
        map.put("name","hello3");
        String hello3 = feignService.hello3(map);
        System.out.println(hello1);
        System.out.println(hello2);
        System.out.println(hello3);*/

    }
}
