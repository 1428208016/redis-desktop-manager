package com.lingzhen.rdm.util;

import java.util.UUID;

/**
 * uuid工具类
 * @date 2019-06-19
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
