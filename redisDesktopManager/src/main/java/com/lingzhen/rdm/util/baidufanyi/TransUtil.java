package com.lingzhen.rdm.util.baidufanyi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 百度翻译工具类
 * createTime:2019-06-24
 *
 */
@Component
public class TransUtil {

    private static String APP_ID = "";      //appid
    private static String SECURITY_KEY = "";//密匙
    private static TransApi api = null;     //api对象

    public static final String EN = "en";
    public static final String ZH = "zh";
    //转换
    public static String getTransResult(String query,String from,String to){
        TransApi api = TransUtil.getTransApi();
        String s = api.getTransResult(query, from, to);
        JSONObject j = JSON.parseObject(s);
        if(null != j && null != j.get("trans_result")){
            JSONArray jarr = (JSONArray) j.get("trans_result");
            if(null != jarr && null != jarr.get(0)){
                if(jarr.size()>1){
                    JSONObject jobj = (JSONObject) jarr.get(0);
                    return ((JSONObject) jarr.get(0)).getString("dst");
                }else{
                    JSONObject jobj = (JSONObject) jarr.get(0);
                    return ((JSONObject) jarr.get(0)).getString("dst");
                }
            }else{
                return "";
            }
        }else{
            return "";
        }
    }

    //获取对象
    private static TransApi getTransApi(){
//        SysConfigService sysConfigService = (SysConfigService) BeanUtil.getBean("sysConfig");
//        if(null == TransUtil.api){
//            TransUtil.api = new TransApi(sysConfigService.findValueByCode("baidufanyi_APP_ID"),sysConfigService.findValueByCode("baidufanyi_SECURITY_KEY"));
//        }
        return TransUtil.api;
    }

}
