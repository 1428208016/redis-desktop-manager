package com.lingzhen.myproject.lifefolder.service.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.myproject.common.util.JWTUtil;
import com.lingzhen.myproject.common.util.PasswordStorage;
import com.lingzhen.myproject.lifefolder.component.RedisComponent;
import com.lingzhen.myproject.lifefolder.mapper.UserMapper;
import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.pojo.ResultInfo;
import com.lingzhen.myproject.lifefolder.service.LoginService;
import com.lingzhen.myproject.lifefolder.service.SystemService;
import com.lingzhen.myproject.lifefolder.service.UserService;
import com.lingzhen.myproject.lifefolder.util.Constant;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import com.lingzhen.myproject.lifefolder.util.IPUtil;
import com.lingzhen.myproject.lifefolder.util.RandomUtil;
import com.lingzhen.myproject.lifefolder.util.sms.SmsAliyun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    SystemService systemService;

    @Override
    public Result register(Map map) {
        Result result = new Result();

        String redisCode = redisComponent.get(Constant.REGISTER_YZM+map.get("phoneNumber"));
        if (!map.get("code").toString().equals(redisCode)) {
            return result.setErrorReturn("验证码错误！");
        }

        //判断用户名、手机号是否已注册
        List<Map> list = userMapper.validUserNameAndPhoneNumber(map);
        if (null != list && list.size() > 0) {
            String userName = map.get("userName").toString();
            String phoneNumber = map.get("phoneNumber").toString();
            for (Map temp : list) {
                if (temp.get("userName").toString().equals(userName)) {
                    return result.setErrorReturn("用户名已存在！");
                }
                if (temp.get("phoneNumber").toString().equals(phoneNumber)) {
                    return result.setErrorReturn("手机号已注册！");
                }
            }
        }

        //插入数据
        String hashPwd = PasswordStorage.createHash(map.get("password").toString());
        map.put("password",hashPwd);
        map.put("nickname",map.get("userName"));
        map.put("createTime", DateUtil.getTime());
        map.put("createDate", DateUtil.getDate());
        int i = userMapper.save(map);
        if (i > 0) {
            userService.initAccount(Long.valueOf(map.get("userId").toString()));
        }

        return result;
    }

    @Override
    public Result login(Map map, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        Result result = new Result();

        //验证token
        String token = HttpServletUtil.getToken();
        if (!"".equals(token) && JWTUtil.verifyToken(token)) {
            result.setResult(ResultInfo.REPEAT_LOGIN);
            return result;
        }
        //验证用户名密码
        Map data = userMapper.findByUserName(map);
        if (null == data || data.isEmpty()) {
            return result.setErrorReturn("用户名或密码错误！");
        }
        if (!PasswordStorage.verifyPassword(map.get("password").toString(),data.get("password").toString())) {
            return result.setErrorReturn("用户名或密码错误！");
        }
        //创建token
        Cookie cookie = new Cookie(JWTUtil.TOKEN,JWTUtil.createToken(data.get("userId").toString()));
        response.addCookie(cookie);

        return result;
    }

    @Override
    public Result logout(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        Result result = new Result();

        String token = HttpServletUtil.getToken();
        if ("".equals(token)) {
            return result.setErrorReturn("未登录！");
        }
        if (!JWTUtil.verifyToken(token)) {
            //无效的token
            return result;
        }

        //置空token
        Cookie cookie = new Cookie(JWTUtil.TOKEN,"");
        response.addCookie(cookie);

        return result;
    }

    @Override
    public Result sendRegisterYzm(String mobile) {
        Result result = new Result();

        // 1.验证
        String unableKey = Constant.UNABLE_SEND_REGISTER_YZM+mobile;
        boolean bool = redisComponent.exists(unableKey);
        if (bool) {
            return result.setErrorReturn("请稍后重试！");
        }

        Map data = userMapper.validMobile(mobile);
        if (null != data && data.size() > 0) {
            return result.setErrorReturn("该手机号已注册");
        }

        // 生成验证码
        String code = RandomUtil.randomNumber(6);
        // 发送
        SmsAliyun smsAliyun = new SmsAliyun();
        result = smsAliyun.sendRegisterYzm(mobile,code);
        if (result.getCode() != Result.SUCCESS) {
            return result;
        }

        // 存缓存
        redisComponent.set(Constant.REGISTER_YZM+mobile,code,60*5);
        redisComponent.set(unableKey,code,60);

        // 记录
        Map selMap = new HashMap();
        selMap.put("mobile",mobile);
        selMap.put("type","1");
        selMap.put("ip", IPUtil.getIpAddress());
        systemService.insertSendSmsLog(selMap);

        return result;
    }
}
