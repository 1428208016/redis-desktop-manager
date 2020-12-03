package com.lingzhen.myproject.lifefolder.mapper;

import java.util.List;
import java.util.Map;

public interface RedisDesktopManagerMapper {

    int save(Map map);

    int edit(Map map);

    List findConnectionByUserId(Long userId);

    int delete(Map map);

    Map findById(Map map);
}
