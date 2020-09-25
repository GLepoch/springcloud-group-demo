package com.glepoch.zuul.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @description:匹配角色拥有的权限与需要的权限是否一致
 * @author: ygl
 * @createDate: 2020/3/30/0030
 * @version: 1.0
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //collection就是MySecurityMetadataSource中设置的角色名称集合
        for (ConfigAttribute attribute : collection) {
            if("ROLE_login".equals(attribute.getAttribute())){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("非法请求！");
                }else{
                    return;//直接return表示允许访问
                }
            }
            //获取登录的用户的授权信息：角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                //比较登录用户的角色和访问需要的角色是否满足
                if(authority.getAuthority().equals(attribute.getAttribute())){
                    return;
                }
            }

        }
        throw new AccessDeniedException("非法请求！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
