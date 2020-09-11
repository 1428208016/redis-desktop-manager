package com.lingzhen.myproject.lifefolder.pojo;

/**
 * @date 2020-09-11
 * @author lingz
 */
public enum ResultInfo {

    //模块code(从1开始)+错误信息code（三位）
    //1 + 001
    REPEAT_LOGIN(1001,"重复登陆"),

    SUCCESS(200,"操作成功！"),
    ERROR(500,"操作失败");

    private int code;
    private String message;

    ResultInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
