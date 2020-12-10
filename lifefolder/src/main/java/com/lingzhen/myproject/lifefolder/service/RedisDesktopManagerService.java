package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;

import java.util.List;
import java.util.Map;

public interface RedisDesktopManagerService {

    /**
     * 保存\编辑连接
     * @param map
     * @return
     */
    int saveOrEdit(Map map);

    /**
     * 查询连接
     * @param csId
     * @return
     */
    Map findConnectionById(String csId);
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

    /**
     * 添加新键
     * @param csId
     * @param dbIndex
     * @param key
     * @param type
     * @param value
     * @param ttl
     * @return
     */
    Result addNewKey(String csId, Integer dbIndex, String key, String type, Object value, Long ttl);

    /**
     * 删除键
     * @param csId
     * @param dbIndex
     * @param key
     * @return
     */
    Result deleteKey(String csId, Integer dbIndex, String key);

    /**
     * 编辑
     * @param csId
     * @param dbIndex
     * @param key
     * @param type
     * @param value
     * @param ttl
     * @return
     */
    Result editKey(String csId, Integer dbIndex, String key, String type, Object value, Long ttl);

    /**
     * 重命名Key
     * @param csId
     * @param dbIndex
     * @param key
     * @param newKey
     * @return
     */
    Result renameKey(String csId, Integer dbIndex, String key, String newKey);

    /**
     * 设置TTL
     * @param csId
     * @param dbIndex
     * @param key
     * @param ttl
     * @return
     */
    Result setTTL(String csId, Integer dbIndex, String key, Integer ttl);

    /**
     * hscan sscan zscan
     * @param csId
     * @param dbIndex
     * @param key
     * @param scanKey
     * @return
     */
    Result kvScan(String csId, Integer dbIndex, String key, String type, String scanKey);

}
