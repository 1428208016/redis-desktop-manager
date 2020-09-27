package com.lingzhen.myproject.lifefolder.service.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.myproject.common.util.JWTUtil;
import com.lingzhen.myproject.common.util.PasswordStorage;
import com.lingzhen.myproject.lifefolder.mapper.UserMapper;
import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.pojo.ResultInfo;
import com.lingzhen.myproject.lifefolder.service.LoginService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result register(Map map) {
        Result result = new Result();

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
        userMapper.save(map);
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
}
