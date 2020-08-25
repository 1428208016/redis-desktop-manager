package com.lingzhen.myproject.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * @date 2020-08-07
 * @Author lingz
 */
public class DateUtil {
    /**
     * yyyy-MM-dd
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_DATE_TIME_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 获取当前时间的日份
     * @return
     */
    public static int getDay() {
        return getYearMonthDay(new Date(),Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时间的日份
     * @param date 日期
     * @return 年
     */
    public static int getDay(Date date) {
        return getYearMonthDay(date,Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间的月份
     * @return
     */
    public static Integer getMonth() {
        return getYearMonthDay(new Date(),Calendar.MONTH)+1;
    }

    /**
     * 获取指定时间的月份
     * @param date
     * @return
     */
    public static Integer getMonth(Date date) {
        return getYearMonthDay(date,Calendar.MONTH)+1;
    }

    /**
     * 获取当前时间的年份
     * @return
     */
    public static Integer getYear() {
        return getYear(new Date());
    }

    /**
     * 获取指定时间的年份
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {
        return getYearMonthDay(date,Calendar.YEAR);
    }

    /**
     * 获取指定时间的年月日
     * @param date
     * @param i
     * @return
     */
    private static Integer getYearMonthDay(Date date, int i){
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(i);
    }

    /**
     * 获取当天剩余秒
     * @return
     */
    public static long getSurplusOfDay(){
        long longg = 0;
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(parseDate(getDate()));
            c.add(Calendar.DAY_OF_MONTH,1);
            Date now = new Date();
            longg = c.getTime().getTime() - now.getTime();
            //当天剩余秒
            longg = longg/1000;
        } catch (Exception e) {
        }
        return longg;
    }

    /**
     * 解析为日期格式 yyyy-MM-dd
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        return parseDateTime(date,FORMAT_DATE);
    }

    /**
     * 解析为时间格式 yyyy-MM-dd HH:mm:ss
     * @param time
     * @return
     */
    public static Date parseTime(String time) {
        return parseDateTime(time,FORMAT_DATE_TIME);
    }

    /**
     * 解析日期时间
     * @param dateTime
     * @param format
     * @return
     */
    public static Date parseDateTime(String dateTime, String format){
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateTime);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 获取当天日期 yyyy-MM-dd
     * @return
     */
    public static String getDate(){
        return getDate(new Date());
    }

    /**
     * 获取任意时间的日期 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String getDate(Date date){
        return getDateTime(date,FORMAT_DATE);
    }

    /**
     * 获取当天时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime(){
        return getTime(new Date());
    }

    /**
     * 获取任意时间 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String getTime(Date date){
        return getDateTime(date,FORMAT_DATE_TIME);
    }

    /**
     * 获取字符串格式的日期时间
     * @param date
     * @param format
     * @return
     */
    private static String getDateTime(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static String getMillisecondTime(){
        return getDateTime(new Date(),FORMAT_DATE_TIME_MILLISECOND);
    }

}
