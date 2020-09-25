package com.glepoch.oauth.service.impl;


import com.glepoch.oauth.bean.Role;
import com.glepoch.oauth.bean.User;
import com.glepoch.oauth.mappper.UserMapper;
import com.glepoch.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/3/30/0030
 * @version: 1.0
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
