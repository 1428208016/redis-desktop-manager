package com.lingzhen.rdm.pojo;

/**
 * 返回类
 * @date 2020-08-22
 * @author linz
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;

    public Result(){
        this.code = Result.SUCCESS;
        this.message = "操作成功！";
    }

    public Result setErrorReturn(String message) {
        this.code = Result.ERROR;
        this.message = message;
        return this;
    }

    public void setError() {
        this.setError("操作失败");
    }

    public void setError(Exception e) {
        this.setError(e.getMessage());
    }

    public void setError(String message) {
        this.code = Result.ERROR;
        this.message = message;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
