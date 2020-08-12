package com.lingzhen.myproject.common.util;

import java.util.UUID;

/**
 * uuid工具类
 * createTime:2019-06-19
 * @author lingz
 */
public class UuidUtil {

    /**
     * 生成一个新的Uuid
     * @return String
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
