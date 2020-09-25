package com.glepoch.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/5/4/0004
 * @version: 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/findAll")
    public String getProducet() {
        return "查询所有商品列表成功！";
    }
}
