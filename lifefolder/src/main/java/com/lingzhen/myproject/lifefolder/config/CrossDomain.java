package com.lingzhen.myproject.lifefolder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 跨域请求支持
 * @date 2020-08-11
 * @author lingz
 */
//@Configuration
public class CrossDomain extends WebMvcConfigurerAdapter {

//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*")
//                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
//                .allowCredentials(false).maxAge(3600);
//    }
}



