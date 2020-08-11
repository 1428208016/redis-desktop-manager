package com.lingzhen.myproject.englishword.model;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String realName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public User() {}

    public User(String userId, String userName, String password, String realName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.realName = realName;
    }
}
