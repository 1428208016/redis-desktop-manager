package com.lingzhen.rdm.service.impl;

import com.alibaba.fastjson.JSON;
import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.rdm.pojo.Result;
import com.lingzhen.rdm.service.RedisDesktopManagerService;
import com.lingzhen.rdm.util.HttpServletUtil;
import com.lingzhen.rdm.util.VerifyUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.*;

@Service
public class RedisDesktopManagerServiceImpl implements RedisDesktopManagerService {

    // 连接map
    private Map<String, Jedis> connectionMap = new HashMap<>();

    @Override
    public int saveOrEdit(Map map) {
        // defaultFilter默认值
        if (VerifyUtil.stringTrimIsEmpty(map.get("defaultFilter"))) {
            map.put("defaultFilter","*");
        }
        // databaseDiscoveryLimit默认值与限制
        if (VerifyUtil.stringTrimIsEmpty(map.get("databaseDiscoveryLimit"))) {
            map.put("databaseDiscoveryLimit","20");
        } else {
            Integer databaseDiscoveryLimit = Integer.valueOf(map.get("databaseDiscoveryLimit").toString());
            if (databaseDiscoveryLimit <= 0 || databaseDiscoveryLimit > 255) {
                map.put("databaseDiscoveryLimit",20);
            }
        }
        map.put("userId", HttpServletUtil.getUserId());
        if (VerifyUtil.stringTrimIsEmpty(map.get("csId"))) {
            // 新增
            map.put("createDate",DateUtil.getDate());
            map.put("createTime",DateUtil.getTime());
        } else {
            // 修改
        }
        return 1;
    }

    @Override
    public Map findConnectionById(String csId) {
        Map selMap = new HashMap();
        selMap.put("userId",HttpServletUtil.getUserId());
        selMap.put("csId",csId);
        return null;
    }

    @Override
    public List findConnectionByUserId(Long userId) {
//        return redisDesktopManagerMapper.findConnectionByUserId(userId);
        return null;
    }

    @Override
    public Result testConnection(Map map) {
        Result result = new Result();
        try {
            String host = map.get("address").toString();
            Integer port = Integer.valueOf(map.get("port").toString());
            String auth = map.get("auth").toString();

            JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port);
            jedisShardInfo.setPassword(auth);
            Jedis jedis = new Jedis(jedisShardInfo);
            String str = jedis.auth(auth);
            if (!"OK".equals(str)) {
                result.setErrorReturn(str);
            }
        } catch (Exception e) {
            result.setError("连接失败，"+e.getMessage());
        }

