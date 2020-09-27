package com.lingzhen.myproject.lifefolder.util;

/**
 * 参数验证工具
 * @date 2020-08-26
 * @author lingz
 *
 */
public class VerifyUtil {

    public static boolean stringIsEmpty(Object obj) {
        return isNull(obj) || obj.toString().isEmpty();
    }

    public static boolean stringTrimIsEmpty(Object obj) {
        return isNull(obj) || obj.toString().trim().isEmpty();
    }

    public static boolean isNull(Object obj) {
        return null == obj;
    }

}
