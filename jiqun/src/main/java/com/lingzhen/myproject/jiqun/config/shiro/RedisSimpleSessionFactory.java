package com.lingzhen.myproject.jiqun.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisSimpleSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext sessionContext) {

        if (sessionContext != null) {
            String host = sessionContext.getHost();
            if (host != null) {
                return new RedisSimpleSession(host);
            }
        }
        return new RedisSimpleSession();
    }
}
