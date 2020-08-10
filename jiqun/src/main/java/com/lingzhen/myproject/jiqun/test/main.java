package com.lingzhen.myproject.jiqun.test;

import com.alibaba.fastjson.JSON;

public class main {

    public static void main(String[] args) {
        Parent p = new Parent();
        p.setId("1");
        Children c = new Children();
        c.setId("2");
        System.out.println(JSON.toJSONString(p));
        System.out.println(JSON.toJSONString(c));
    }
}
