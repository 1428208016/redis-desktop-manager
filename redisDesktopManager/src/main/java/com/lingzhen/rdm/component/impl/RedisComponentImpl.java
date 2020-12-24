package com.lingzhen.rdm.component.impl;

import com.lingzhen.rdm.component.RedisComponent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisComponentImpl implements RedisComponent {

    @Resource(name = "myRedisTemplate")
    private RedisTemplate template;

    public void set(String key, String val, long expire){
        template.opsForValue().set(key,val,expire, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        Object val = template.opsForValue().get(key);
        if (null == val) {
            return null;
        } else {
            return val.toString();
        }
    }

    @Override
    public Boolean exists(String key) {
        return template.hasKey(key);
    }

    @Override
    public void hSet(String key, String hashKey, Object val) {
        template.opsForHash().put(key,hashKey,val);
    }

    @Override
    public Long lPush(String key, Object val) {
        return template.opsForList().leftPush(key,val);
    }


    public void test(){



    }

}
