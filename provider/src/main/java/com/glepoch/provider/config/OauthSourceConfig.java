package com.glepoch.provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/5/4/0004
 * @version: 1.0
 */
@Configuration  //spring配置类
@EnableResourceServer  //启动oauth认证
@EnableGlobalMethodSecurity(securedEnabled = true) //开启Spring Security注解
public class OauthSourceConfig extends ResourceServerConfigurerAdapter {
    private String SIGNING_KEY = "glepoch";
    @Autowired
    private DataSource dataSource;

    /**
     * TokenStore是OAuth2保存token的接口
     * 其下有RedisTokenStore保存到redis中，
     * JdbcTokenStore保存到数据库中，
     * InMemoryTokenStore保存到内存中等实现类，
     * 这里我们选择保存在数据库中
     *
     * @return
     */
    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
    //token保存策略
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
        //数据库保存token
        //return new JdbcTokenStore(dataSource);
    }
////认证服务器与资源服务器不同服务器情况,通过url访问验证方式
/*    @Bean
    RemoteTokenServices tokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:9001/oauth/check_token");
        services.setClientId("glepoch_one");
        services.setClientSecret("123");
        return services;
    }*/

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        /*//认证服务器与资源服务器相同数据库情况可以用这段代码
        TokenStore tokenStore = new JdbcTokenStore(dataSource);
        resources.resourceId("product_api") //指定当前资源的id，非常重要！必须写！
                .tokenStore(tokenStore);//指定保存token的方式*/

        //认证服务器与资源服务器不同服务器情况，,通过url访问验证方式
        //resources.resourceId("product_api").tokenServices(tokenServices());

        //jwt访问
        resources.resourceId("product_api2").tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                //指定不同请求方式访问资源所需要的权限，一般查询是read，其余是write。
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
                .and()
                .headers().addHeaderWriter((request, response) -> {
            response.addHeader("Access-Control-Allow-Origin", "*");//允许跨域
            if (request.getMethod().equals("OPTIONS")) {//如果是跨域的预检请求，则原封不动向下传达请求头信息
                response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            }
        });
    }
}