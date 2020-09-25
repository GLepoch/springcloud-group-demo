/*
package com.glepoch.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * @description:
 * @author: ygl
 * @createDate: 2020/6/14/0014
 * @version: 1.0
 *//*

@Component
public class PermissFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("filter----------------------");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       */
/* if (username.equals("susan")&&password.equals("123")){

        }else {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            requestContext.setResponseBody("未认证！");
        }*//*

        return null;
    }
}
*/
