package com.lingzhen.myproject.test;

public class Parent {
    private transient String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
