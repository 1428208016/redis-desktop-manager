package com.lingzhen.myproject.jiqun.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiroCacheManager implements CacheManager {

    @Autowired
    private ShiroCache shiroCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return shiroCache;
    }
}
