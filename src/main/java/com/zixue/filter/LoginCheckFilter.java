package com.zixue.filter;

import com.alibaba.fastjson.JSONObject;
import com.zixue.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import com.zixue.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("拦截前操作～～");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 转化请求对象
        HttpServletRequest req =  (HttpServletRequest) request;
        //1. 获取请求的url
        String url = req.getRequestURL().toString();
        log.info("requestURI:{}",url);
        // 2登录请求放行
        if(url.contains("/login")){
            log.info("登录操作");
            chain.doFilter(request, response);
            return;
        }
        // 3. 获取请求头中的token
        String jwt = req.getHeader("token");
        // 4. 判断token是否为空
        if(!StringUtils.hasLength(jwt)){
            log.info("token为空");
            Result err= Result.error("NOT_LOGIN");
            // 转化为json
            String notLogin = JSONObject.toJSONString(err);
            response.getWriter().write(notLogin);
            return;
        }
        // 5 JWT 验证
       try{
           JwtUtils.parseJwt(jwt);
       }catch (Exception e){
            e.printStackTrace();
            log.info("解析token失败，返回未登录错误信息");
           Result err= Result.error("NOT_LOGIN");
           // 转化为json
           String notLogin = JSONObject.toJSONString(err);
           response.getWriter().write(notLogin);
           return;
       }

        // 6. 放行
        log.info("令牌合法，放行");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("应用停止自动销毁～");
        Filter.super.destroy();
    }
}
