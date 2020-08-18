package com.lingzhen.myproject.controller;

import com.lingzhen.myproject.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main")
public class MainController extends BaseController {

    @RequestMapping("index")
    @ResponseBody
    public String index() {
//        Subject subject = SecurityUtils.getSubject();
//        String str = subject.getSession().getId().toString();

        return "index page";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login() {

        PageData pd = this.getPageData();

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(pd.getString("username"), pd.getString("pwd")));
            System.out.println("登录成功!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
        }

        System.out.println(pd);
        return "login page";
    }





}
