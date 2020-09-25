/*
package com.glepoch.consumer.service.impl;

import com.glepoch.consumer.service.FeignService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

*/
/**
 * @description:hystrix的熔断处理方式，这里使用了resilience4j的机制故注释掉
 * @author: ygl
 * @createDate: 2020/6/14/0014
 * @version: 1.0
 *//*

@Component
@RequestMapping("/glepch")
public class FeignServiceFallBack implements FeignService {
    @Value("${server.port}")
    Integer port;
    @Override
    public String hello1(String name) {
        return "consumer>>>error"+new Date()+name+">>>"+port;
    }

    @Override
    public String hello2(String name) {
        return "consumer>>>error"+new Date()+name+">>>"+port;
    }

    @Override
    public String hello3(Map names) {
        return "consumer>>>error"+new Date()+names.get("name")+">>>"+port;
    }
}
*/
