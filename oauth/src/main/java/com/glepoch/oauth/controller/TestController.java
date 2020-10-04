package com.glepoch.oauth.controller;

import com.glepoch.oauth.execption.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
public class TestController {

    @PostMapping(value = "err")
    public void error(){
        throw new BusinessException(400, "业务异常错误信息");
    }

    @RequestMapping(value = "err2")
    public void error2(HttpHeaders request) throws NoHandlerFoundException {
        throw new NoHandlerFoundException("","", request);
    }

    @RequestMapping(value = "err3")
    public int error3(){
        int a = 10 / 0;
        return a;
    }
}