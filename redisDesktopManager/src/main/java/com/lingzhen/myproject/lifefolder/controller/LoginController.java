package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.LoginService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import com.lingzhen.myproject.lifefolder.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletRequest request,HttpServletResponse response) {
        Result result = new Result();
        Map map =  HttpServletUtil.getRequestParameter();

        try {
            result = loginService.login(map,request,response);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }
    /**
     * 注册
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(HttpServletResponse response) {
        Result result = new Result();
        Map map =  HttpServletUtil.getRequestParameter();

        //验证用户名
        this.validUserName(map,result);
        if (result.getCode() != Result.SUCCESS) {
            return result;
        }
        //验证密码
        this.validPassword(map,result);
        if (result.getCode() != Result.SUCCESS) {
            return result;
        }
        //验证手机号
        this.validPhoneNumber(map,result);
        if (result.getCode() != Result.SUCCESS) {
            return result;
        }
        // 验证验证码
        if (VerifyUtil.stringTrimIsEmpty(map.get("code"))) {
            return result.setErrorReturn("验证码为空");
        }

        try {
            result = loginService.register(map);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }

    private Result validUserName(Map map,Result result) {
        if (null == map.get("userName")) {
            return result.setErrorReturn("userName为空");
        }
        String userName = map.get("userName").toString().trim();
        if (userName.length() < 3 || userName.length() > 20) {
            return result.setErrorReturn("userName长度错误！");
        }
        String userNameReg = "([0-9]|[a-z]|[A-Z]|\\_|\\@|\\.)+";
        if (!userName.matches(userNameReg)) {
            return result.setErrorReturn("用户名格式不正确");
        }
        return result;
    }

    private Result validPassword(Map map,Result result) {
        if (null == map.get("password")) {
            return result.setErrorReturn("password为空");
        }
        String password = map.get("password").toString().trim();
        if (password.length() < 3 || password.length() > 16) {
            return result.setErrorReturn("密码长度错误！");
        }
        if (password.indexOf(" ") >= 0) {
            return result.setErrorReturn("密码格式错误！");
        }
        if (null == map.get("rePassword")) {
            return result.setErrorReturn("rePassword为空");
        }
        if (!password.equals(map.get("rePassword").toString())) {
            return result.setErrorReturn("两次密码不一致！");
        }
        map.put("password",password);

        return result;
    }

    private Result validPhoneNumber(Map map,Result result) {
        if (null == map.get("phoneNumber")) {
            return result.setErrorReturn("phoneNumber为空");
        }
        String phoneNumber = map.get("phoneNumber").toString().trim();
        if (phoneNumber.length() != 11) {
            return result.setErrorReturn("phoneNumber格式错误！");
        }
        String phoneNumberReg = "^(130|131|132|133|134|135|136|137|138|139|145|147|149|"+
                "150|151|152|153|155|156|157|158|159|173|175|176|177|178|"+
                "180|181|182|183|184|185|186|187|188|189)[0-9]{8}$";
        if(!phoneNumber.matches(phoneNumberReg)) {
            return result.setErrorReturn("phoneNumber格式错误！");
        }

        return result;
    }

    /**
     * 登出
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();

        try {
            result = loginService.logout(request,response);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }

    /**
     * 发送注册验证码
     */
    @PostMapping("sendRegisterYzm")
    @ResponseBody
    public Result sendRegisterYzm() {
        Result result = new Result();
        Map map =  HttpServletUtil.getRequestParameter();

        //验证手机号
        if (VerifyUtil.stringTrimIsEmpty(map.get("phoneNumber"))) {
            return result.setErrorReturn("手机号为空");
        }
        this.validPhoneNumber(map,result);
        if (result.getCode() != Result.SUCCESS) {
            return result;
        }

        try {
            String phoneNumber = map.get("phoneNumber").toString().trim();
            result = loginService.sendRegisterYzm(phoneNumber);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }


}
