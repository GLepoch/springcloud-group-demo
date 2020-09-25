package com.glepoch.zuul.config;

import com.glepoch.zuul.bean.Menu;
import com.glepoch.zuul.bean.Role;
import com.glepoch.zuul.mappper.MenuMapper;
import com.glepoch.zuul.mappper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description:根据请求URL找出这个请求需要那些角色访问
 * @author: ygl
 * @createDate: 2020/3/30/0030
 * @version: 1.0
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMapper roleMapper;

    /*
     * 获取各个菜单需要那些角色才行访问，并放到缓存
     * */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        List<Menu> menuList = new ArrayList<>();
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        /*if (requestUrl != null) {
            StringBuffer urlSb = new StringBuffer(requestUrl);
            if (requestUrl.startsWith("/")) {
                urlSb.delete(0, 1);
                int i = urlSb.indexOf("/");
                requestUrl = urlSb.substring(i);
            } else if (requestUrl.startsWith("\\")) {
                urlSb.delete(0, 1);
                int i = urlSb.indexOf("\\");
                requestUrl = urlSb.substring(i);
            } else {
                int i = requestUrl.indexOf("/");
                requestUrl = requestUrl.substring(i);
            }
        }*/
        //这里设置缓存
        ServletContext servletContext = ((FilterInvocation) o).getHttpRequest().getServletContext();
        menuList = (List<Menu>) servletContext.getAttribute("menuList");
        if (menuList == null || menuList.size() == 0) {//如果缓存中没获取到菜单数据则在数据库中查找
            menuList = menuMapper.findAllMenu();
            servletContext.setAttribute("menuList", menuList);
        }
        for (Menu menu : menuList) {//循环出每个菜单与请求URL比对
            Integer mid = menu.getId();
            if (pathMatcher.match(menu.getPattern(), requestUrl)) {//如果请求URL与菜单配置匹配则查找那些角色有这个菜单权限
                List<Role> roles = roleMapper.findRoleByMid(mid);
                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    rolesStr[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(rolesStr);
            }
        }
        //设置一个标识ROLE_login标识后面MyAccessDecisionManager类用，来到这里表示非法请求了
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
