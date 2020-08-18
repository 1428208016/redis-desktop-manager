package com.lingzhen.myproject.common;

import com.lingzhen.myproject.common.util.NetworkRequestOperator;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        NetworkRequestOperator networkRequestOperator = new NetworkRequestOperator();
        Map map = new HashMap();
        map.put("content-type","application/json");
        networkRequestOperator.setHeaders(map);

        String url = "http://apidev.bbapp.cn/bb/serverapi/activity/valentinesDay/express?text=12&userId=21";
        String str = networkRequestOperator.post(url,"");
        System.out.println(str);
    }
}
