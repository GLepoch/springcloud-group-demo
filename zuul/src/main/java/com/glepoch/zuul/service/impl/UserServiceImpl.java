package com.glepoch.zuul.service.impl;

import com.glepoch.zuul.bean.Role;
import com.glepoch.zuul.bean.User;
import com.glepoch.zuul.mappper.UserMapper;
import com.glepoch.zuul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/3/30/0030
 * @version: 1.0
 * 需要继承UserDetailsService接口才能让SpringSecurity登录认证生效，这里的接口已经继承了
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userMapper.loadUserByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<Role> roles=userMapper.getRoleById(user.getId());
        user.setRoles(roles);
        return user;
    }
}
