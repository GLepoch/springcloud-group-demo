package com.glepoch.oauth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
/*
* 需要继承UserDetailsService接口才能让SpringSecurity登录认证生效
* */
public interface UserService  extends UserDetailsService {
}
