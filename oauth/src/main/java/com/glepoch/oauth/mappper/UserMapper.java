package com.glepoch.oauth.mappper;

import com.glepoch.oauth.bean.Role;
import com.glepoch.oauth.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface UserMapper {
    @Select("select id,username,password,enabled,locked from user where username=#{username}")
    User loadUserByUsername(String username);
    @Select("select r.id,r.name,r.nameZh from role r join user_role ur on r.id=ur.rid where uid=#{uid}")
    List<Role> getRoleById(Integer uid);
}
