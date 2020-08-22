package com.lingzhen.myproject.lifefolder.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lingzhen.myproject.common.util.JWTUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilterCopy /** implements Filter**/ {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        Cookie[] cookies = httpServletRequest.getCookies();
//        String token = "";
//        if (null != cookies && cookies.length > 0) {
//            for (Cookie cookie : cookies) {
//                if ("token".equals(cookie.getName())) {
//                    token = cookie.getValue();
//                }
//            }
//        }
//
//
//
//        DecodedJWT jwt = JWT.decode(token);
//        String userId = jwt.getClaim(JWTUtil.JWT_CLAIM_KEY).asString();
//
//
//        boolean bool = JWTUtil.verifyToken(token);
//        token = JWTUtil.createToken("123456");
//
//        Cookie cookie = new Cookie("token",token);
//        httpServletResponse.addCookie(cookie);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }

}
