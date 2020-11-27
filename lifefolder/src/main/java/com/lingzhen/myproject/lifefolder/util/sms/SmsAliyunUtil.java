package com.lingzhen.myproject.lifefolder.util.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;

/**
 * 阿里云短信发送接口实现
 */
public class SmsAliyunUtil {

    private final String DOMAIN = "dysmsapi.aliyuncs.com";
    private final String VERSION = "2017-05-25";
    private final String ACTION = "SendSms";
    private final String REGION_ID = "cn-hangzhou";

    private final String ACCESS_KEY_ID  = "LTAI4GF7HMFg1EcNVYAqeipd";
    private final String ACCESS_KEY_SECRET  = "VwxhvaVsVkUH3dJzdOR8HQ819Q8mqv";

    // 发送注册验证码 5分钟过期
    public Object sendRegisterYzm(String mobile, String code) {
        String signName = "lingzhen";
        String templateCode = "SMS_205891385";
        JSONObject json = new JSONObject();
        json.put("code",code);
        return this.send(mobile,signName,templateCode,json.toJSONString());
    }

    private Object send(String mobile, String signName, String templateCode, String templateParamJSON) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(DOMAIN);
        request.setSysVersion(VERSION);
        request.setSysAction(ACTION);
        request.putQueryParameter("RegionId", REGION_ID);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParamJSON);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (Exception e) {
        }
        return null;
    }

}
