package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;

import java.util.List;
import java.util.Map;

public interface RedisDesktopManagerService {

    /**
     * 保存连接
     * @param map
     * @return
     */
    int save(Map map);

    /**
     * 查询用户连接
     * @param userId
     * @return
     */
    List findConnectionByUserId(Long userId);

    /**
     * 测试连接
     * @param map
     * @return
     */
    Result testConnection(Map map);

    /**
     * 删除连接
     * @param csId
     * @return
     */
    int delete(String csId);

    /**
     *
     * @param connectionKey
     * @param dbIndex
     * @param key
     * @return
     */
    List scan(String connectionKey,Integer dbIndex, String key);

    /**
     * 连接数据库
     * @param csId
     * @return
     */
    Result connectionRedis(String csId);

    /**
     * 加载键值
     * @param connectionKey
     * @param dbIndex
     * @param key
     * @return
     */
    Result loadKeyValue(String connectionKey,Integer dbIndex, String key);


}
