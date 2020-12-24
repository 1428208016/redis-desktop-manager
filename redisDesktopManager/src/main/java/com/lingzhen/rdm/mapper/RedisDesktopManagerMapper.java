package com.lingzhen.rdm.mapper;

import java.util.List;
import java.util.Map;

public interface RedisDesktopManagerMapper {

    int save(Map map);

    int edit(Map map);

    List findConnectionByUserId(Long userId);

    int delete(Map map);

    Map findById(Map map);

    int addOperationLog(Map map);
}
