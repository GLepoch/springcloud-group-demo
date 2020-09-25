package com.glepoch.consumer.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "provider")
@RequestMapping("/feign")
public interface FeignService {
    @GetMapping("/hello1/{name}")
    public String hello1(@PathVariable String name);

    @GetMapping("/hello2")
    public String hello2(@RequestParam String name);

    @GetMapping("/hello3")
    public String hello3(@RequestParam Map names);
}
