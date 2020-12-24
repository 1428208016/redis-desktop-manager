package com.lingzhen.myproject.lifefolder.filter;

import com.lingzhen.myproject.common.util.JWTUtil;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Token过滤器
 * @date 2020-08-22
 * @author lingz
 */
@Configuration
public class LoginFilter implements Filter {

    private String[] PASS_PATH = {
            "/js/.*",
            "/css/.*",
            "/plugin/.*",
            "/img/.*",
            "/main/login.html",
            "/login",
            "/main/register.html",
            "/register",
            "/sendRegisterYzm"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取请求路径，不包括项目名，会有前缀“/”
        String path = httpServletRequest.getServletPath();
        boolean bool = true;
        for (String str : PASS_PATH) {
            if (Pattern.matches(str, path)) {
                bool = false;
                break;
            }
        }

        // 需要过滤
        if (bool) {
            // Token验证
            String token = HttpServletUtil.getToken();
            bool = JWTUtil.verifyToken(token);
            if (!bool) {
                // 验证不通过：重定向到登录页面
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/main/login.html");
                return ;
            } else {
                // 验证通过：判断是否需要刷新token
                Date issuedDate = JWTUtil.getIssuedAt(token);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(issuedDate);
                calendar.add(Calendar.SECOND,JWTUtil.TOKEN_EXPIRE_SECOND/2);
                Date current = new Date();
                if (current.after(calendar.getTime())) {
                    // 创建新Token
                    Cookie cookie = new Cookie(JWTUtil.TOKEN,JWTUtil.createToken(JWTUtil.getValue(token)));
                    cookie.setMaxAge(JWTUtil.TOKEN_EXPIRE_SECOND);
                    ((HttpServletResponse) servletResponse).addCookie(cookie);
                }
            }
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
