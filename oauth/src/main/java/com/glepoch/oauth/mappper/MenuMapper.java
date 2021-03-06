package com.glepoch.oauth.mappper;

import com.glepoch.oauth.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("select id,pattern from menu")
    List<Menu> findAllMenu();
}
