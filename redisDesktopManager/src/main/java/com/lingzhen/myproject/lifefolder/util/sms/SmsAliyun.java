package com.lingzhen.myproject.lifefolder.util.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lingzhen.myproject.lifefolder.pojo.Result;

import java.util.Map;

/**
 * 阿里云短信发送接口实现
 */
public class SmsAliyun {

    private final String DOMAIN = "dysmsapi.aliyuncs.com";
    private final String VERSION = "2017-05-25";
    private final String ACTION = "SendSms";
    private final String REGION_ID = "cn-hangzhou";

    private final String ACCESS_KEY_ID  = "LTAI4GF7HMFg1EcNVYAqeipd";
    private final String ACCESS_KEY_SECRET  = "VwxhvaVsVkUH3dJzdOR8HQ819Q8mqv";

    // 发送注册验证码
    public Result sendRegisterYzm(String mobile, String code) {
        String signName = "ABC商城";
        String templateCode = "SMS_205891385";
        JSONObject json = new JSONObject();
        json.put("code",code);
        return this.send(mobile,signName,templateCode,json.toJSONString());
    }

    private Result send(String mobile, String signName, String templateCode, String templateParamJSON) {
        Result result = new Result();
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
            Map data = JSON.parseObject(response.getData());
            if (null == data || data.size() <= 0) {
                return result.setErrorReturn("系统异常");
            }
            if (!"OK".equals(data.get("Code").toString())) {
                return result.setErrorReturn(data.get("Message").toString());
            }
        } catch (Exception e) {
        }
        return result;
    }

}
