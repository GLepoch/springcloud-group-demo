package com.glepoch.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/5/4/0004
 * @version: 1.0
 * 测试第三方认证服务器：http://localhost:9001/oauth/authorize?response_type=code&client_id=glepoch_one
 */
@RestController
public class TestOAuth2Login {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/login2")
    public String login(String code) {
        if (code != null) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("code", code);
            map.add("client_id", "glepoch_one");
            map.add("client_secret", "123");
            map.add("grant_type", "authorization_code");
            Map<String,String> resp = restTemplate.postForObject("http://localhost:9001/oauth/token", map, Map.class);
            String access_token = resp.get("access_token");
            System.out.println("access_token为："+access_token);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + access_token);
            HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> entity = restTemplate.exchange("http://localhost:9002/product/findAll", HttpMethod.GET, httpEntity, String.class);
            System.out.println("响应状态码:" + entity.getStatusCode());
            System.out.println("响应内容为:" + entity.getBody());
            return entity.getBody();
        }
        return "没获取到授权码。";
    }
}
