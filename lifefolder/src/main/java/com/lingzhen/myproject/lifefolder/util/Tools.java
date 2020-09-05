package com.lingzhen.myproject.lifefolder.util;

/**
 * 工具类
 * @date 2019-06-20
 */
public class Tools {

    /**
     * 判断字符串是否为空
     * @param s
     * @return 为空返回true,否则返回false
     */
    public static boolean isEmpty(String s){
        return null ==s||"".equals(s)||"null".equals(s)||"NULL".equals(s)||"Null".equals(s);
    }

    /**
     * 判断字符串非空
     * @param s
     * @return 非空返回true,否则返回false
     */
    public static boolean notEmpty(String s){
        return null != s && !"".equals(s) && !"null".equals(s) && !"NULL".equals(s) && !"Null".equals(s);
    }
}
