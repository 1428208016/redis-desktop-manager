package com.lingzhen.myproject.lifefolder.util;

import java.util.Map;

public class PageUtil {

    public final static int PAGE_SIZE = 20;

    public static void PageHelper(Map map){

        int pageNum=1;
        int pageSize= PAGE_SIZE;

        if (null != map.get("pageNum")) {
            pageNum=Integer.valueOf(map.get("pageNum").toString());
        }

        if (null != map.get("pageSize")) {
            pageSize=Integer.valueOf(map.get("pageSize").toString());
        }
        com.github.pagehelper.PageHelper.startPage(pageNum,pageSize);
    }
}
