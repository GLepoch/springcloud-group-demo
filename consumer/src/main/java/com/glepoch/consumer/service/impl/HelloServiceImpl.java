package com.glepoch.consumer.service.impl;

import com.glepoch.consumer.service.FeignService;
import com.glepoch.consumer.service.HelloService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/6/14/0014
 * @version: 1.0
 */
@Service
@Retry(name = "retryA")
@CircuitBreaker(name = "cbA", fallbackMethod = "error1")
public class HelloServiceImpl implements HelloService {
    @Autowired
    FeignService feignService;
    @Value("${server.port}")
    Integer port;

    @Override
    public String hello1() {
        //float a = 1 / 0;
        System.out.println(11);
        String consumer = feignService.hello1("consumer");
        System.out.println("请求结果：" + consumer);
        return "consumer>>>success " + new Date() + "consumer" + ">>>" + port;
    }

    public String error1(Throwable t) {
        return "error1  >>>" + t.getMessage();
    }
}
