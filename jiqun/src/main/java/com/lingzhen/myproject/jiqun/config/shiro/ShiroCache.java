package com.lingzhen.myproject.jiqun.config.shiro;

import com.lingzhen.myproject.jiqun.config.Constant;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class ShiroCache implements Cache {

    @Resource(name = "redisTemplateMaster")
    RedisTemplate redisTemplateMaster;

    @Override
    public Object get(Object o) throws CacheException {
        Object obj = redisTemplateMaster.opsForValue().get(o);
        return obj;
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        //String str = JSON.toJSONString(o2);
        redisTemplateMaster.opsForValue().set(o,o2, Constant.REDIS_SESSION_EXPIRE_TIME,TimeUnit.SECONDS);
        return null;
    }

    @Override
    public Object remove(Object o) throws CacheException {
        Object object = get(o);
        redisTemplateMaster.opsForValue().getOperations().delete(o);
        return object;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set keys() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }
}
