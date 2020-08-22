package com.lingzhen.myproject.lifefolder.pojo;

/**
 * 返回类
 * createTime:2020-08-22
 * @author linz
 */
public class Result {
    private int code;
    private String message;

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;

    public Result(){
        this.code = Result.SUCCESS;
        this.message = "操作成功！";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
