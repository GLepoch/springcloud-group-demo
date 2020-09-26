package com.glepoch.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    @Value("${eureka.instance.prefer-ip-address}")
    private String name;

    @RequestMapping("/config")
    public void getConfig(){
        System.out.println("name:"+name);
    }
}
