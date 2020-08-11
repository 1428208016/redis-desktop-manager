package com.lingzhen.myproject.englishword.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * createTime:2019-06-26
 */
public class DateUtil {

    /**
     * 获取自定义格式的日期
     * @param format
     * @return
     */
    public static String getCurrentTiemFormat(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime(){ return getCurrentTiemFormat("yyyy-MM-dd HH:mm:ss"); }
    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static String getMillisecondTime(){
        return getCurrentTiemFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取当前时间：yyyy-MM-dd
     * @return
     */
    public static String getDay(){ return getCurrentTiemFormat("yyyy-MM-dd");}
}
