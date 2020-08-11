package com.lingzhen.myproject.englishword.util;

import java.util.UUID;

/**
 * uuid工具类
 * createTime:2019-0619
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
