package com.lingzhen.myproject.englishword.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lingzhen.myproject.englishword.util.baidufanyi.TransApi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "";
    private static final String SECURITY_KEY = "";

    public static void main(String[] args) throws UnsupportedEncodingException {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "Java Enterprise";
        String s = api.getTransResult(query, "en", "zh");
        JSONObject j = JSON.parseObject(s);
        JSONArray jarr = (JSONArray) j.get("trans_result");
        JSONObject jobj = (JSONObject) jarr.get(0);
        System.out.println(jobj.get("src")+":"+jobj.get("dst"));

    }

    private static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        List<String> list = new ArrayList<String>();
        for(String temp : strs){
            if(!"".equals(temp)){
                if(temp.length() > 4){
                    list.add(temp.substring(0,4));
                }else{
                    list.add(temp);
                }
            }
        }
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for(String temp : list){
            returnStr += (char) Integer.valueOf(temp, 16).intValue();
        }
        return returnStr;
    }



}
