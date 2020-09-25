package com.glepoch.oauth.config;


import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:拦截认证token响应，用于在响应中自定义响应内容
 * @author: ygl
 * @createDate: 2020/5/5/0005
 * @version: 1.0
 */
@Component
public class CustomAdditionalInformation implements TokenEnhancer {
    //我们可以从 OAuth2AccessToken 中取出已经生成的额外信息，然后在此基础上追加自己的信息。
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        //accessToken 默认响应的access_token
        Map<String, Object> info = accessToken.getAdditionalInformation();
        info.put("glepoch", "测试在认证响应中添加属性！");//自定义添加内容
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
