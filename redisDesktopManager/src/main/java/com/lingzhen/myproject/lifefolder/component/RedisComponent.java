package com.lingzhen.myproject.lifefolder.component;

public interface RedisComponent {

    void set(String key, String val, long expire);

    String get(String key);

    Boolean exists(String key);

    void hSet(String key, String hashKey, Object val);

    Long lPush(String key, Object val);

}
