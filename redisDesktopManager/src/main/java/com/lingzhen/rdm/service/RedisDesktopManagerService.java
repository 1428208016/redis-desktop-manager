package com.lingzhen.rdm.service;

import com.lingzhen.rdm.pojo.Result;

import java.util.List;
import java.util.Map;

public interface RedisDesktopManagerService {

    /**
     * 测试连接
     * @param map
     * @return
     */
    Result testConnection(Map map);

    /**
     *
     * @param connStr
     * @param dbIndex
     * @param key
     * @return
     */
    List scan(String connStr,Integer dbIndex, String key);

    /**
     * 连接数据库
     * @param objStr
     * @return
     */
    Result connectionRedis(String connKey, String objStr);

    /**
     * 加载键值
     * @param connStr
     * @param dbIndex
     * @param key
     * @return
     */
    Result loadKeyValue(String connStr,Integer dbIndex, String key);

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
     * @param connStr
     * @param dbIndex
     * @param key
     * @return
     */
    Result deleteKey(String connStr, Integer dbIndex, String key);

    /**
     * 编辑
     * @param connStr
     * @param dbIndex
     * @param key
     * @param type
     * @param value
     * @param ttl
     * @return
     */
    Result editKey(String connStr, Integer dbIndex, String key, String type, Object value, Long ttl);

    /**
     * 重命名Key
     * @param connStr
     * @param dbIndex
     * @param key
     * @param newKey
     * @return
     */
    Result renameKey(String connStr, Integer dbIndex, String key, String newKey);

    /**
     * 设置TTL
     * @param connStr
     * @param dbIndex
     * @param key
     * @param ttl
     * @return
     */
    Result setTTL(String connStr, Integer dbIndex, String key, Integer ttl);

    /**
     * hscan sscan zscan
     * @param connStr
     * @param dbIndex
     * @param key
     * @param scanKey
     * @return
     */
    Result kvScan(String connStr, Integer dbIndex, String key, String type, String scanKey);

}
