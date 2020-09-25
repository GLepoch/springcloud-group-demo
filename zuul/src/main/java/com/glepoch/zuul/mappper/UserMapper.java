package com.glepoch.zuul.mappper;

import com.glepoch.zuul.bean.Role;
import com.glepoch.zuul.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
    @Select("select id,username,password,enabled,locked from user where username=#{username}")
    User loadUserByUsername(String username);
    @Select("select r.id,r.name,r.nameZh from role r join user_role ur on r.id=ur.rid where uid=#{uid}")
    List<Role> getRoleById(Integer uid);
}
