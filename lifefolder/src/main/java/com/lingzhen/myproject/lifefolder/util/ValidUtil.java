package com.lingzhen.myproject.lifefolder.util;

import java.util.Map;

/**
 * 参数验证工具
 * @date 2020-08-26
 * @author lingz
 */
public class ValidUtil {

    /**
     * 判断map里面是否存在key值
     * @param map
     * @param key
     * @return
     */
    public static boolean valueNotNull(Map map,String key) {
        if (null == map.get(key) || map.get(key).toString().trim().length() <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
