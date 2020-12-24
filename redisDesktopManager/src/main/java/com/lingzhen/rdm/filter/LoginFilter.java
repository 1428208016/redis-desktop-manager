package com.lingzhen.rdm.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器
 * @date 2020-08-22
 * @author lingz
 */
@Configuration
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取请求路径，不包括项目名，会有前缀“/”
        String path = httpServletRequest.getServletPath();
        if ("/".equals(path)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/main.html");
            return ;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(LoginFilter loginFilter){
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        //设置配置的过滤器
        frBean.setFilter(loginFilter);
        //设置过滤路径
        List urlList = new ArrayList();
        urlList.add("/*");
        frBean.setUrlPatterns(urlList);
        //设置优先级
        frBean.setOrder(1);
        return frBean;
    }

}