        return result;
    }

    @Override
    public int delete(String csId) {
        Map map = new HashMap();
        map.put("csId",csId);
        map.put("userId",HttpServletUtil.getUserId());
//        return redisDesktopManagerMapper.delete(map);
        return 1;
    }

    @Override
    public List scan(String connectionKey, Integer dbIndex, String key) {
        List retList = new ArrayList();
        Jedis jedis = connectionMap.get(connectionKey);
        if (null == jedis) {
            return retList;
        }

        jedis.select(dbIndex);

        Integer pageSize = 200;     //每页数据条数
        Integer count = pageSize;

        ScanParams sp = new ScanParams();
        sp.match(key);
        sp.count(count);

        // cursor 从0开始
        String cursor = "0";
        int listSize = 0;
        do {
            ScanResult<String> resultList = jedis.scan(cursor,sp);
            listSize = resultList.getResult().size();
            if (listSize > 0) {
                retList.addAll(resultList.getResult());
                if (retList.size() >= pageSize) {
                    retList = retList.subList(0,pageSize);
                    break;
                }
            }
            cursor = resultList.getCursor();
            sp.count(count*=2);
            if ("0".equals(cursor)) {
                // 全库查询完
                break;
            }
        } while (true);
        if (retList.size() > 0) {
            Collections.sort(retList);
        }
        return retList;
    }

    @Override
    public Result connectionRedis(String csId) {
        Result result = new Result();
        // 查询连接信息
        Map map = new HashMap();
        map.put("userId",HttpServletUtil.getUserId());
        map.put("csId",csId);
        Map data = this.findConnectionById(csId);
        if (null == data || data.size() <= 0) {
            return result.setErrorReturn("未找到连接信息！");
        }
        // 验证
        String auth = data.get("auth").toString();

        JedisShardInfo jedisShardInfo = new JedisShardInfo(data.get("address").toString(),Integer.valueOf(data.get("port").toString()));
        jedisShardInfo.setPassword(auth);
        Jedis jedis = new Jedis(jedisShardInfo);
        String retMsg = jedis.auth(auth);
        if (!"OK".equals(retMsg)) {
            return result.setErrorReturn(retMsg);
        }

        connectionMap.put(csId,jedis);
        // 查询databases
        List databaseList = new ArrayList();
        List<String> databases = jedis.configGet("databases");
        if (null != databases && databases.size() >= 2) {
            int index = Integer.valueOf(databases.get(1));
            int databaseDiscoveryLimit = Integer.valueOf(data.get("databaseDiscoveryLimit").toString());
            for (int i = 0;i < index && i < databaseDiscoveryLimit; i++) {
                Map _data = new HashMap();
                _data.put("dbName","db"+i);

                jedis.select(i);
                _data.put("size",jedis.dbSize());
                databaseList.add(_data);
            }
        }
        Map retMap = new HashMap();
        retMap.put("connectionKey",csId);
        retMap.put("connectionName",data.get("connectionName"));
        retMap.put("list",databaseList);
        result.setData(retMap);

        return result;
    }

    @Override
    public Result loadKeyValue(String connectionKey, Integer dbIndex, String key) {
        Result result = new Result();

        Jedis jedis = connectionMap.get(connectionKey);
        if (null == jedis) {
            return result.setErrorReturn("连接失败！");
        }

        jedis.select(dbIndex);

        String type = jedis.type(key);
        Long ttl = jedis.ttl(key);
        Object data;
        if ("string".equals(type)) {
            data = jedis.get(key);
        } else if ("list".equals(type)) {
            data = jedis.lrange(key,0,-1);
        } else if ("set".equals(type)) {
            ScanParams sp = new ScanParams();
            sp.match("*");
            sp.count(100);
            data = jedis.sscan(key,"0",sp).getResult();
        } else if ("zset".equals(type)) {
            ScanParams sp = new ScanParams();
            sp.match("*");
            sp.count(100);
            data = jedis.zscan(key,"0",sp).getResult();
        } else if ("hash".equals(type)) {
            ScanParams sp = new ScanParams();
            sp.match("*");
            sp.count(100);
            data = jedis.hscan(key,"0",sp).getResult();
        } else {
            data = null;
        }

        Map retMap = new HashMap();
        retMap.put("key",key);
        retMap.put("type",type);
        retMap.put("ttl",ttl);
        retMap.put("data",data);
        result.setData(retMap);

        return result;
    }

    @Override
    public Result addNewKey(String csId, Integer dbIndex, String key, String type, Object value, Long ttl) {
        Result result = new Result();

        Jedis jedis = connectionMap.get(csId);
        if (null == jedis) {
            return result.setErrorReturn("无此连接！");
        }
        // 选择数据库
        jedis.select(dbIndex);

        if (jedis.exists(key)) {
            return result.setErrorReturn("key已存在！");
        }

        if ("string".equals(type)) {
            jedis.set(key,value.toString());
        } else if ("list".equals(type)) {
            List<String> data = JSON.parseArray(value.toString(),String.class);
            if (data.size() <= 0) {
                return result.setErrorReturn("请至少添加一行数据");
            }
            String[] strArr = new String[data.size()];
            jedis.rpush(key,data.toArray(strArr));
        } else if ("set".equals(type)) {
            List<String> data = JSON.parseArray(value.toString(),String.class);
            if (data.size() <= 0) {
                return result.setErrorReturn("请至少添加一行数据");
            }
            String[] strArr = new String[data.size()];
            jedis.sadd(key,data.toArray(strArr));
        } else if ("zset".equals(type)) {
            Map<String, String> data = JSON.parseObject(value.toString(),Map.class);
            if (data.size() <= 0) {
                return result.setErrorReturn("请至少添加一行数据");
            }
            Map<String, Double> newData = new HashMap<>();
            Set<String> keySet = data.keySet();
            for (String _key : keySet) {
                newData.put(_key,Double.valueOf(data.get(_key)));
            }
            jedis.zadd(key,newData);
        } else if ("hash".equals(type)) {
            Map<String, String> data = JSON.parseObject(value.toString(),Map.class);
            if (data.size() <= 0) {
                return result.setErrorReturn("请至少添加一行数据");
            }
            jedis.hset(key,data);
        }
        // 过期时间
        if (ttl != -1) {
            jedis.expire(key,ttl.intValue());
        }

        return result;
    }

    @Override
    public Result deleteKey(String csId, Integer dbIndex, String key) {
        Result result = new Result();

        Jedis jedis = connectionMap.get(csId);
        if (null == jedis) {
            return result.setErrorReturn("无此连接！");
        }
        // 选择数据库
        jedis.select(dbIndex);

        jedis.del(key);

        return result;
    }

    @Override
    public Result editKey(String csId, Integer dbIndex, String key, String type, Object value, Long ttl) {
        Result result = new Result();

        Jedis jedis = connectionMap.get(csId);
        if (null == jedis) {
            return result.setErrorReturn("无此连接！");
        }
        // 选择数据库
        jedis.select(dbIndex);

        if ("string".equals(type)) {
            jedis.set(key,value.toString());
        } else {
            List<Map> valueArr = JSON.parseArray(value.toString(),Map.class);
            if (valueArr.size() > 0) {
                if ("list".equals(type)) {
                    for (Map temp : valueArr) {
                        String _type = temp.get("type").toString();
                        String _value = temp.get("value").toString();
                        if ("set".equals(_type)) {
                            jedis.lset(key,Long.valueOf(temp.get("index").toString()),_value);
                        } else if ("left".equals(_type)) {
                            jedis.lpush(key,_value);
                        } else if ("right".equals(_type)) {
                            jedis.rpush(key,_value);
                        } else if ("rem".equals(_type)) {
//                            count > 0：删除等于element从头到尾移动的元素。
//                            count < 0：删除等于element从尾到头移动的元素。
//                            count = 0：删除所有等于的元素element。
                            jedis.lrem(key,Long.valueOf(temp.get("count").toString()),_value);
                        }
                    }
                } else if ("set".equals(type)) {
                    for (Map temp : valueArr) {
                        String _type = temp.get("type").toString();
                        String _value = temp.get("value").toString();
                        if ("set".equals(_type)) {
                            String oldValue = temp.get("oldValue").toString();
                            jedis.srem(key,oldValue);
                            jedis.sadd(key,_value);
                        } else if ("right".equals(_type)) {
                            jedis.sadd(key,_value);
                        } else if ("rem".equals(_type)) {
                            jedis.srem(key,_value);
                        }

                    }
                } else if ("zset".equals(type)) {
                    for (Map temp : valueArr) {
                        String _type = temp.get("type").toString();
                        String _value = temp.get("value").toString();
                        if ("set".equals(_type)) {
                            String oldValue = temp.get("oldValue").toString();
                            Double score = Double.valueOf(temp.get("score").toString());
                            jedis.zrem(key,oldValue);
                            jedis.zadd(key,score,_value);
                        } else if ("add".equals(_type)) {
                            Double score = Double.valueOf(temp.get("score").toString());
                            jedis.zadd(key,score,_value);
                        } else if ("rem".equals(_type)) {
                            jedis.zrem(key,_value);
                        }
                    }
                } else if ("hash".equals(type)) {
                    for (Map temp : valueArr) {
                        String _type = temp.get("type").toString();
                        String _hashKey = temp.get("value").toString();
                        if ("set".equals(_type)) {
                            String oldHashKey = temp.get("oldHashKey").toString();
                            String _fieldValue = temp.get("fieldValue").toString();
                            jedis.hdel(key,oldHashKey);
                            jedis.hset(key,_hashKey,_fieldValue);
                        } else if ("add".equals(_type)) {
                            String _fieldValue = temp.get("fieldValue").toString();
                            jedis.hset(key,_hashKey,_fieldValue);
                        } else if ("rem".equals(_type)) {
                            jedis.hdel(key,_hashKey);
                        }
                    }
                }
            }
        }

        // 过期时间
        if (ttl != -1) {
            jedis.expire(key,ttl.intValue());
        }

        return result;
    }

    @Override
    public Result renameKey(String csId, Integer dbIndex, String key, String newKey) {
        Result result = new Result();

        Jedis jedis = connectionMap.get(csId);
        if (null == jedis) {
            return result.setErrorReturn("无此连接！");
        }
        // 选择数据库
        jedis.select(dbIndex);
        // 重命名
        Long reply = jedis.renamenx(key,newKey);
        if (reply == 0) {
            result.setError("新Key已存在！");
        }

        return result;
    }

    @Override
    public Result setTTL(String csId, Integer dbIndex, String key, Integer ttl) {
        Result result = new Result();

        Jedis jedis = connectionMap.get(csId);
        if (null == jedis) {
            return result.setErrorReturn("无此连接！");
        }
        // 选择数据库
        jedis.select(dbIndex);
        // 重命名
        if (ttl == -1) {
            jedis.persist(key);
        } else {
            jedis.expire(key,ttl);
        }

        return result;
    }

    @Override
    public Result kvScan(String csId, Integer dbIndex, String key, String type, String scanKey) {
        Result result = new Result();

        Jedis jedis = connectionMap.get(csId);
        if (null == jedis) {
            return result.setErrorReturn("无此连接！");
        }
        // 选择数据库
        jedis.select(dbIndex);

        String cursor = "0";
        Integer count = 200;
        Integer pageSize = 200;

        ScanParams sp = new ScanParams();
        sp.count(count);
        sp.match("*"+scanKey+"*");

        List retList = new ArrayList();
        if ("set".equals(type)) {
            do {
                ScanResult<String> resultList = jedis.sscan(key,cursor,sp);
                Integer listSize = resultList.getResult().size();
                if (listSize > 0) {
                    retList.addAll(resultList.getResult());
                    if (retList.size() >= pageSize) {
                        retList = retList.subList(0,pageSize);
                        break;
                    }
                }
                cursor = resultList.getCursor();
                if ("0".equals(cursor)) {
                    // 全库查询完
                    break;
                }
                sp.count(count*=2);
            } while (true);
        } else if ("zset".equals(type)) {
            do {
                ScanResult<Tuple> resultList = jedis.zscan(key,cursor,sp);
                Integer listSize = resultList.getResult().size();
                if (listSize > 0) {
                    retList.addAll(resultList.getResult());
                    if (retList.size() >= pageSize) {
                        retList = retList.subList(0,pageSize);
                        break;
                    }
                }
                cursor = resultList.getCursor();
                if ("0".equals(cursor)) {
                    // 全库查询完
                    break;
                }
                sp.count(count*=2);
            } while (true);
        } else if ("hash".equals(type)) {
            do {
                ScanResult<Map.Entry<String, String>> resultList = jedis.hscan(key,cursor,sp);
                Integer listSize = resultList.getResult().size();
                if (listSize > 0) {
                    retList.addAll(resultList.getResult());
                    if (retList.size() >= pageSize) {
                        retList = retList.subList(0,pageSize);
                        break;
                    }
                }
                cursor = resultList.getCursor();
                if ("0".equals(cursor)) {
                    // 全库查询完
                    break;
                }
                sp.count(count*=2);
            } while (true);
        }
        result.setData(retList);
        return result;
    }

    // 添加操作日志
    private void addOperationLog(String csId, int database, String operation, int time, String state, String remark) {
        Long userId = HttpServletUtil.getUserId();
        Map relMap = new HashMap();
        relMap.put("userId",userId);
        relMap.put("csId",csId);
//        Map data = redisDesktopManagerMapper.findById(relMap);

        Map map = new HashMap();
//        map.put("addressName",data.get("connectionName"));
//        map.put("addressUrl",data.get("address"));
        map.put("database",database);
        map.put("operation",operation);
        map.put("time",time);
        map.put("state",state);
        map.put("userId",userId);
        map.put("remark",remark);
        map.put("createDate",DateUtil.getDate());
        map.put("createTime",DateUtil.getTime());
//        redisDesktopManagerMapper.addOperationLog(map);
    }

}
