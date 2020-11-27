package com.lingzhen.myproject.lifefolder.component.impl;

import com.lingzhen.myproject.lifefolder.component.RedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisComponentImpl implements RedisComponent {

    @Autowired
    @Qualifier("redisTemplate")
//    @Resource(name = "redisTemplate")
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


    public void a(){
    }

}
