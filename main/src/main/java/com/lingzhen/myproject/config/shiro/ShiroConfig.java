package com.lingzhen.myproject.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //验证方式
    @Bean
    ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    @Bean
    org.apache.shiro.web.mgt.DefaultWebSecurityManager securityManager(
            ShiroCacheManager shiroCacheManager,
            ShiroCachingSessionDAO shiroCachingSessionDAO,
            RedisSimpleSessionFactory redisSimpleSessionFactory) {

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //验证方式
        manager.setRealm(shiroRealm());
        //session管理方式
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(shiroCachingSessionDAO);
        defaultWebSessionManager.setSessionFactory(redisSimpleSessionFactory);  //自定义生成session对象工厂
        manager.setSessionManager(defaultWebSessionManager);
        //session管理操作者
        manager.setCacheManager(shiroCacheManager);

        return manager;
    }


    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/html/login.html");
        bean.setSuccessUrl("/main/index");
        bean.setUnauthorizedUrl("/unauthorizedurl");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/main/login", "anon");
//        map.put("/js/**", "anon");
//        map.put("/css/**", "anon");

        map.put("/**", "authc");

        bean.setFilterChainDefinitionMap(map);
        return bean;
    }


}
