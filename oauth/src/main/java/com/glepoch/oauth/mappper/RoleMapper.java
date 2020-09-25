package com.glepoch.oauth.mappper;

import com.glepoch.oauth.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select r.id,r.name,r.nameZh from role r join menu_role mr on r.id=mr.rid where mr.mid=#{mid}")
    List<Role> findRoleByMid(Integer mid);
}
